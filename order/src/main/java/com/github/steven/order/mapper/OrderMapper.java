package com.github.steven.order.mapper;

import com.github.steven.order.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderMapper {
    /**
     * 保存订单
     *
     * @param order 订单对象
     * @return
     */
    @Insert(" insert into `order` (create_time,number,status,product_id,total_amount,count,user_id) " +
            " values ( #{createTime},#{number},#{status},#{productId},#{totalAmount},#{count},#{userId})")
    int save(Order order);

    /**
     * 更新订单
     *
     * @param order 订单对象
     * @return
     */
    @Update("update `order` set status = #{status} , total_amount=#{totalAmount} where number=#{number}")
    int update(Order order);

    /**
     * 获取所有的订单
     *
     * @return List<Order>
     */
    @Select("select * from  order")
    List<Order> listAll();
}
