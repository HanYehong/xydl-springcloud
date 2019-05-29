package com.njit.xydl.life.express.service.impl;

import com.njit.xydl.life.express.service.SmsSendService;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {

	private static final String UID = "yeammhan";

	private static final String KEY = "d41d8cd98f00b204e980";

	private static final String URL = "http://gbk.api.smschinese.cn/";

	@Override
	public void sendForRefuse(String phone) throws IOException {
		String content = "同学，很抱歉，您的接单没有征得发布者同意~不要灰心，再接再厉吧。";
		sendBody(phone, content);
	}

	@Override
	public void sendForAccept(String phone, String orderNumber) throws IOException {
		String content = "hi~同学，你的订单" + orderNumber + "已经被接单啦~快去看看吧，需要授权之后才可以代领~";
		sendBody(phone, content);
	}

	private void sendBody(String phone, String content) throws IOException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(URL);
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
		NameValuePair[] data = {
				new NameValuePair("Uid", UID),
				new NameValuePair("Key", KEY),
				new NameValuePair("smsMob", phone),
				new NameValuePair("smsText", content) };
		post.setRequestBody(data);
		client.executeMethod(post);
		post.releaseConnection();
	}
}
