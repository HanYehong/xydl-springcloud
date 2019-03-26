package com.njit.xydl.users.util;

import java.security.MessageDigest;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
public class MD5Util {

	private static char hexDigists[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d', 'e','f'};

	public final static String encrypt(String mdStr){

		// 如果为空，则返回""
		String s = mdStr == null ? "" : mdStr;

		try {
			// 获取二进制
			byte[] strTemp = s.getBytes();

			MessageDigest mdTemp = MessageDigest.getInstance("MD5");

			// 执行加密
			mdTemp.update(strTemp);

			// 加密结果
			byte[] md = mdTemp.digest();

			// 字符数组
			char[] str = new char[md.length * 2];

			// 将二进制加密结果转化为字符
			int k = 0;
			for (int i = 0; i < md.length; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigists[byte0 >>> 4 &0xf];
				str[k++] = hexDigists[byte0 & 0xf];
			}

			return new String(str);

		} catch (Exception e) {
			return null;
		}

	}

}
