package com.njit.xydl.life.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Express {
    private Long id;

    private String order_number;

    private String publishor;

    private Integer size;

    private String description;

    private String name;

    private String phone;

    private String pickup_code;

    private String destination;

    private String special_attention;

    private Date order_deadline_date;

    private Double price;

    private String acceptor;

    private Integer status;

    private Date accept_time;

    private Date create_time;

    private Date update_time;

    private Integer is_delete;
}