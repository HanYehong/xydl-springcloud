package com.njit.xydl.life.common.feign.exception;

import lombok.Data;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@Data
public class ExceptionInfo{

	private Long timestamp;

	private Integer status;

	private String exception;

	private String message;

	private String path;

	private String error;


}

