package com.fateking.yi.main.strategy;

import com.fateking.yi.support.KTickStack;

public interface Strategy {

    /**
     * 解析
     *
     * @param stack
     */
    void parse(KTickStack stack);

}
