package com.fateking.yi.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DepthTick implements Serializable {

    private Long id;
    private Long ts;
    private List<List<BigDecimal>> bids;
    private List<List<BigDecimal>> asks;
}
