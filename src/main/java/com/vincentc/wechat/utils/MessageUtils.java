package com.vincentc.wechat.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.vincentc.wechat.entity.GeneralMessage;

public class MessageUtils {
	public static <T> T getIncomeMsg(String msg, Class<T> type) {
		XStream xstream = new XStream();
		xstream.alias("xml", type);
		T obj = (T) xstream.fromXML(msg);
		return obj;
	}

	public static String getSendMsgStr(Object o) {
		XStream xstream = new XStream();
		xstream.alias("xml", o.getClass());
		return xstream.toXML(o);
	}

	public static String getMsgType(String msg) {
		String cdataStr = StringUtils.substringBetween(msg, "<MsgType>",
				"</MsgType>");
		return StringUtils.substringBetween(cdataStr, "<![CDATA[", "]]>");
	}

	public static String getCreateTimeStr() {
		Date d = new Date();
		return new String().valueOf(d.getTime());
	}

	public static <T extends GeneralMessage> T getBaseSendMsg(
			GeneralMessage incomeMsg, Class<T> sendMsgType) {
		T sendMsg = (T) new GeneralMessage();
		sendMsg.setFromUserName(incomeMsg.getToUserName());
		sendMsg.setToUserName(incomeMsg.getFromUserName());
		sendMsg.setCreateTime(getCreateTimeStr());
		String msgType = StringUtils.substringAfter(sendMsgType.getName(),
				"Send");
		sendMsg.setMsgType(StringUtils.lowerCase(msgType));
		return sendMsg;
	}
}
