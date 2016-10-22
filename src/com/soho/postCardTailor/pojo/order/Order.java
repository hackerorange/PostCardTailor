package com.soho.postCardTailor.pojo.order;

import com.soho.postCardTailor.exception.SizeFormatWrongException;
import com.soho.postCardTailor.pojo.Operator;
import com.soho.postCardTailor.pojo.graphics.Size;

/**
 * 订单实体类
 */
public class Order {
    private Integer id;
    private String customerName;
    private String waterMark;
    private Size size;
    private String type;
    private Operator operator;

    public Order() {
    }

    public Order(Integer id, String customerName, String waterMark, Size size, String type, Operator operator) {
        this.id = id;
        this.customerName = customerName;
        this.waterMark = waterMark;
        this.size = size;
        this.type = type;
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getWaterMark() {
        return waterMark;
    }

    public void setWaterMark(String waterMark) {
        this.waterMark = waterMark;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", waterMark='" + waterMark + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}
