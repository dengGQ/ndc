package com.ndc.channel.flight.dto.verifyPrice;

import lombok.Data;

/**
 * @Author chencuixia
 * @Description:
 * @date: Created on 2021/3/19
 */
@Data
public class FeiBaApiVerifyPriceReq {

    /**
     * 验价参数 不能为空
     */
    private FeibaApiVerificationParams priceVerificationParams;

}
