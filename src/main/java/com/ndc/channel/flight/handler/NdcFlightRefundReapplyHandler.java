package com.ndc.channel.flight.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ndc.channel.entity.NdcFlightApiOrderRel;
import com.ndc.channel.entity.NdcFlightApiRefundOrderRel;
import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.executor.OrderDetailDelayQueryExecutor;
import com.ndc.channel.flight.dto.MsgBody;
import com.ndc.channel.flight.dto.orderDetail.NdcOrderDetailData;
import com.ndc.channel.flight.dto.orderDetail.OrderTicketInfo;
import com.ndc.channel.flight.dto.refund.NdcRefundOrderSearchParams;
import com.ndc.channel.flight.dto.refund.RefundReapplyParams;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.Response;
import com.ndc.channel.flight.xmlBean.refundReapply.request.bean.*;
import com.ndc.channel.flight.xmlBean.refundReapply.response.bean.Error;
import com.ndc.channel.flight.xmlBean.refundReapply.response.bean.IATAOrderViewRS;
import com.ndc.channel.mapper.NdcFlightApiOrderRelMapper;
import com.ndc.channel.mapper.NdcFlightApiRefundOrderRelMapper;
import com.ndc.channel.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 退票重新申请
 */
@Slf4j
@Component
public class NdcFlightRefundReapplyHandler {

    @Resource
    private NdcFlightApiOrderRelMapper orderRelMapper;

    @Resource
    private NdcFlightApiRefundOrderRelMapper refundOrderRelMapper;

    @Resource
    private NdcApiTools ndcApiTools;

    @Resource
    private NdcFlightRefundOrderDetailHandler refundOrderDetailHandler;

    @Resource
    private OrderDetailDelayQueryExecutor delayQueryExecutor;

