package com.vincentc.wechat.action.handle;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;
import com.vincentc.wechat.action.BaseAction;
import com.vincentc.wechat.entity.GeneralMessage;
import com.vincentc.wechat.entity.IncomeEvent;
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
		String msg = getIncodeMsg();
		logger.info("incomeMsg:" + msg);
		if (MessageUtils.getMsgType(msg).equals(GeneralMessage.MSG_TYPE_TEXT)) {
			IncomeText text = MessageUtils.getIncomeMsg(msg, IncomeText.class);
			logger.info("sendMsg:" + getSendMsg(text));
			inputStream = new ByteArrayInputStream(getSendMsg(text).getBytes(
					"UTF-8"));
		} else if (MessageUtils.getMsgType(msg).equals(
				GeneralMessage.MSG_TYPE_EVENT)) {
			IncomeEvent event = MessageUtils.getIncomeMsg(msg,
					IncomeEvent.class);
			greeting(event);
		}
	}

	private void greeting(IncomeEvent event)
			throws UnsupportedEncodingException {
		SendText text = MessageUtils.getBaseSendMsg(event, SendText.class);
		text.setContent("欢迎关注vincent的公众帐号！");
		String greetingStr = MessageUtils.getSendMsgStr(text);
		inputStream = new ByteArrayInputStream(greetingStr.getBytes("UTF-8"));
	}

	private String getSendMsg(IncomeText text) {
		SendText sendText = MessageUtils.getBaseSendMsg(text, SendText.class);
		sendText.setContent(text.getContent());
		return MessageUtils.getSendMsgStr(sendText);
	}

	private String getIncodeMsg() throws IOException {
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
