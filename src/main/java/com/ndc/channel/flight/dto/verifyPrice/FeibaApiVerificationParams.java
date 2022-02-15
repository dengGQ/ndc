package com.ndc.channel.flight.dto.verifyPrice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author chencuixia
 * @Description:
 * @date: Created on 2021/3/22
 */
@Data
public class FeibaApiVerificationParams {

    @ApiModelProperty(value = "航班id", required = true)
    private String flightId;

    @ApiModelProperty(value = "机票id", required = true)
    private String ticketId;

}
