package com.njit.xydl.life.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExpressOrder {
    private String id;

    private String username;

    private String kdSize;

    private String kdDescription;

    private String name;

    private Long phone;

    private String pickupCode;

    private String destination;

    private String specialAttention;

    private Date orderDeadlineDate;

    private Date orderDeadlineTime;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    private Boolean del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getKdSize() {
        return kdSize;
    }

    public void setKdSize(String kdSize) {
        this.kdSize = kdSize == null ? null : kdSize.trim();
    }

    public String getKdDescription() {
        return kdDescription;
    }

    public void setKdDescription(String kdDescription) {
        this.kdDescription = kdDescription == null ? null : kdDescription.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode == null ? null : pickupCode.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getSpecialAttention() {
        return specialAttention;
    }

    public void setSpecialAttention(String specialAttention) {
        this.specialAttention = specialAttention == null ? null : specialAttention.trim();
    }

    public Date getOrderDeadlineDate() {
        return orderDeadlineDate;
    }

    public void setOrderDeadlineDate(Date orderDeadlineDate) {
        this.orderDeadlineDate = orderDeadlineDate;
    }

    public Date getOrderDeadlineTime() {
        return orderDeadlineTime;
    }

    public void setOrderDeadlineTime(Date orderDeadlineTime) {
        this.orderDeadlineTime = orderDeadlineTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}