package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.Account;
import com.fateking.yi.dto.Balance;
import com.fateking.yi.dto.HuobiStandardResponse;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.utils.HuobiHttpClientUtil;
import com.fateking.yi.utils.SpElUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author dingxin
 */
@Service
@Slf4j
@Retryable
public class AccountServiceImpl implements AccountService {

    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public Account getAccount() {
        return (Account) HuobiHttpClientUtil.get(huobiConfig.getAccounts(), null, HuobiStandardResponse.class).getData();
    }

    @Override
    public Balance getBalance(Long accountId) {
        Map<String, Object> context = Maps.newHashMap();
        context.put("accountId", accountId);
        return (Balance) HuobiHttpClientUtil.get(SpElUtil.parse(huobiConfig.getBalance(), context), null, HuobiStandardResponse.class).getData();
    }
}
