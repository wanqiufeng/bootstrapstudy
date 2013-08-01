package com.vincentc.wechat.utils;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.XStream;

public class MessageUtils {
	public static <T> T getIncomeMsg(String msg, Class<T> type) {
		XStream xstream = new XStream();
		xstream.alias("xml", type);
		T obj = (T) xstream.fromXML(msg);
		return obj;
	}

	public static String getSendMsg(Object o) {
		XStream xstream = new XStream();
		xstream.alias("xml", o.getClass());
		return xstream.toXML(o);
	}

	public static String getMsgType(String msg) {
		return StringUtils.substringBetween(msg, "<MsgType>", "</MsgType>");
	}
}
