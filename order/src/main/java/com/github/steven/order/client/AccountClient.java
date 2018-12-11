package com.github.steven.order.client;

import com.github.steven.account.api.dto.AccountDTO;
import com.github.steven.account.api.entity.AccountDO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account-service")
public interface AccountClient {

    /**
     * 用户账户付款
     *
     * @param accountDTO 实体类
     * @return true 成功
     */
    @PostMapping("account-service/account/payment")
    Boolean payment(@RequestBody AccountDTO accountDTO);

    /**
     * 获取账号信息
     *
     * @param userId 用户id
     * @return AccountDo
     */
    @PostMapping("account-service/account/findByUserId")
    AccountDO findUserId(@RequestParam("userId") String userId);
}
