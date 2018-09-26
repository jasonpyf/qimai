package com.jason.qimai.bean;

import java.util.List;

public class DownLoad {

	private int code;

	private String msg;

	private List<Data> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public class Data {

		private String name;

		private List<List<String>> data;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<List<String>> getData() {
			return data;
		}

		public void setData(List<List<String>> data) {
			this.data = data;
		}

	}
}
