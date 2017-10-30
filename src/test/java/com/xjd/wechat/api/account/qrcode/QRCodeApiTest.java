package com.xjd.wechat.api.account.qrcode;

import java.util.Base64;

import org.junit.Test;

import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.api.account.qrcode.QRCodeApi;
import com.xjd.wechat.api.account.qrcode.QRCodeTicket;

/**
 * @author elvis.xu
 * @since 2017-10-30 16:03
 */
public class QRCodeApiTest {
	@Test
	public void getQRCodeTicket() throws Exception {
		QRCodeTicket entity = QRCodeApi.getQRCodeTicket("SF1MTsqa_mPE2dMX-MdUUtFk52ulmHddI9xDDRGXlXV_XjEfCZpROq5Bg-t6QrOhXNph9yF2XwyJJKaOow209fL7STJiumhvB_1r7ZQYjVQyJWsosoFlU2lkfqQOcJNFOPOcAFACDC",
				true, 3600, null, "org=10");
		System.out.println(ApiUtils.toJson(entity));
	}

	@Test
	public void getQRCodeImage() throws Exception {
		byte[] bytes = QRCodeApi.getQRCodeImage("gQH37zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOWtyVTV3cE1lMTExWkhIU2hwY0kAAgRb3fZZAwQQDgAA");
		System.out.println(Base64.getEncoder().encodeToString(bytes));
	}
}