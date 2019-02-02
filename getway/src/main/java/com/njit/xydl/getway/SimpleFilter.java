package com.njit.xydl.getway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class SimpleFilter extends ZuulFilter {
    /**
     * pre
     * 路由请求前执行。
     * route
     * 处理实际的路由请求。
     * post
     * 在请求路由完成后执行。
     * error
     * 处理请求期间出现错误执行。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否执行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器功能
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获得当前上下文，存放请求和状态信息
        RequestContext ctx = RequestContext.getCurrentContext() ;
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        String url = request.getRequestURL().toString() ;
        System.out.printf("%s request to %s\r\n" , method , url);
        return null;
    }
}
