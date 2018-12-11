package com.github.steven.order.service.impl;

import com.github.steven.account.api.dto.AccountDTO;
import com.github.steven.account.api.entity.AccountDO;
import com.github.steven.inventory.api.dto.InventoryDTO;
import com.github.steven.inventory.api.entity.InventoryDO;
import com.github.steven.order.client.AccountClient;
import com.github.steven.order.client.InventoryClient;
import com.github.steven.order.entity.Order;
import com.github.steven.order.enums.OrderStatusEnum;
import com.github.steven.order.mapper.OrderMapper;
import com.github.steven.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final OrderMapper orderMapper;

    private final AccountClient accountClient;

    private final InventoryClient inventoryClient;

    /**
     * Instantiates a new Payment service.
     *
     * @param orderMapper
     * @param accountClient
     * @param inventoryClient
     */
    @Autowired(required = false)
    public PaymentServiceImpl(OrderMapper orderMapper, AccountClient accountClient, InventoryClient inventoryClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
        this.inventoryClient = inventoryClient;
    }

    @Override
    public void makePayment(Order order) {

        //检查数据 这里只是demo
        final AccountDO accountDO = accountClient.findUserId(order.getUserId());

        if (accountDO.getBalance().compareTo(order.getTotalAmount()) <= 0) {
            LOGGER.error("余额不足");
        }

        final InventoryDO inventoryDO = inventoryClient.findByProductId(order.getProductId());

        if (inventoryDO.getTotalInventory() < order.getCount()) {
            LOGGER.error("库存不足");
        }

        order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
        orderMapper.update(order);
        //扣除用户余额
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());

        accountClient.payment(accountDTO);

        //进入扣减库存操作
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        inventoryClient.decrese(inventoryDTO);
    }
}
