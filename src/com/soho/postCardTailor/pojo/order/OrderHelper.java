package com.soho.postCardTailor.pojo.order;

import com.soho.postCardTailor.exception.SizeFormatWrongException;
import com.soho.postCardTailor.pojo.graphics.Size;

/**
 * 实现DTO与POJO之间的转换
 */
public class OrderHelper {
    /**
     * 从Order转化为OrderDTO
     *
     * @param order Order实体类
     * @return Order数据传输类
     */
    public static OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setSize(order.getSize().toString());
        orderDTO.setWaterMark(order.getWaterMark());
        orderDTO.setType(order.getType());
        return orderDTO;
    }

    public static Order fromOrderDTO(OrderDTO orderDTO) throws SizeFormatWrongException {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setType(orderDTO.getType());
        order.setWaterMark(orderDTO.getWaterMark());
        order.setSize(Size.parseSize(orderDTO.getSize()));
        return order;
    }
}
