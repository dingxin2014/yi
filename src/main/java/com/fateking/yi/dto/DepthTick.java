package com.fateking.yi.dto;

import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DepthTick implements Serializable {

    private Long id;
    private Long ts;
    private List<ImmutablePair<BigDecimal, BigDecimal>> bids;
    private List<ImmutablePair<BigDecimal, BigDecimal>> asks;
}
