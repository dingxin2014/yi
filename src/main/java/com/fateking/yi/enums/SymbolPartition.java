package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 交易区 main主区，innovation创新区，bifurcation分叉区
 */
@AllArgsConstructor
@Getter
public enum SymbolPartition {

    MAIN("main", "主区"),

    INNOVATION("innovation", "创新区"),

    BIFURCATION("bifurcation", "分叉区");

    private String code;
    private String name;

}
