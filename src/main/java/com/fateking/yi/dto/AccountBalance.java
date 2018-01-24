package com.fateking.yi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountBalance implements Serializable {

    private Long id;
    private String type;  //spot：现货账户
    private String state; //账户状态 working：正常, lock：账户被锁定
    private List<Balance> list;
    @JsonProperty("user-id")
    private Long userId;
}
