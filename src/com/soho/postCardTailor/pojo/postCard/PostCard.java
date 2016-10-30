package com.soho.postCardTailor.pojo.postCard;

import com.soho.postCardTailor.pojo.order.Order;

import java.util.Date;

/**
 * 明信片基本信息类(图片位置相关)
 */
public class PostCard {
    private Integer id;
    private Order order;
    private String filePath;
    private String thumbPath;
    private String fileName;
    private Date createTime;
    private Integer stateId;

    public PostCard() {

    }

    public PostCard(Integer id, Order order, String filePath, String thumbPath, String fileName, Date createTime, Integer stateId) {
        this.id = id;
        this.order = order;
        this.filePath = filePath;
        this.thumbPath = thumbPath;
        this.fileName = fileName;
        this.createTime = createTime;
        this.stateId = stateId;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PostCard{" +
                "id=" + id +
                ", order=" + order +
                ", filePath='" + filePath + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", createTime=" + createTime +
                ", stateId=" + stateId +
                '}';
    }
}
