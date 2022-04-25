package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.refund.*;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.CarrierFee;
import com.ndc.channel.flight.xmlBean.refundApply.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.*;
import com.ndc.channel.flight.xmlBean.refundApply.response.bean.Error;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.BookingRef;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.OrderChangeParameters;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Request;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.OrderAmendment;
import com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PaymentInfo;
import com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BinaryObject;
import com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Media;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.service.NdcFlightApiRefundOrderRelService;
import com.ndc.channel.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NdcFlightOrderRefundHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightApiOrderRelMapper apiOrderRelMapper;

    @Resource
    private OrderDetailDelayQueryExecutor delayQueryExecutor;

    @Resource
    private NdcFlightApiRefundOrderRelService refundOrderRelService;

    /**
     * 退票单提交
     * @param params
     * @return
     */
    public String refundConfirm(RefundApplyParams params) {

        final NdcFlightApiOrderRel orderRel = apiOrderRelMapper.selectByOrderId(params.getOrderNumber());

        List<String> ticketNumberListRefund = params.getRefundPassengerList().stream().map(RefundApplyPassengerParams::getTicketNumber).collect(Collectors.toList());
        Response refundApplyResponse = refundApply(orderRel.getOrderId(), ticketNumberListRefund);

        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request request = getRefundConfirmReqData(params, refundApplyResponse, orderRel);

        List<String> contactInfoId = request.getDataLists().getContactInfoList().stream().map(com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo::getContactInfoID).collect(Collectors.toList());
        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.IATAOrderChangeRQ rq = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.IATAOrderChangeRQ(contactInfoId);
        rq.setRequest(request);

        com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.IATAOrderViewRS iataOrderViewRS = ndcApiTools.refundConfirm(rq);

        final com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.Error error = iataOrderViewRS.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getError().getDescText());
        }

        // 本次退票乘客ID
        List<String> paxIdList = request.getDataLists().getPaxList().stream().map(com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax::getPaxID).collect(Collectors.toList());

        Long refundRelId = refundOrderRelService.insertEntity(params, orderRel.getOrderId(), rq.getPayloadAttributes().getEchoTokenText());

        NdcRefundOrderSearchParams searchParams = new NdcRefundOrderSearchParams(refundRelId, paxIdList);

        delayQueryExecutor.submitTask(JSON.toJSONString(new MsgBody(searchParams, "2")), 60L);

        return String.valueOf(refundRelId);
    }

    /**
     * 机票退票申请
     * @param orderId
     * @return
     */
    public Response refundApply(String orderId, List<String> ticketNumberList) {

        NdcFlightApiOrderRel ndcFlightApiOrderRel = apiOrderRelMapper.selectByOrderId(orderId);

        IATAOrderRetrieveRQ iataOrderRetrieveRQ = new IATAOrderRetrieveRQ(orderId, ndcFlightApiOrderRel);

        IATAOrderViewRS refundApplyResult = ndcApiTools.refundApply(iataOrderRetrieveRQ);

        final Error error = refundApplyResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        Response response = refundApplyResult.getResponse();


        /*for (TicketDocInfo ticketDocInfo : response.getOrderAmendment().getTicketDocInfo()) {
            Ticket ticket = ticketDocInfo.getTicket();
            Coupon coupon = ticket.getCoupon();
            //客票状态
            String couponStatusCode = coupon.getCouponStatusCode();

            // 不可退标识，true不可退，false可退
            Boolean nonRefundableInd = coupon.getNonRefundableInd();

            // 客票状态描述
            String labelByCode = BusinessEnum.TicketStatus.getLabelByCode(couponStatusCode);
            if (nonRefundableInd && ticketNumberList.contains(ticket.getTicketNumber())) {
                throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "票号（"+ticket.getTicketNumber()+"）不可退，客票状态（"+labelByCode+")");
            }
        }*/

        return response;

    }
    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request getRefundConfirmReqData(RefundApplyParams params, com.ndc.channel.flight.xmlBean.refundApply.response.bean.Response refundApplyResponse, NdcFlightApiOrderRel orderRel) {
        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request request = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request();

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.OrderChangeParameters parameters = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.OrderChangeParameters();
        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(params.getRefundReason()));

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists refundConfirmDataLists = createRefundConfirmDataLists(params, refundApplyResponse);
        request.setDataLists(refundConfirmDataLists);

        final Map<String, RefundApplyPassengerParams> repeatTicketNumberMapping = params.getRefundPassengerList().stream().collect(Collectors.toMap(RefundApplyPassengerParams::getIdcardCode, Function.identity()));
        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> bookingRefList = createRefundConfirmBookingRef(parameters.getReasonCode(), request.getDataLists().getPaxList(), orderRel, repeatTicketNumberMapping);
        request.setBookingRef(bookingRefList);

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order refundConfirmOrder = createRefundConfirmOrder(orderRel);
        request.setOrder(refundConfirmOrder);

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Remark remark = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Remark();

        final JSONObject remarkJson = new JSONObject();
        remarkJson.put("orderId", orderRel.getOrderId());
        remarkJson.put("actionTypeCode", "Add");
        remarkJson.put("reasonRemarkText", params.getMemo());
        if (CollectionUtils.isNotEmpty(refundConfirmDataLists.getMediaList())) {
            remarkJson.put("mediaId", refundConfirmDataLists.getMediaList().stream().map(Media::getMediaID).collect(Collectors.toList()));
        }
        remark.setRemarkText(remarkJson.toJSONString());
        parameters.setRemark(remark);
        request.setOrderChangeParameters(parameters);

        return request;
    }

    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists createRefundConfirmDataLists(RefundApplyParams params, Response refundApplyResponse) {

        final List<String> refundAttachmentUrl = params.getRefundAttachmentUrl();

        List<String> idCardListRefundPassenger = params.getRefundPassengerList().stream().map(RefundApplyPassengerParams::getIdcardCode).collect(Collectors.toList());

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists refundConfirmDataLists = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists();

        final DataLists dataLists = refundApplyResponse.getDataLists();

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> refundConfirmPaxList = createRefundConfirmPaxList(dataLists, idCardListRefundPassenger);
        refundConfirmDataLists.setPaxList(refundConfirmPaxList);

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> refundConfirmContactInfoList = createRefundConfirmContactInfo(dataLists);
        refundConfirmDataLists.setContactInfoList(refundConfirmContactInfoList);


        List<Media> mediaList = createMediaList(refundAttachmentUrl);
        if (CollectionUtils.isNotEmpty(mediaList)) {

            refundConfirmDataLists.setMediaList(mediaList);
        }

        return refundConfirmDataLists;
    }

    private static List<Media> createMediaList(List<String> refundAttachmentUrl) {

        if (CollectionUtils.isNotEmpty(refundAttachmentUrl)) {
            return Collections.EMPTY_LIST;
        }

        List<Media> mediaList = new ArrayList<>();

        for (String attachUrl : refundAttachmentUrl) {
            if (StringUtils.isEmpty(attachUrl)) {
                continue;
            }
            final BinaryObject base64Str = getFileBase64Str(attachUrl);
            if (base64Str != null) {
                Media media = new Media();
                media.setMediaID(UUID.randomUUID().toString());
                media.setBinaryObject(base64Str);
                media.setDescText("退票申请证明文件");

                mediaList.add(media);
            }
        }
        return mediaList;
    }

    private static BinaryObject getFileBase64Str(String url) {

        BinaryObject binaryObject = new BinaryObject();
        try {
            URLConnection connection = new URL(url).openConnection();

            InputStream inStream = connection.getInputStream();

            byte[] bytes = FileUtil.readFileInputStream(inStream);

            final String fileStr = Base64.getEncoder().encodeToString(bytes);

            final File file = new File(url);
            binaryObject.setFileName(file.getName());
            binaryObject.setFormat(connection.getContentType());
            binaryObject.setValue(fileStr);
            return binaryObject;
        }catch (Exception e) {
            log.error("附件下载失败，url={}", url);
        }

        return null;
    }

    public static void main(String[] args) {

        try{
            String s = "https://www.appzyw.net/upfiles/image/201909/20190930104220747.jpg";
            final BinaryObject binaryObject = getFileBase64Str(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> createRefundConfirmPaxList(DataLists dataLists, List<String> idCardListRefundPassenger) {
        final List<Pax> paxList = dataLists.getPaxList().stream().filter(pax -> {
            final IdentityDoc identityDoc = pax.getIdentityDoc();
            return idCardListRefundPassenger.contains(identityDoc.getIdentityDocID());
        }).collect(Collectors.toList());
        return JSONObject.parseObject(JSON.toJSONString(paxList), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax>>() {});
    }
    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> createRefundConfirmBookingRef(String refundReasonCode, List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> paxList, NdcFlightApiOrderRel orderRel, Map<String, RefundApplyPassengerParams> repeatTicketNumberMapping) {

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> bookingRefList = new ArrayList<>();

        for (com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax pax : paxList) {
            bookingRefList.add(createBookingRef(orderRel.getOwnerCode(), pax.getPaxID(), BusinessEnum.BookingRefTypeCode.PAX_ID.getCode()));
            if (BusinessEnum.RefundWay._23.getCode().equals(refundReasonCode)) {
                String repeatTicketNumber = repeatTicketNumberMapping.get(pax.getIdentityDoc().getIdentityDocID()).getRepeatTicketNumber();
                if (StringUtils.isEmpty(repeatTicketNumber)) {
                    throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, "重购客票退票，客票号必填！");
                }
                bookingRefList.add(createBookingRef(orderRel.getOwnerCode(), repeatTicketNumber, BusinessEnum.BookingRefTypeCode.REPEAT_TICKET_NUMBER.getCode()));
            }
        }

        return bookingRefList;
    }

    private static com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef createBookingRef(String ownerCode, String bookingID, String bookingRefTypeCode) {
        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef br = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef();
        br.setBookingID(bookingID);
        br.setBookingRefTypeCode(bookingRefTypeCode);

        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingEntity be = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingEntity();
        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Carrier carrier = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Carrier();
        carrier.setAirlineDesigCode(ownerCode);
        be.setCarrier(carrier);
        br.setBookingEntity(be);

        return br;
    }


    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> createRefundConfirmContactInfo(DataLists dataLists) {

        final List<ContactInfo> contactInfoList = dataLists.getContactInfoList();

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> contactInfos = JSONArray.parseArray(JSON.toJSONString(contactInfoList), com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo.class);
//        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo> contactInfos = JSONObject.parseObject(JSON.toJSONString(contactInfoList), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo>>() {});
        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.ContactInfo primaryContact = contactInfos.stream().filter(cf -> cf.getContactTypeText().equals("PRIMARY")).findFirst().get();
        primaryContact.setContactTypeText("APPLICANT");

        return contactInfos.stream().filter(c->!c.getContactTypeText().equals("TRAVEL_AGENCY")).collect(Collectors.toList());
    }
    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order createRefundConfirmOrder(NdcFlightApiOrderRel orderRel) {
        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order order = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order();
        order.setOrderID(orderRel.getOrderId());
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(orderRel.getOwnerTypeCode());

        return order;
    }
}
