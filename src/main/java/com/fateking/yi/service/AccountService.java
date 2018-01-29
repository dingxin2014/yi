package com.fateking.yi.service;

import com.fateking.yi.dto.Account;
import com.fateking.yi.dto.AccountBalance;

import java.util.List;

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
    List<Account> getAccount();


    /**
     * 获取资产
     *
     * @param accountId
     * @return
     */
    AccountBalance getBalance(Long accountId);
}
