package com.fateking.yi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 账号
 *
 * @author dingxin
 */
@Data
public class Account {

    private Long id;      //account-id
    private String type;  //spot：现货账户
    private String state; //账户状态 working：正常, lock：账户被锁定
    @JsonProperty("user-id")
    private String userId; //账户Id

}
