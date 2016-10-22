package com.soho.postCardTailor.pojo.order;


/**
 * Order数据传输类
 */
public class OrderDTO {
    private Integer id;
    private String customerName;
    private String waterMark;
    private String size;
    private String type;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", waterMark='" + waterMark + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                '}';
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
