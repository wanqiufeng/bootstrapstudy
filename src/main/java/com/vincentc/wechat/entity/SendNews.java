package com.vincentc.wechat.entity;

import java.util.ArrayList;
import java.util.List;

public class SendNews extends GeneralMessage {
	private String ArticleCount;
	private List<SendArticle> Articles = new ArrayList<SendArticle>();

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public List<SendArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<SendArticle> articles) {
		Articles = articles;
	}

}
