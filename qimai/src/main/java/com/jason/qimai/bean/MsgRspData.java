package com.jason.qimai.bean;

public class MsgRspData extends RspData {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MsgRspData [msg=" + msg + "]";
	}

}
