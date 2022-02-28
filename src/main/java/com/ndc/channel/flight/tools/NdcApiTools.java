package com.ndc.channel.flight.tools;

import com.ndc.channel.enumtype.BusinessEnum;
import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.flight.xmlBean.createOrder.request.bean.IATAOrderCreateRQ;
import com.ndc.channel.flight.xmlBean.createOrder.response.bean.IATAOrderViewRS;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.IATAAirShoppingRQ;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.IATAAirShoppingRS;
import com.ndc.channel.flight.xmlBean.orderPay.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.verifyPrice.request.bean.IATAOfferPriceRQ;
import com.ndc.channel.flight.xmlBean.verifyPrice.response.bean.IATAOfferPriceRS;
import com.ndc.channel.http.ChannelOKHttpService;
import com.ndc.channel.model.NdcAccountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class NdcApiTools {

    /**
     * 航班查询
     * @param rq
     * @return
     */
    public IATAAirShoppingRS flightSearch(IATAAirShoppingRQ rq) {
        try{

            final NdcAccountInfo accountInfo = new NdcAccountInfo(BusinessEnum.NdcApiInfo.FLIGHT_SEARCH);

            return remote(accountInfo, rq, IATAAirShoppingRQ.class, IATAAirShoppingRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("Ndc机票查询异常", e);
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
            final NdcAccountInfo accountInfo = new NdcAccountInfo(BusinessEnum.NdcApiInfo.OFFER_PRICE);

            return remote(accountInfo, rq, IATAOfferPriceRQ.class, IATAOfferPriceRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc接口异常", e);
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
            final NdcAccountInfo accountInfo = new NdcAccountInfo(BusinessEnum.NdcApiInfo.CREATE_ORDER);

            return remote(accountInfo, rq, IATAOrderCreateRQ.class, IATAOrderViewRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc接口异常", e);
            return null;
        }
    }

    public com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS orderPay(IATAOrderChangeRQ rq) {
        try{
            final NdcAccountInfo accountInfo = new NdcAccountInfo(BusinessEnum.NdcApiInfo.CREATE_ORDER);

            return remote(accountInfo, rq, IATAOrderChangeRQ.class, com.ndc.channel.flight.xmlBean.orderPay.response.bean.IATAOrderViewRS.class);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("东方航空Ndc接口异常", e);
            return null;
        }
    }

    private <T> T remote(NdcAccountInfo accountInfo, Object rq, Class<?> reqClazz, Class<T> respClazz) throws Exception{

        JAXBContext jaxbContext = JAXBContext.newInstance(reqClazz);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(rq, stringWriter);

        String url = "http://code-ceair.srv.test.flybytrip.com/ota/openapi/sandbox"+accountInfo.getPath();
        String xmlParams1 = stringWriter.toString();
        ChannelOKHttpService channelOKHttpService = new ChannelOKHttpService();
        Map<String, String> headers = new HashMap<>();
        headers.put("timeStamp", accountInfo.getTimeStamp());
        headers.put("requestId", accountInfo.getRequestId());
        headers.put("merCode", "BOP-2021122034800");
        headers.put("chnlCode", "1462");
        headers.put("Authorization", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJub3JtYWwiXSwiYXV0aG9yaXRpZXMiOlsiMTQ2MiJdLCJqdGkiOiI4OTY1Njk5YS1hMTNmLTQ5Y2UtYmQ5OC1mZDA5MWY0ODMxNWUiLCJjbGllbnRfaWQiOiIxNDYyIn0.fi5LlLjc2SEdil_sfskJasD1Hwr82FT-vogYL2IuTOdl1T8TaE5yAimNZTJOgo1myk-R-5Ipy4cSJekTr_7BbnRQiM_J0Nzx3urQFmc7MSRJnvJLmzvBD5E4ZanzHqAiRfPrC_yyX-CjaXTXW5Cg6tnAEf5ShB-kovP0CCTwGO7WIJByvXRigfI7YOqWPuhcQxUAoBaAYtgit35AcG3daW7x0LPRnGNgBeQTfXQGSpoVU9GYN-2bRlm1ql2L3IxVjtGueOMwxiMkPou0ys9k8rEBEvy5QjwI0SnwYakLognNNqtIYp0ewR4tVjyE0dECwUQWnrpkMuxgLseeuSaCsQ");
        headers.put("apiCode", accountInfo.getApiCode());
        String resp = channelOKHttpService.doPostXml(url, xmlParams1, headers);

        String respLog = resp;
        if (accountInfo.getApiCode().equals("A0534")) {
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
