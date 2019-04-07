package com.njit.xydl.atlas.navigation.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AtlasImage {
    private Long id;

    private Long atlasId;

    private String imageUrl;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}