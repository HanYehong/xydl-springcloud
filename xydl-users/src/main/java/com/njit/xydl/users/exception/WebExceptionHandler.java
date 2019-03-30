package com.njit.xydl.users.exception;

import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
@ControllerAdvice(basePackages = {"com.njit.xydl"})
public class WebExceptionHandler {

	private static final String ERROR_MSG = "未知异常";

	@ExceptionHandler(Exception.class)
	@ResponseBody
	Response handleControllerException(HttpServletRequest request, Throwable ex) {

		String errorMsg = ERROR_MSG;

		if (ex instanceof ValidException) {

			errorMsg = ex.getMessage();

		} else if (ex instanceof MethodArgumentNotValidException) {

			MethodArgumentNotValidException validException = (MethodArgumentNotValidException) ex;

			BindingResult result = validException.getBindingResult();

			if (result.hasErrors()) {
				FieldError fieldError = result.getFieldError();
				errorMsg = fieldError.getDefaultMessage();
			}

		}

		return Response.ok(Status.fail, errorMsg);
	}
}
