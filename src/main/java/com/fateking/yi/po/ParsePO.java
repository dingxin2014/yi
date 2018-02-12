package com.fateking.yi.po;

import com.fateking.yi.enums.Symbol;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "T_YI_PARSE")
@Data
public class ParsePO {

    @Enumerated(EnumType.STRING)
    private Symbol symbol;              //交易对

    @Id
    @GeneratedValue
    private Long id;                    //订单ID
    private Long timestamp;
    private BigDecimal variance;        //方差

}
