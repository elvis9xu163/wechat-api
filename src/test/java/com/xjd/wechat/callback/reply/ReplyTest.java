package com.xjd.wechat.callback.reply;

import org.junit.Test;

/**
 * @author elvis.xu
 * @since 2017-10-31 14:04
 */
public class ReplyTest {

	@Test
	public void test() throws Exception {
		{
			ReplyText reply = Replys.newReply(ReplyText.class, "gh_e75ca3b7b1a4", "oWTCBwWqXFntRgkfsGDmKgj7KvjY");
			reply.setContent("您好啊!");
			System.out.println(reply);
		}
		{
			ReplyImage reply = Replys.newReply(ReplyImage.class, "gh_e75ca3b7b1a4", "oWTCBwWqXFntRgkfsGDmKgj7KvjY");
			reply.setMediaId("B8v4drly7teZLAbTQ6FMX-bqMbszRDZO-E2c9vbpbxQjXlUzs3Un2pTOOm59DzYY");
			System.out.println(reply);
		}
		{
			ReplyVoice reply = Replys.newReply(ReplyVoice.class, "gh_e75ca3b7b1a4", "oWTCBwWqXFntRgkfsGDmKgj7KvjY");
			reply.setMediaId("B8v4drly7teZLAbTQ6FMX-bqMbszRDZO-E2c9vbpbxQjXlUzs3Un2pTOOm59DzYY");
			System.out.println(reply);
		}

	}
}