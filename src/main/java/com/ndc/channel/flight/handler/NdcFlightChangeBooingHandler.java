package com.ndc.channel.flight.handler;

import com.ndc.channel.exception.BusinessException;
import com.ndc.channel.exception.BusinessExceptionCode;
import com.ndc.channel.flight.dto.changeBooking.ChangeBookingReqParams;
import com.ndc.channel.flight.dto.changeBooking.ChangeBookingRespData;
import com.ndc.channel.flight.tools.NdcApiTools;
import com.ndc.channel.flight.xmlBean.changeBooking.request.bean.IATAOrderChangeRQ;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.Error;
import com.ndc.channel.flight.xmlBean.changeBooking.response.bean.IATAOrderViewRS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class NdcFlightChangeBooingHandler {

    @Resource
    private NdcApiTools ndcApiTools;

    public ChangeBookingRespData changeBooking(ChangeBookingReqParams params) {
        ChangeBookingRespData respData = new ChangeBookingRespData();

        final IATAOrderViewRS viewRS = ndcApiTools.changeBooking(createIATAOrderChangeRQ(params));

        final Error error = viewRS.getError();
        if (error != null) {
            throw new BusinessException(BusinessExceptionCode.REQUEST_PARAM_ERROR, error.getError().getDescText());
        }

        return respData;
    }

    private IATAOrderChangeRQ createIATAOrderChangeRQ(ChangeBookingReqParams params) {

        IATAOrderChangeRQ rs = new IATAOrderChangeRQ();

        return rs;
    }
}
