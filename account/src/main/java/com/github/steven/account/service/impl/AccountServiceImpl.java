package com.github.steven.account.service.impl;

import com.github.steven.account.mapper.AccountMapper;
import com.github.steven.accountapi.dto.AccountDTO;
import com.github.steven.accountapi.entity.AccountDO;
import com.github.steven.accountapi.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service( "accountService")
public class AccountServiceImpl implements AccountService {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    @Override
    public boolean payment(AccountDTO accountDTO) {
        LOGGER.info("=========springcloud执行付款接口==========");
        final AccountDO accountDo = accountMapper.findByUserId(accountDTO.getUserId());
        if (accountDo.getBalance().compareTo(accountDTO.getAmount()) <= 0) {
            LOGGER.error("springclound account-service 资金不足");
        }
        accountDo.setBalance(accountDo.getBalance().subtract(accountDTO.getAmount()));
        accountDo.setUpdateTime(new Date());
        final int update = accountMapper.update(accountDo);
        if (update != 1) {
            LOGGER.error("springcloud account-service 资金不足");
        }
        return Boolean.TRUE;
    }

    @Override
    public AccountDO findByUserId(String userId) {
        final AccountDO byUserId = accountMapper.findByUserId(userId);
        return byUserId;
    }
}
