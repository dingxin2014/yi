package com.fateking.yi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HistoryTradeData implements Serializable {

    private Long id;
    private Long ts;
    private List<TradeDetail> data;

}
