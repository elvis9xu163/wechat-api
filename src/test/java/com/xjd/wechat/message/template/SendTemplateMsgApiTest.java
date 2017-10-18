package com.xjd.wechat.message.template;

import java.util.Arrays;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

/**
 * @author elvis.xu
 * @since 2017-10-18 14:59
 */
public class SendTemplateMsgApiTest {
	@Test
	public void sendTemplateMsg() throws Exception {
		final int[] i = {0};
		SendTemplateMsgApi.sendTemplateMsg("3wY3O8oCZPZtShHzWWpetyPFPsbzjQGtFgVYmUoO7zYNykVRjE4Hfe1z67PpIm_uezw_lGHlv0lSMZqC6ZQi7sC-eAP3DsSu8RxLJx69QR8mR-OSOZ27-woTO_vy6d4dBYMcABAKHV",
				"oWTCBwWqXFntRgkfsGDmKgj7KvjY",
				"NLvp7XCKGqBcYsmxTHoUMupy0jGBLOx6A-PLoNE_RFw",
				"http://www.wozai4u.com",
				Arrays.asList(new SendTemplateMsgApi.MsgTemplateParam().setKey("first").setValue("报名成功!").setColor("#173177"),
						new SendTemplateMsgApi.MsgTemplateParam("keynote1", "友爱团结", "#173177"),
						new SendTemplateMsgApi.MsgTemplateParam("keynote2", "见活动详情", "#173177"),
						new SendTemplateMsgApi.MsgTemplateParam("keynote3", "见活动详情", "#173177"),
						new SendTemplateMsgApi.MsgTemplateParam("remark", "感谢您的参与!", "#173177")),
				null, new SendTemplateMsgApi.SendTemplateMsgCallback() {
			@Override
			public void onSuccess(long msgId) {
				System.out.println("msgId: " + msgId);
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