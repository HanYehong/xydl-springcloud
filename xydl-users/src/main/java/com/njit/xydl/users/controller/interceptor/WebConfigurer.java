package com.njit.xydl.users.controller.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

	@Autowired
	private CheckTokenInterceptor checkTokenInterceptor;

	/**
	 * 这个方法是用来配置静态资源的，比如html，js，css，等等
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

	/**
	 * 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截除了/login/getSession之外的所有请求
		registry.addInterceptor(checkTokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login/getToken");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}