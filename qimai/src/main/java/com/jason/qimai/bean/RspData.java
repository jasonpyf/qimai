package com.jason.qimai.bean;

public class RspData {

	public static final int TYPE_MSG = 1;

	public static final int TYPE_DATA = 2;

	private int type;

	private int subType;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	@Override
	public String toString() {
		return "RspData [type=" + type + ", subType=" + subType + "]";
	}

}
