package com.njit.xydl.life.lostfound.entity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class LostFoundImage {
    private Long id;

    private String lostNumber;

    @NotBlank(message = "图片url必填")
    private String imageUrl;

    private Date createTime;

    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLostNumber() {
        return lostNumber;
    }

    public void setLostNumber(String lostNumber) {
        this.lostNumber = lostNumber == null ? null : lostNumber.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}