    public boolean refundReapply(RefundReapplyParams params) {

        // 退票单关联主键
        final Long refundRelId = params.getNdcRefundRelId();
        NdcFlightApiRefundOrderRel refundOrderRel = refundOrderRelMapper.selectByPrimaryKey(refundRelId);
        String refundId = refundOrderRel.getRefundId();

        NdcFlightApiOrderRel orderRel = orderRelMapper.selectByOrderId(refundOrderRel.getOrderId());

        // 退货单查询
        NdcOrderDetailData detailData = refundOrderDetailHandler.refundOrderDetail(orderRel, refundOrderRel);

        /*if (!detailData.getTicketInfoList().get(0).getRefundAuditingStatus().equals(BusinessEnum.RefundAuditingStatus.REJECT_FIRST.getCode())) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, "退货单审核状态不允许重新申请退票");
        }*/

        Response refundDetailResponse = detailData.getRefundDetailResponse();

        com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.DataLists dataLists = refundDetailResponse.getDataLists();
        Request request = createRefundReapplyRequest(orderRel, refundId, params, dataLists);

        final List<String> contactInfoIdList = request.getDataLists().getContactInfoList().stream().map(ContactInfo::getContactInfoID).collect(Collectors.toList());

        IATAOrderChangeRQ rq = new IATAOrderChangeRQ(contactInfoIdList);
        rq.setRequest(request);

        IATAOrderViewRS iataOrderViewRS = ndcApiTools.refundReapply(rq);
        final Error error = iataOrderViewRS.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.FLIGHT_CHANNEL_ERROR, error.getError().getDescText());
        }

        List<String> paxIdList = detailData.getTicketInfoList().stream().map(OrderTicketInfo::getPaxId).collect(Collectors.toList());

        NdcRefundOrderSearchParams searchParams = new NdcRefundOrderSearchParams(refundRelId, paxIdList);

        delayQueryExecutor.submitTask(JSON.toJSONString(new MsgBody(searchParams, "2")), 60L);

        return true;
    }

    private Request createRefundReapplyRequest(NdcFlightApiOrderRel orderRel, String channelRefundId, RefundReapplyParams params, com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.DataLists dataLists) {

        final Request request = new Request();

        final DataLists refundReapplyDataLists = createRefundReapplyDataLists(params, dataLists);
        request.setDataLists(refundReapplyDataLists);

        final Order order = createRefundReapplyOrder(orderRel);
        request.setOrder(order);

        final BookingRef bookingRef = createRefundReapplyBookingRef(channelRefundId, orderRel);
        request.setBookingRef(bookingRef);

        final OrderChangeParameters parameters = createOrderChangeParameters(orderRel, refundReapplyDataLists, params);

        request.setOrderChangeParameters(parameters);

        return request;
    }

    public OrderChangeParameters createOrderChangeParameters(NdcFlightApiOrderRel orderRel, DataLists refundReapplyDataLists, RefundReapplyParams params) {

        OrderChangeParameters parameters = new OrderChangeParameters();
        parameters.setReasonCode(BusinessEnum.RefundWay.getReasonCode(params.getRefundReason()));

        final Remark remark = new Remark();

        JSONObject remarkJson = new JSONObject();
        remarkJson.put("orderId", orderRel.getOrderId());
        remarkJson.put("reasonRemarkText", params.getMemo());
        if (CollectionUtils.isNotEmpty(refundReapplyDataLists.getMediaList())) {
            remarkJson.put("mediaId", refundReapplyDataLists.getMediaList().stream().map(Media::getMediaID).collect(Collectors.toList()));
        }
        remark.setRemarkText(remarkJson.toJSONString());
        parameters.setRemark(remark);

        return parameters;
    }

    public BookingRef createRefundReapplyBookingRef(String channelRefundId, NdcFlightApiOrderRel orderRel) {
        BookingRef bookingRef = new BookingRef();

        bookingRef.setBookingID(channelRefundId);
        bookingRef.setBookingRefTypeCode(BusinessEnum.BookingRefTypeCode.REFUND_ORDER_ID.getCode());

        final BookingEntity bookingEntity = new BookingEntity();
        final Carrier carrier = new Carrier();
        carrier.setAirlineDesigCode(orderRel.getOwnerCode());

        bookingEntity.setCarrier(carrier);

        bookingRef.setBookingEntity(bookingEntity);

        return bookingRef;
    }

    public Order createRefundReapplyOrder(NdcFlightApiOrderRel orderRel) {

        final Order order = new Order();

        order.setOrderID(orderRel.getOrderId());
        order.setOwnerCode(orderRel.getOwnerCode());
        order.setOwnerTypeCode(orderRel.getOwnerTypeCode());

        return order;
    }

    private DataLists createRefundReapplyDataLists(RefundReapplyParams params, com.ndc.channel.flight.xmlBean.refundOrderDetail.response.bean.DataLists dataListsParams) {
        DataLists dataLists = new DataLists();

        final String[] agentContactInfo = orderRelMapper.selectContact().split(",");

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactInfoID(UUID.randomUUID().toString());
        contactInfo.setContactTypeText("APPLICANT");

        Individual individual = new Individual();
        individual.setIndividualID(UUID.randomUUID().toString());
        individual.setSurname(agentContactInfo[0]);
        contactInfo.setIndividual(individual);

        final Phone phone = new Phone();
        phone.setPhoneNumber(agentContactInfo[1]);
        phone.setCountryDialingCode("+86");
        contactInfo.setPhone(Arrays.asList(phone));

        dataLists.setContactInfoList(Arrays.asList(contactInfo));

        final List<Media> mediaList = createMediaList(params.getRefundAttachmentUrl());
        if (CollectionUtils.isNotEmpty(mediaList)) {

            dataLists.setMediaList(mediaList);
        }
        return dataLists;
    }

    private List<Media> createMediaList(List<String> refundAttachmentUrl) {
        if (CollectionUtils.isEmpty(refundAttachmentUrl)) {
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
}
