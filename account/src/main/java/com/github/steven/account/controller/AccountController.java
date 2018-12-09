package com.github.steven.account.controller;

import com.github.steven.account.api.dto.AccountDTO;
import com.github.steven.account.api.entity.AccountDO;
import com.github.steven.account.api.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/payment")
    @ApiOperation(value = "支付")
    public Boolean save(@RequestBody AccountDTO accountDTO) {
        return accountService.payment(accountDTO);
    }

    @RequestMapping("/findByUserId")
    @ApiOperation(value = "根据Id查询用户")
    public AccountDO findByUserId(@RequestParam("userId") String userId) {
        return accountService.findByUserId(userId);
    }
}
