package com.njit.xydl.life.common.exception;

import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yehong.han
 * @date 2019/4/24
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	/**
	 * 参数校验失败Handler, 如使用 org.springframework.util.Assert 类校验失败的处理
	 *
	 * @param request HttpServletRequest
	 * @param exception catch ConstraintViolationException
	 */
	@ExceptionHandler(value = IllegalArgumentException.class)
	public Response assertFailHandler(HttpServletRequest request,
									  IllegalArgumentException exception) {
		return Response.ok(Status.FAIL, exception.getMessage());
	}

	/**
	 * 所有异常报错 Handler, 兜底
	 *
	 * @param request HttpServletRequest
	 * @param exception Exception
	 * @throws Exception 错误
	 */
	@ExceptionHandler(value = Exception.class)
	public Response allExceptionHandler(HttpServletRequest request, Exception exception) 		{
		exception.printStackTrace();
		return Response.ok(Status.FAIL, exception.getMessage());
	}

}