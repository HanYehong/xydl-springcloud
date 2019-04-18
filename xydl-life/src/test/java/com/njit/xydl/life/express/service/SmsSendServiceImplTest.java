package com.njit.xydl.life.express.service;

import com.njit.xydl.life.express.service.impl.SmsSendServiceImpl;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
public class SmsSendServiceImplTest {

	@Test
	public void sendForRefuse() throws IOException {
		String phone = "15189809881";

		new SmsSendServiceImpl().sendForRefuse(phone);
	}
}
