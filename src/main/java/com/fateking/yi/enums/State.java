package com.fateking.yi.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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

    /**
     * 状态是否进行中
     *
     * @return
     */
    public boolean isInProgress() {
        return in(PreSubmitted, Submitted, PartialFilled);
    }

    public static final List<State> inProgressStates() {
        return Lists.newArrayList(PreSubmitted, Submitted, PartialFilled);
    }

    public static final List<State> notInProgressStates() {
        return Lists.newArrayList(PartialCanceled, Filled, Canceled);
    }

    /**
     * 状态不在进行中 正常/非正常 结束单据
     *
     * @return
     */
    public boolean notInProgress() {
        return !isInProgress();
    }

    @Override
    public String toString() {
        return code;
    }

}
