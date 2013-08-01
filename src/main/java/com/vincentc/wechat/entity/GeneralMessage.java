package com.vincentc.wechat.entity;

public class GeneralMessage {
	public static final String MSG_TYPE_TEXT = "text";
	public static final String MSG_TYPE_IMAGE = "image";
	public static final String MSG_TYPE_LOCACTION = "location";
	public static final String MSG_TYPE_LINK = "link";
	public static final String MSG_TYPE_EVENT = "event";
	public static final String MSG_TYPE_MUSIC = "music";
	public static final String MSG_TYPE_NEWS = "news";
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
