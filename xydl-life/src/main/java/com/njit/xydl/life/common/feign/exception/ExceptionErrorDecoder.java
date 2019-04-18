package com.njit.xydl.life.common.feign.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
//@Slf4j
//@Configuration
public class ExceptionErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		try {
			Exception exception = null;
			if (response.body() != null) {
				ExceptionInfo exceptionInfo = JSON.parseObject(Util.toString(response.body().asReader()), new TypeReference<ExceptionInfo>() {
				});
//				Class clazz = ValidException.class;
				if (400 <= response.status() || response.status() <= 600){
					exception = new HystrixBadRequestException(exceptionInfo.getMessage(), exception);
				}
//				return (Exception) clazz.getDeclaredConstructor(String.class).newInstance(exceptionInfo.getMessage());
				return exception;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FeignException.errorStatus(methodKey, response);
	}
}
