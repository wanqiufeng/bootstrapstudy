package com.vincentc.wechat.action.handle;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;
import com.vincentc.wechat.action.BaseAction;
import com.vincentc.wechat.entity.GeneralMessage;
import com.vincentc.wechat.entity.IncomeText;
import com.vincentc.wechat.entity.SendText;
import com.vincentc.wechat.utils.MessageUtils;

public class HandleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String TOKEN = "wanqiufeng";
	private InputStream inputStream;

	private static Logger logger = Logger.getLogger(HandleAction.class);

	public String execute() {
		try {
			if (isGetRequest()) {
				siteConnect();
			} else {
				responseMsg();
			}
			return ActionSupport.SUCCESS;
		} catch (Exception e) {
			logger.error(e, e);
			return ActionSupport.ERROR;
		}
	}

	private void responseMsg() throws IOException {
		String msg = getMsg();
		logger.error("incomeMsg:" + msg);
		if (MessageUtils.getMsgType(msg).equals(GeneralMessage.MSG_TYPE_TEXT)) {
			IncomeText text = MessageUtils.getIncomeMsg(msg, IncomeText.class);
			logger.error("sendMsg:" + getSendMsg(text));
			inputStream = new ByteArrayInputStream(getSendMsg(text).getBytes(
					"UTF-8"));
		}
	}

	private String getSendMsg(IncomeText text) {
		SendText sendText = new SendText();
		sendText.setFromUserName(text.getToUserName());
		sendText.setToUserName(text.getFromUserName());
		sendText.setContent("hahahahxxxxx");
		sendText.setCreateTime("324343434");
		sendText.setMsgType(GeneralMessage.MSG_TYPE_TEXT);
		return MessageUtils.getSendMsg(sendText);
	}

	private String getMsg() throws IOException {
		BufferedReader reader = this.getRequest().getReader();
		StringBuffer sb = new StringBuffer();
		String str = reader.readLine();
		for (; null != str; str = reader.readLine()) {
			sb.append(str);
		}
		reader.close();
		return sb.toString();
	}

	private void siteConnect() throws UnsupportedEncodingException {
		String echostr = this.getRequest().getParameter("echostr");
		inputStream = new ByteArrayInputStream(echostr.getBytes("UTF-8"));
	}

	private boolean isGetRequest() {
		return this.getRequest().getMethod().equals(GET);
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
