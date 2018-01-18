package com.fateking.yi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Symbol implements Serializable {

    @JsonProperty("base-currency")
    private String baseCurrency;
    @JsonProperty("quote-currency")
    private String quoteCurrency;

    private String symbol;

}
