package com.github.steven.account.service.impl;

import com.github.steven.account.mapper.AccountMapper;
import com.github.steven.account.api.dto.AccountDTO;
import com.github.steven.account.api.entity.AccountDO;
import com.github.steven.account.api.service.AccountService;
import com.steven.transaction.annotation.Transaction;
import com.steven.transaction.common.exception.TransactionRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transaction(destination = "account")
    @Transactional(rollbackFor = Exception.class)
    public boolean payment(AccountDTO accountDTO) {
        LOGGER.info("=========springcloud执行付款接口==========");
        final AccountDO accountDo = accountMapper.findByUserId(accountDTO.getUserId());
        if (accountDo.getBalance().compareTo(accountDTO.getAmount()) <= 0) {
            throw new TransactionRuntimeException("springclound account-service 资金不足");
        }
        accountDo.setBalance(accountDo.getBalance().subtract(accountDTO.getAmount()));
        accountDo.setUpdateTime(new Date());
        final int update = accountMapper.update(accountDo);
        if (update != 1) {
            throw new TransactionRuntimeException("springcloud account-service 资金不足");
        }
        return Boolean.TRUE;
    }

    @Override
    public AccountDO findByUserId(String userId) {
        final AccountDO byUserId = accountMapper.findByUserId(userId);
        return byUserId;
    }
}
