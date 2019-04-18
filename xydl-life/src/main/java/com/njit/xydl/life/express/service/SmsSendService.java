package com.njit.xydl.life.express.service;

import java.io.IOException;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
public interface SmsSendService {

	void sendForRefuse(String phone) throws IOException;

	void sendForAccept(String phone, String orderNumber) throws IOException;
}
