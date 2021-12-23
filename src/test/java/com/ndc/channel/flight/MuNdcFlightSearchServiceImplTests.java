package com.ndc.channel.flight;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ndc.channel.flight.handler.NdcFlightSearchHandler;
import com.ndc.channel.flight.xmlBean.flightSearch.request.bean.*;
import com.ndc.channel.flight.xmlBean.flightSearch.response.bean.IATAAirShoppingRS;
import com.ndc.channel.flight.tools.NdcApiTools;

import java.io.FileWriter;
import java.io.IOException;

public class MuNdcFlightSearchServiceImplTests {





    public static void main(String[] args) {


//        final NdcFlightSearchHandler searchHandler = new NdcFlightSearchHandler();
//        searchHandler.flightSearch("2022-03-13", "PEK", "SHA");

        NdcApiTools ndcApiTools = new NdcApiTools();
        IATAAirShoppingRQ flightSearchParams = NdcFlightSearchHandler.getFlightSearchParams("2022-03-13", "PEK", "SHA");
        IATAAirShoppingRS rs = ndcApiTools.flightSearch(flightSearchParams);
        String content = JSON.toJSONString(rs, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        NdcFlightSearchHandler.saveAsFileWriter(content, "/home/dgq/Desktop/rsp.json");
    }

}
