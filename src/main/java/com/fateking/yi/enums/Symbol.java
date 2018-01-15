package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 币种标志
 *
 * @author dingxin
 */
@AllArgsConstructor
@Getter
public enum Symbol {

    XRP("xrp", "瑞波币");

    private String code;
    private String name;

    public boolean in(Symbol... symbols) {
        if (symbols == null) {
            return false;
        }
        for (Symbol symbol : symbols) {
            if (this.equals(symbol)) {
                return true;
            }
        }
        return false;
    }
}
