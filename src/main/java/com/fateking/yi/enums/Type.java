package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * { percent10, step0, step1, step2, step3, step4, step5 }
 *
 * @author dingxin
 */
@Getter
@AllArgsConstructor
public enum Type {

    Percent10("percent10", "10%"),
    Step0("step0", ""),
    Step1("step1", ""),
    Step2("step2", ""),
    Step3("step3", ""),
    Step4("step4", ""),
    Step5("step5", "");

    private String code;
    private String name;


    public boolean in(Type... types) {
        if (types == null) {
            return false;
        }
        for (Type type : types) {
            if (this.equals(type)) {
                return true;
            }
        }
        return false;
    }

}
