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
import com.ndc.channel.flight.dto.refund.RefundApplyParams;
import com.ndc.channel.flight.dto.refund.RefundApplyPassengerParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryParams;
import com.ndc.channel.flight.dto.refund.RefundChangeMoneyQueryResp;
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

        final com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.Response refundConfirmResponse = iataOrderViewRS.getResponse();

        final com.ndc.channel.flight.xmlBean.refundConfirm.response.bean.Order order = refundConfirmResponse.getOrder();
        final String refundId = order.getOrderID();

        refundOrderRelService.insertEntity(params, orderRel.getOrderId(), refundId, rq.getPayloadAttributes().getEchoTokenText());

        delayQueryExecutor.submitTask(JSON.toJSONString(new MsgBody(orderRel.getOrderId(), "2")), 60L);

        return refundId;
    }

    /**
     * 机票退票金额查询
     * @param params
     * @return
     */
    public RefundChangeMoneyQueryResp refundMoneyQuery(RefundChangeMoneyQueryParams params) {

        NdcFlightApiOrderRel ndcOrderRel = apiOrderRelMapper.selectByOrderId(params.getChannelOrderNumber());

        final Response refundApplyResp = refundApply(params.getChannelOrderNumber(), params.getTicketNumList());

        final DataLists dataLists = refundApplyResp.getDataLists();

        final IATAOrderChangeRQ refundAmountQueryReqParams = createRefundAmountQueryReqParams(params, ndcOrderRel, dataLists);
        com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.IATAOrderViewRS refundAmountQueryResult = ndcApiTools.refundAmountQuery(refundAmountQueryReqParams);

        com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Error error = refundAmountQueryResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        RefundChangeMoneyQueryResp resp = new RefundChangeMoneyQueryResp();

        PaymentInfo paymentInfo = refundAmountQueryResult.getPaymentInfo();
        if (paymentInfo == null) {
            if (params.getQueryType() == 1) {
                resp.setChangeFee(BigDecimal.ZERO);
            }else {
                resp.setRefundFee(BigDecimal.ZERO);
            }
            return resp;
        }

        final com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Response response = refundAmountQueryResult.getResponse();

        OrderAmendment orderAmendment = response.getOrderAmendment();
        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo> ticketDocInfoList = orderAmendment.getTicketDocInfo();

        resp = getRefundChangeMoneyQueryResp(params.getTicketNumList(), ticketDocInfoList);


//        redisUtils.setStrExpire(getKey(ndcOrderRel.getOrderId(), params.getTicketNumList()), JSON.toJSONString(resp), 5, TimeUnit.MINUTES);

        return resp;
    }

    /*private static String getKey(String orderId, List<String> ticketNumber) {
        return new StringBuilder(orderId).append("-").append(ticketNumber.stream().sorted().collect(Collectors.joining(","))).toString();
    }*/

    /**
     * 机票退票申请
     * @param orderId
     * @return
     */
    private Response refundApply(String orderId, List<String> ticketNumberList) {

        NdcFlightApiOrderRel ndcFlightApiOrderRel = apiOrderRelMapper.selectByOrderId(orderId);

        IATAOrderRetrieveRQ iataOrderRetrieveRQ = new IATAOrderRetrieveRQ(orderId, ndcFlightApiOrderRel);

        IATAOrderViewRS refundApplyResult = ndcApiTools.refundApply(iataOrderRetrieveRQ);

        final Error error = refundApplyResult.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getDescText());
        }

        Response response = refundApplyResult.getResponse();


        for (TicketDocInfo ticketDocInfo : response.getOrderAmendment().getTicketDocInfo()) {
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
        }

        return response;

    }

    private IATAOrderChangeRQ createRefundAmountQueryReqParams(RefundChangeMoneyQueryParams params, NdcFlightApiOrderRel ndcOrderRel, DataLists dataLists) {

        final List<Pax> paxList = dataLists.getPaxList();
        final List<String> contactInfoRefID = dataLists.getContactInfoList().stream().map(ContactInfo::getContactInfoID).collect(Collectors.toList());

        IATAOrderChangeRQ iataOrderChangeRQ = new IATAOrderChangeRQ(contactInfoRefID);
        Request request = new Request();

        request.setOrder(createOrder(params.getChannelOrderNumber(), ndcOrderRel));
        request.setBookingRef(createBookingRef(params.getChannelOrderNumber(), ndcOrderRel, paxList));
        request.setDataLists(createDateLists(dataLists));
        request.setOrderChangeParameters(createOrderChangeParameters(params.getRefundWay()));

        iataOrderChangeRQ.setRequest(request);

        return iataOrderChangeRQ;
    }

    private com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Order createOrder(String channelOrderNumber, NdcFlightApiOrderRel orderRel) {
        com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Order order = new com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Order();

        order.setOrderID(channelOrderNumber);
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(orderRel.getOwnerTypeCode());
        return order;
    }

    private List<BookingRef> createBookingRef(String orderId, NdcFlightApiOrderRel ndcFlightApiOrderRel, List<Pax> paxList) {

        return paxList.stream().map(pax -> {
            BookingRef bookingRef = new BookingRef();

            bookingRef.setBookingID(pax.getPaxID());
            bookingRef.setBookingRefTypeCode("1");
            return bookingRef;
        }).collect(Collectors.toList());
    }

    private com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.DataLists createDateLists(DataLists dataLists) {
        final com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.DataLists dataList = new com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.DataLists();

        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Pax> paxList = JSONObject.parseObject(JSON.toJSONString(dataLists.getPaxList()), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.Pax>>() {
        });

        final List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo> contactInfoList = JSONObject.parseObject(JSON.toJSONString(dataLists.getContactInfoList()), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo>>() {});
        final com.ndc.channel.flight.xmlBean.refundAmountSearch.request.bean.ContactInfo primaryContact = contactInfoList.stream().filter(cf -> cf.getContactTypeText().equals("PRIMARY")).findFirst().get();
        primaryContact.setContactTypeText("APPLICANT");

        dataList.setPaxList(paxList);
        dataList.setContactInfoList(contactInfoList.stream().filter(c->!c.getContactTypeText().equals("TRAVEL_AGENCY")).collect(Collectors.toList()));

        return dataList;
    }

    private OrderChangeParameters createOrderChangeParameters(byte refundWay) {
        OrderChangeParameters parameters = new OrderChangeParameters();

        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(refundWay));

        return parameters;
    }

    private RefundChangeMoneyQueryResp getRefundChangeMoneyQueryResp(List<String> ticketNumList, List<com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo> ticketDocInfoList) {

        RefundChangeMoneyQueryResp resp = new RefundChangeMoneyQueryResp();

        BigDecimal refundFee = new BigDecimal("0");

        BigDecimal changeFee = new BigDecimal("0");

        BigDecimal refundMoney = new BigDecimal("0");

        for (com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.TicketDocInfo ticketDocInfo : ticketDocInfoList) {

            String ticketNumber = ticketDocInfo.getTicket().getTicketNumber();

            CarrierFee carrierFee = ticketDocInfo.getCarrierFee();

            if (ticketNumList.contains(ticketNumber)) {

                Map<String, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount> penaltyAmountMap = ticketDocInfo.getPenalty().stream().collect(Collectors.toMap(com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getTypeCode, com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.Penalty::getPenaltyAmount));

                com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount penaltyAmountRefund = penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CANCELLATION.getCode());
                com.ndc.channel.flight.xmlBean.refundAmountSearch.response.bean.refund.PenaltyAmount penaltyAmountChange = penaltyAmountMap.get(BusinessEnum.ChangeRefundTypeCode.CHANGE.getCode());

                refundFee = refundFee.add(new BigDecimal(Optional.ofNullable(penaltyAmountRefund.getValue()).orElse("0")));
                changeFee = changeFee.add(new BigDecimal(Optional.ofNullable(penaltyAmountChange.getValue()).orElse("0")));

                refundMoney = refundMoney.add(new BigDecimal(Optional.ofNullable(carrierFee.getAmount().getValue()).orElse("0")));
            }
        }

        resp.setChangeFee(changeFee);
        resp.setRefundFee(refundFee);
        resp.setRefundMoney(refundMoney);

        return resp;
    }


    private com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request getRefundConfirmReqData(RefundApplyParams params, com.ndc.channel.flight.xmlBean.refundApply.response.bean.Response refundApplyResponse, NdcFlightApiOrderRel orderRel) {
        com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request request = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Request();

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.DataLists refundConfirmDataLists = createRefundConfirmDataLists(params, refundApplyResponse);
        request.setDataLists(refundConfirmDataLists);

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> bookingRefList = createRefundConfirmBookingRef(request.getDataLists().getPaxList(), orderRel);
        request.setBookingRef(bookingRefList);

        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Order refundConfirmOrder = createRefundConfirmOrder(orderRel);
        request.setOrder(refundConfirmOrder);


        final com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.OrderChangeParameters parameters = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.OrderChangeParameters();
        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(params.getRefundWay()));

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

        if (CollectionUtils.isNotEmpty(refundAttachmentUrl)) {

            List<Media> mediaList = refundAttachmentUrl.stream().filter(s-> StringUtils.isNoneBlank(s)).map(attachUrl -> {
                Media media = new Media();
                media.setMediaID(UUID.randomUUID().toString());
                media.setBinaryObject(getFileBase64Str(attachUrl));
                media.setDescText("退票申请证明文件");

                return media;
            }).collect(Collectors.toList());

            if (mediaList.size() != 0) {
                refundConfirmDataLists.setMediaList(mediaList);
            }
        }

        return refundConfirmDataLists;
    }

    private static BinaryObject getFileBase64Str(String url) {

        BinaryObject binaryObject = new BinaryObject();
        try {
            URLConnection connection = new URL(url).openConnection();

            InputStream inStream = connection.getInputStream();

            byte[] bytes = readFileInputStream(inStream);

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
//            System.out.println(JSON.toJSONString(binaryObject));

            final Media media = new Media();

            media.setBinaryObject(binaryObject);
            media.setMediaID(UUID.randomUUID().toString());

            final JSONObject remarkJson = new JSONObject();
            remarkJson.put("actionTypeCode", "Add");
            remarkJson.put("mediaId", Arrays.asList(media).stream().map(Media::getMediaID).collect(Collectors.toList()));

            System.out.println(remarkJson.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static byte[] readFileInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outStream= new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inputStream.read(buffer)) != -1){
            outStream.write(buffer,0, len);

        }
        inputStream.close();

        return outStream.toByteArray();
    }

    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> createRefundConfirmPaxList(DataLists dataLists, List<String> idCardListRefundPassenger) {
        final List<Pax> paxList = dataLists.getPaxList().stream().filter(pax -> {
            final IdentityDoc identityDoc = pax.getIdentityDoc();
            return idCardListRefundPassenger.contains(identityDoc.getIdentityDocID());
        }).collect(Collectors.toList());
        return JSONObject.parseObject(JSON.toJSONString(paxList), new TypeReference<List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax>>() {});
    }
    private List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> createRefundConfirmBookingRef(List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Pax> paxList, NdcFlightApiOrderRel orderRel) {

        final List<com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef> bookingRefList = paxList.stream().map(pax -> {
            com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef br = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingRef();
            br.setBookingID(pax.getPaxID());
            br.setBookingRefTypeCode("1");

            com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingEntity be = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.BookingEntity();
            com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Carrier carrier = new com.ndc.channel.flight.xmlBean.refundConfirm.request.bean.Carrier();
            carrier.setAirlineDesigCode(orderRel.getOwnerCode());
            be.setCarrier(carrier);
            br.setBookingEntity(be);

            return br;
        }).collect(Collectors.toList());

        return bookingRefList;
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
