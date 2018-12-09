package com.github.steven.account.api.service;

import com.github.steven.account.api.dto.AccountDTO;
import com.github.steven.account.api.entity.AccountDO;

public interface AccountService {
    boolean payment(AccountDTO accountDTO);

    AccountDO findByUserId(String userId);
}
