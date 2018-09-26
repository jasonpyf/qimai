package com.jason.qimai.bean;

public class ReqData {
	
	public static final int TYPE_SEARCH = 1;

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReqData [type=" + type + "]";
	}

}
