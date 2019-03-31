package com.njit.xydl.zuul.interceptor;

import com.netflix.zuul.exception.ZuulException;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HanYehong
 * @date 2019/3/31 21:17
 */
@RestController
public class ErrorHandler implements ErrorController {

    /**
     * zuul的异常处理
     *
     * @param request HTTP请求
     * @return API统一响应
     */
    @RequestMapping("/error")
    public Response error(HttpServletRequest request, HttpServletResponse response) {

        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        String message = "系统繁忙，请稍后再试。";

        if (exception instanceof ZuulException) {
            message = exception.getMessage();
        }

        response.setStatus(HttpStatus.OK.value());

        return Response.ok(Status.FAIL, message);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
