package com.soho.postCardTailor.business.impl;

import com.soho.postCardTailor.business.IOrderBusiness;
import com.soho.postCardTailor.dao.IOrderDAO;
import com.soho.postCardTailor.pojo.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单业务逻辑
 */
@Service("orderBusiness")
public class OrderBusinessImpl implements IOrderBusiness {
    @Autowired
    IOrderDAO orderDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
    public boolean insert(Order order) {
        return orderDAO.doCreate(order);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order findById(Integer orderId) {
        return orderDAO.findById(orderId);
    }
}
