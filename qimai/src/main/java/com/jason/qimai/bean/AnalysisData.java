package com.jason.qimai.bean;

import com.jason.qimai.bean.SearchData.AppData.AppInfo;

public class AnalysisData {

	private String appId;

	private String appName;

	private String icon;

	private String publisher;

	private int average;

	public String getAppId() {
		return appId;
	}

	public AnalysisData setAppId(String appId) {
		this.appId = appId;
		return this;
	}

	public String getAppName() {
		return appName;
	}

	public AnalysisData setAppName(String appName) {
		this.appName = appName;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public AnalysisData setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getPublisher() {
		return publisher;
	}

	public AnalysisData setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	public int getAverage() {
		return average;
	}

	public AnalysisData setAverage(int average) {
		this.average = average;
		return this;
	}

	@Override
	public String toString() {
		return "AnalysisData [appId=" + appId + ", appName=" + appName + ", icon=" + icon + ", publisher=" + publisher
				+ ", average=" + average + "]";
	}
	
	public static AnalysisData from(AppInfo appInfo) {
		AnalysisData data = new AnalysisData();
		data.appId = appInfo.getAppId();
		data.appName = appInfo.getAppName();
		data.icon = appInfo.getIcon();
		data.publisher = appInfo.getPublisher();
		return data;
	}

}
