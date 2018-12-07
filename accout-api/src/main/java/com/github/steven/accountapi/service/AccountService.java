package com.github.steven.accountapi.service;

import com.github.steven.accountapi.dto.AccountDTO;
import com.github.steven.accountapi.entity.AccountDO;

public interface AccountService {
    boolean payment(AccountDTO accountDTO);

    AccountDO findByUserId(String userId);
}
