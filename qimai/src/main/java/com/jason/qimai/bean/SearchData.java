package com.jason.qimai.bean;

import java.util.List;

public class SearchData {

	private int code;

	private boolean isSearch;

	private int maxPage;

	private String msg;

	private int totalNum;

	private List<AppData> appList;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<AppData> getAppList() {
		return appList;
	}

	public void setAppList(List<AppData> appList) {
		this.appList = appList;
	}

	@Override
	public String toString() {
		return "SearchData [code=" + code + ", isSearch=" + isSearch + ", maxPage=" + maxPage + ", msg=" + msg
				+ ", totalNum=" + totalNum + ", appList=" + appList + "]";
	}

	public class AppData {

		private String genre;

		private int isMyApp;

		private AppInfo appInfo;

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public int getIsMyApp() {
			return isMyApp;
		}

		public void setIsMyApp(int isMyApp) {
			this.isMyApp = isMyApp;
		}

		public AppInfo getAppInfo() {
			return appInfo;
		}

		public void setAppInfo(AppInfo appInfo) {
			this.appInfo = appInfo;
		}

		@Override
		public String toString() {
			return "AppData [genre=" + genre + ", isMyApp=" + isMyApp + ", appInfo=" + appInfo + "]";
		}

		public class AppInfo {

			private String appId;

			private String appName;

			private String icon;

			private String publisher;

			public String getAppId() {
				return appId;
			}

			public void setAppId(String appId) {
				this.appId = appId;
			}

			public String getAppName() {
				return appName;
			}

			public void setAppName(String appName) {
				this.appName = appName;
			}

			public String getIcon() {
				return icon;
			}

			public void setIcon(String icon) {
				this.icon = icon;
			}

			public String getPublisher() {
				return publisher;
			}

			public void setPublisher(String publisher) {
				this.publisher = publisher;
			}

			@Override
			public String toString() {
				return "AppInfo [appId=" + appId + ", appName=" + appName + ", icon=" + icon + ", publisher="
						+ publisher + "]";
			}

		}
	}
}
