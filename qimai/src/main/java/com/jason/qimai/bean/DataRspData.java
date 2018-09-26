package com.jason.qimai.bean;

import java.util.List;

public class DataRspData extends RspData {

	private List<AnalysisData> data;

	public List<AnalysisData> getData() {
		return data;
	}

	public void setData(List<AnalysisData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataRspData [data=" + data + "]";
	}

}
