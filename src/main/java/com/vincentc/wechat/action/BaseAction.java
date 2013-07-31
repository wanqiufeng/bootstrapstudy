package com.vincentc.wechat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

}
