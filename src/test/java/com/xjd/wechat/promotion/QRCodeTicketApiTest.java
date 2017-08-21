package com.xjd.wechat.promotion;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

/**
 * @author elvis.xu
 * @since 2017-08-15 17:21
 */
public class QRCodeTicketApiTest {
	@Test
	public void testGetQRCodeTicket() throws Exception {
		int[] i = {0};
		QRCodeTicketApi.getQRCodeTicket("xxx", true, 60 * 60 * 24 * 10, null, "我是参数&想怎么写就怎么写", new QRCodeTicketApi.QRCodeTicketCallback() {
			@Override
			public void onSuccess(String ticket, int expireInSeconds, String url) {
				System.out.println("ticket: " + ticket);
				System.out.println("expireInSeconds: " + expireInSeconds);
				System.out.println("url: " + url);
				i[0] = 1;
			}

			@Override
			public void onFailure(int errCode, String errMsg) {
				System.out.println("errCode: " + errCode);
				System.out.println("errMsg: " + errMsg);
				i[0] = -1;
			}
		});

		Assertions.assertThat(i[0]).isEqualTo(1);
	}

}