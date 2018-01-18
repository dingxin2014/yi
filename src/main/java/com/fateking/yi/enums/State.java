package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 委托单状态
 *
 * @author dingxin
 */
@AllArgsConstructor
@Getter
public enum State {

    PreSubmitted("pre-submitted", "准备提交"),
    Submitted("submitted", "已提交"),
    PartialFilled("partial-filled", "部分成交"),
    PartialCanceled("partial-canceled", "部分成交撤销"),
    Filled("filled", "完全成交"),
    Canceled("canceled", "已撤销");


    private String code;
    private String name;

    public static State parse(String code) {
        for (State s : values()) {
            if (s.code.equals(code)) {
                return s;
            }
        }
        return null;
    }

    public boolean in(State... states) {
        if (states == null) {
            return false;
        }
        for (State state : states) {
            if (this.equals(state)) {
                return true;
            }
        }
        return false;
    }
}
