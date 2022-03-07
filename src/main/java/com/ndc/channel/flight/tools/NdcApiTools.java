package com.ndc.channel.flight.tools;

import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.IATAOrderCreateRQ;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.IATAOrderViewRS;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.IATAAirShoppingRQ;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.IATAAirShoppingRS;
import com.ndc.channel.flight.xmlBean.orderDetail.request.bean.IATAOrderRetrieveRQ;
import com.ndc.channel.flight.xmlBean.orderPay.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.IATAOfferPriceRQ;
import com.ndc.channel.flight.xmlBean.verifyPrice.response.bean.IATAOfferPriceRS;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.entity.NdcAccountInfo;
import com.ndc.channel.mapper.NdcAccountInfoMapper;
import com.ndc.channel.model.NdcAccountInfoData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class NdcApiTools {

    @Resource
    private NdcAccountInfoMapper accountInfoMapper;

    /**
     * 航班查询
     * @param rq
     * @return
     */
    public IATAAirShoppingRS flightSearch(IATAAirShoppingRQ rq) {
        try{

            NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

            accountInfo.setNdcApiInfo(BusinessEnum.NdcApiInfo.FLIGHT_SEARCH);

            return remote(accountInfo, rq, IATAAirShoppingRQ.class, IATAAirShoppingRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方Ndc机票查询接口异常", e);
            return null;
        }
    }

    /**
     * 验舱验价
     * @param rq
     * @return
     */
    public IATAOfferPriceRS flightOfferPrice(IATAOfferPriceRQ rq) {
        try{

            NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

            accountInfo.setNdcApiInfo(BusinessEnum.NdcApiInfo.OFFER_PRICE);

            return remote(accountInfo, rq, IATAOfferPriceRQ.class, IATAOfferPriceRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc验舱验价接口异常", e);
            return null;
        }
    }


    /**
     * 机票订单创建
     * @param rq
     * @return
     */
    public IATAOrderViewRS createOrder(IATAOrderCreateRQ rq) {
        try{

            NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

            accountInfo.setNdcApiInfo(BusinessEnum.NdcApiInfo.CREATE_ORDER);

            return remote(accountInfo, rq, IATAOrderCreateRQ.class, IATAOrderViewRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc创单接口异常", e);
            return null;
        }
    }

    /**
     * 机票订单支付出票
     * @param rq
     * @return
     */
    public com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS orderPay(IATAOrderChangeRQ rq) {
        try{

            NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

            accountInfo.setNdcApiInfo(BusinessEnum.NdcApiInfo.ORDER_PAY);

            return remote(accountInfo, rq, IATAOrderChangeRQ.class, com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc订单支付接口异常", e);
            return null;
        }
    }

    /**
     * 机票订单详情
     * @param rq
     * @return
     */
    public com.ndc.channel.flight.xmlBean.orderDetail.response.bean.IATAOrderViewRS orderDetail(IATAOrderRetrieveRQ rq) {
        try{

            NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

            accountInfo.setNdcApiInfo(BusinessEnum.NdcApiInfo.ORDER_DETAIL);

            return remote(accountInfo, rq, IATAOrderRetrieveRQ.class, com.ndc.channel.flight.xmlBean.orderDetail.response.bean.IATAOrderViewRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc订单详情接口异常", e);
            return null;
        }
    }

    public com.ndc.channel.flight.xmlBean.orderRefund.response.bean.IATAOrderViewRS refundApply(com.ndc.channel.flight.xmlBean.orderRefund.request.bean.IATAOrderRetrieveRQ rq) {
        try{

            NdcAccountInfoData accountInfo = accountInfoMapper.selectByNdcCode("mu_ndc");

            accountInfo.setNdcApiInfo(BusinessEnum.NdcApiInfo.REFUND_APPLY);

            return remote(accountInfo, rq, com.ndc.channel.flight.xmlBean.orderRefund.request.bean.IATAOrderRetrieveRQ.class, com.ndc.channel.flight.xmlBean.orderRefund.response.bean.IATAOrderViewRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc退票申请接口异常", e);
            return null;
        }
    }

    private <T> T remote(NdcAccountInfoData accountInfo, Object rq, Class<?> reqClazz, Class<T> respClazz) throws Exception{

        JAXBContext jaxbContext = JAXBContext.newInstance(reqClazz);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(rq, stringWriter);

        String url = accountInfo.getApiUrl()+accountInfo.getNdcApiInfo().getApiPath();
        String xmlParams1 = stringWriter.toString();
        ChannelOKHttpService channelOKHttpService = new ChannelOKHttpService();
        Map<String, String> headers = new HashMap<>();
        headers.put("timeStamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        headers.put("requestId", UUID.randomUUID().toString());
        headers.put("merCode", accountInfo.getMerCode());
        headers.put("chnlCode", accountInfo.getChnlCode());
        headers.put("Authorization", accountInfo.getAuthorization());
        headers.put("apiCode", accountInfo.getNdcApiInfo().getApiCode());
        String resp = channelOKHttpService.doPostXml(url, xmlParams1, headers);

        String respLog = resp;
        if (accountInfo.getNdcApiInfo().getApiCode().equals("A0534")) {
            respLog = "内容太大隐藏";
        }
        log.info("url={}, req={}, rep={}", url, xmlParams1, respLog);

        JAXBContext jaxbContext1 = JAXBContext.newInstance(respClazz);
        final Unmarshaller unmarshaller = jaxbContext1.createUnmarshaller();
        final StringReader stringReader = new StringReader(resp);
        T rs = (T)unmarshaller.unmarshal(stringReader);

        return rs;
    }

}
