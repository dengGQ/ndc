package com.ndc.channel.flight.tools;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.flight.handler.NdcFlightSearchHandler;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.IATAAirShoppingRQ;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.IATAAirShoppingRS;
import com.ndc.channel.http.ChannelOKHttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public IATAAirShoppingRS flightSearch(IATAAirShoppingRQ rq) {
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(IATAAirShoppingRQ.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(rq, stringWriter);
            String url = "http://code-ceair.srv.test.flybytrip.com/ota/openapi/sandbox/ndc-shopping-common/shopping/basicShopping";
            String xmlParams1 = stringWriter.toString();
            ChannelOKHttpService channelOKHttpService = new ChannelOKHttpService();
            System.out.println(xmlParams1);
            Map<String, String> headers = new HashMap<>();
//            headers.put("Content-Type", "application/xml");
            headers.put("timeStamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            headers.put("requestId", UUID.randomUUID().toString());
            headers.put("merCode", "BOP-2021122034800");
            headers.put("chnlCode", "1462");
            headers.put("Authorization", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJub3JtYWwiXSwiYXV0aG9yaXRpZXMiOlsiMTQ2MiJdLCJqdGkiOiI4OTY1Njk5YS1hMTNmLTQ5Y2UtYmQ5OC1mZDA5MWY0ODMxNWUiLCJjbGllbnRfaWQiOiIxNDYyIn0.fi5LlLjc2SEdil_sfskJasD1Hwr82FT-vogYL2IuTOdl1T8TaE5yAimNZTJOgo1myk-R-5Ipy4cSJekTr_7BbnRQiM_J0Nzx3urQFmc7MSRJnvJLmzvBD5E4ZanzHqAiRfPrC_yyX-CjaXTXW5Cg6tnAEf5ShB-kovP0CCTwGO7WIJByvXRigfI7YOqWPuhcQxUAoBaAYtgit35AcG3daW7x0LPRnGNgBeQTfXQGSpoVU9GYN-2bRlm1ql2L3IxVjtGueOMwxiMkPou0ys9k8rEBEvy5QjwI0SnwYakLognNNqtIYp0ewR4tVjyE0dECwUQWnrpkMuxgLseeuSaCsQ");
            headers.put("apiCode", "A0534");
            String resp = channelOKHttpService.doPostXml(url, xmlParams1, headers);

//            NdcFlightSearchHandler.saveAsFileWriter(resp, "/home/dgq/Desktop/rsp.xml");

            JAXBContext jaxbContext1 = JAXBContext.newInstance(IATAAirShoppingRS.class);
//
            final Unmarshaller unmarshaller = jaxbContext1.createUnmarshaller();
            final StringReader stringReader = new StringReader(resp);
            IATAAirShoppingRS rs = (IATAAirShoppingRS)unmarshaller.unmarshal(stringReader);

            return rs;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            log.error("Ndc机票查询异常", e);
            return null;
        }
    }
}
