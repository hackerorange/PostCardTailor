package com.soho.postCardTailor.dao;

import com.soho.postCardTailor.pojo.order.Order;

import java.util.List;

/**
 * Created by HackerOrange on 2016/10/5.
 */
public interface IOrderDAO {
    /**
     * 保存一个新的订单
     *
     * @param order 要保存的订单信息
     * @return 是否保存成功
     */
    boolean doCreate(Order order);

    /**
     * 查找所有订单
     *
     * @return 所有订单
     */
    List<Order> findAll();

    /**
     * 根据ID查找订单
     *
     * @param orderId 订单ID
     * @return 查找到的订单
     */
    Order findById(Integer orderId);
}
