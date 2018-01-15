package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 周期 huobi可选值：{1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year }
 *
 * @author dingxin
 */
@AllArgsConstructor
@Getter
public enum Period {

    _1min("1min", "一分钟"),
    _5min("5min", "五分钟"),
    _30min("30min", "三十分钟"),
    _60min("60min", "六十分钟"),
    _1day("1day", "一天"),
    _1mon("1mon", "一月"),
    _1week("1week", "一周"),
    _1year("1year", "一年");

    private String code;
    private String name;

    public boolean in(Period... periods) {
        if (periods == null) {
            return false;
        }
        for (Period period : periods) {
            if (this.equals(period)) {
                return true;
            }
        }
        return false;
    }
}
