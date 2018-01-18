package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Direction {

    BUY("buy", ""),
    SELL("sell", "");

    private String code;
    private String name;

    @Override
    public String toString() {
        return code;
    }
}
