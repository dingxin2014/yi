package com.fateking.yi.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TradeDetail implements Serializable {

    private Long id;
    private BigDecimal price;
    private BigDecimal amount;
    private String direction;
    private Long ts;

}
