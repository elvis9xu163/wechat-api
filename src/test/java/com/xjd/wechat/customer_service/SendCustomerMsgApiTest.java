package com.xjd.wechat.customer_service;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import static com.xjd.wechat.TestEnv.accessToken;

/**
 * @author elvis.xu
 * @since 2017-08-15 18:14
 */
public class SendCustomerMsgApiTest {

	@Test
	public void testSendCustomerMsgText() throws Exception {
		final int[] i = {0};
		SendCustomerMsgApi.sendCustomerMsgText(accessToken, "oWTCBwWqXFntRgkfsGDmKgj7KvjY", "老铁，扎心，香菇，蓝瘦！ <a href='http://f.wozai4u.com'>我是链接</a>", new SendCustomerMsgApi.SendCustomerMsgCallback() {
			@Override
			public void onSuccess() {
				i[0] = 1;
			}

			@Override
			public void onFailed(int errCode, String errMsg) {
				System.out.println("errCode: " + errCode);
				System.out.println("errMsg: " + errMsg);
				i[0] = -1;
			}
		});
		Assertions.assertThat(i[0]).isEqualTo(1);
	}
}