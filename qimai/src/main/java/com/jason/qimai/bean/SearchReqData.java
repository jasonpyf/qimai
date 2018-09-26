package com.jason.qimai.bean;

public class SearchReqData extends ReqData {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "SearchReqData [title=" + title + "]";
	}

}
