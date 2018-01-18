package com.fateking.yi.po;

import lombok.Data;

//@Entity
@Data
public class AccountPO {

    private Long id;      //account-id
    private String type;  //spot：现货账户
    private String state; //账户状态 working：正常, lock：账户被锁定
    private String userId; //账户Id

}
