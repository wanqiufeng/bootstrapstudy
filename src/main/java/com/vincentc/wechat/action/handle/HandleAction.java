package com.vincentc.wechat.action.handle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.vincentc.wechat.action.BaseAction;

public class HandleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String TOKEN = "wanqiufeng";
	private InputStream inputStream;

	public String execute() {
		if (isGetRequest() && siteConnect()) {
			String echostr = this.getRequest().getParameter("echostr");
			try {
				inputStream = new ByteArrayInputStream(
						echostr.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return Action.INPUT;
			}
			
			
			
			
			
			return ActionSupport.SUCCESS;
		} else {
			return Action.INPUT;
		}

	}

	private boolean siteConnect() {
		Map<String, String> map = this.getRequest().getParameterMap();
		String timestamp = map.get("timestamp");
		String nonce = map.get("nonce");
		String signature = map.get("signature");
		List<String> list = new ArrayList<String>();
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		return getEncodeStr(list).equals(signature);
	}

	private String getEncodeStr(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s);
		}
		return DigestUtils.shaHex(sb.toString());
	}

	private boolean isGetRequest() {
		return this.getRequest().getMethod().equals(GET);
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
