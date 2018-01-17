package com.fateking.yi.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author dingxin
 */
@Data
public class Tick implements Serializable {

    private Long id;            //K线id
    private Long ts;
    private BigDecimal open;    //开盘价
    private BigDecimal close;   //收盘价 当K线为最晚的一根时 是最新成交价
    private BigDecimal low;     //最低价
    private BigDecimal high;    //最高价
    private BigDecimal amount;  //成交量
    private BigDecimal vol;     //成交额, 即 sum(每一笔成交价 * 该笔的成交量)
    private Integer count;      //成交笔数
    private List<BigDecimal> bid;     // [price, amount] 买一价 买一量  买一价:当前最高的委托买进价格;量:在该价格上的委托买进数量.
    private List<BigDecimal> ask;     // [price, amount] 卖一价 卖一量  卖一价:当前最低的委托卖出价格,量同理.

}
