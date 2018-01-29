package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.Account;
import com.fateking.yi.dto.AccountBalance;
import com.fateking.yi.dto.HuobiAccountBalanceResponse;
import com.fateking.yi.dto.HuobiAccountResponse;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.support.HuobiHttpClient;
import com.fateking.yi.utils.SpElUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author dingxin
 */
@Service
@Slf4j
@Retryable
public class AccountServiceImpl implements AccountService {

    @Autowired
    HuobiHttpClient huobiHttpClient;
    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public List<Account> getAccount() {
        HuobiAccountResponse response = huobiHttpClient.get(huobiConfig.getAccounts(), null, HuobiAccountResponse.class);
        if (response == null) {
            return null;
        }
        List<Account> accounts = response.getData();
        if (CollectionUtils.isEmpty(accounts)) {
            return null;
        }
        return accounts;
    }

    @Override
    public AccountBalance getBalance(Long accountId) {
        Map<String, Object> context = Maps.newHashMap();
        context.put("accountId", accountId);
        HuobiAccountBalanceResponse response = huobiHttpClient.get(SpElUtil.parse(huobiConfig.getBalance(), context), null, HuobiAccountBalanceResponse.class);
        if (response == null) {
            return null;
        }
        return response.getData();
    }
}
