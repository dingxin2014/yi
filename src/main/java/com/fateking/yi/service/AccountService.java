package com.fateking.yi.service;

import com.fateking.yi.dto.Account;
import com.fateking.yi.dto.Balance;

/**
 * 获取账号信息
 *
 * @author dingxin
 */
public interface AccountService {

    /**
     * 获取账号信息
     *
     * @return
     */
    Account getAccount();


    /**
     * 获取资产
     *
     * @return
     */
    Balance getBalance();
}
