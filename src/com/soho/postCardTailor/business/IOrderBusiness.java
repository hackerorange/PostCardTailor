package com.soho.postCardTailor.business;

import com.soho.postCardTailor.pojo.order.Order;

import java.util.List;

/**
 * 订单业务逻辑
 */
public interface IOrderBusiness {
    /**
     * 添加新的订单
     *
     * @param order 要添加的订单
     * @return 是否添加成功
     */
    boolean insert(Order order);

    /**
     * 获取所有订单
     *
     * @return 所有订单
     */
    List<Order> getAll();

    /**
     * 根据订单ID获取订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    Order findById(Integer orderId);
}
