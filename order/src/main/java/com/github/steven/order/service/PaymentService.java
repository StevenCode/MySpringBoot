package com.github.steven.order.service;

import com.github.steven.order.entity.Order;

public interface PaymentService {

    /**
     *  订单支付
     *
     * @param order
     */
    void makePayment(Order order);
}
