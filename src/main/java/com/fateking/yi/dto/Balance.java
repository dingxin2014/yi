package com.fateking.yi.dto;

import lombok.Data;

/**
 * 资产
 *
 * @author dingxin
 */
@Data
public class Balance {

    private String balance;     //🈷️余额
    private String currency;    //币种
    private String type;        //trade: 交易余额，frozen: 冻结余额

}
