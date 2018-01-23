package com.fateking.yi.dto;

import com.fateking.yi.enums.Symbol;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dingxin
 */
@Data
public class KTick implements Serializable {

    private Symbol symbol;      //交易对
    private Long id;            //K线id
    private BigDecimal open;    //开盘价
    private BigDecimal close;   //收盘价 当K线为最晚的一根时 是最新成交价
    private BigDecimal low;     //最低价
    private BigDecimal high;    //最高价
    private BigDecimal amount;  //成交量
    private BigDecimal vol;     //成交额, 即 sum(每一笔成交价 * 该笔的成交量)
    private Integer count;      //成交笔数

}
