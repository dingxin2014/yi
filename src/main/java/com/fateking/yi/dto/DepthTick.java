package com.fateking.yi.dto;

import com.fateking.yi.enums.Symbol;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DepthTick implements Serializable {

    private Symbol symbol;
    private Long id;
    private Long ts;
    private List<List<BigDecimal>> bids;   // [price, amount] 买一价 买一量  买一价:当前最高的委托买进价格;量:在该价格上的委托买进数量.
    private List<List<BigDecimal>> asks;   // [price, amount] 卖一价 卖一量  卖一价:当前最低的委托卖出价格,量同理.
}


