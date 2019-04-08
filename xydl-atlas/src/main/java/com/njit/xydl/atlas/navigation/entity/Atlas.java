package com.njit.xydl.atlas.navigation.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Atlas {
    private Long id;

    private String title;

    private String description;

    private String logoUrl;

    private Double longitude;

    private Double latitude;

    private Integer category;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}