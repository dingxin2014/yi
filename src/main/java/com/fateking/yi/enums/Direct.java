package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dingxin
 */
@AllArgsConstructor
@Getter
public enum Direct {

    Prev("prev", "向前"),
    Next("next", "向后");

    private String code;
    private String name;

    public boolean in(Direct... directs) {
        if (directs == null) {
            return false;
        }
        for (Direct direct : directs) {
            if (this.equals(direct)) {
                return true;
            }
        }
        return false;
    }
}
