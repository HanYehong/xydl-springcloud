package com.njit.xydl.life.lostfound.entity;

import java.util.Date;
import java.util.List;

public class LostFound {
    private Long id;

    private String lostNumber;

    private Integer lostType;

    private Integer lostLocation;

    private String content;

    private String creator;

    private Date createTime;

    private Integer isDelete;

    private List<LostFoundImage> imageList;

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

    public Integer getLostType() {
        return lostType;
    }

    public void setLostType(Integer lostType) {
        this.lostType = lostType;
    }

    public Integer getLostLocation() {
        return lostLocation;
    }

    public void setLostLocation(Integer lostLocation) {
        this.lostLocation = lostLocation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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

    public List<LostFoundImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<LostFoundImage> imageList) {
        this.imageList = imageList;
    }
}