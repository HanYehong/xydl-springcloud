package com.njit.xydl.life.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Long id;

    private String content;

    private String contactWay;

    private String commitor;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}