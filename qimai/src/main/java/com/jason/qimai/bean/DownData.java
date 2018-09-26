package com.jason.qimai.bean;

import java.util.List;

public class DownData {

	private int code;

	private String downloadTotal;

	private String msg;

	private Data data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDownloadTotal() {
		return downloadTotal;
	}

	public void setDownloadTotal(String downloadTotal) {
		this.downloadTotal = downloadTotal;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DownData [code=" + code + ", downloadTotal=" + downloadTotal + ", msg=" + msg + ", data=" + data + "]";
	}

	public class Data {

		private long max_date;

		private long min_date;

		private int step;

		private int total;

		private List<SubData> list;

		public long getMax_date() {
			return max_date;
		}

		public void setMax_date(long max_date) {
			this.max_date = max_date;
		}

		public long getMin_date() {
			return min_date;
		}

		public void setMin_date(long min_date) {
			this.min_date = min_date;
		}

		public int getStep() {
			return step;
		}

		public void setStep(int step) {
			this.step = step;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public List<SubData> getList() {
			return list;
		}

		public void setList(List<SubData> list) {
			this.list = list;
		}

		@Override
		public String toString() {
			return "Data [max_date=" + max_date + ", min_date=" + min_date + ", step=" + step + ", total=" + total
					+ ", list=" + list + "]";
		}

		public class SubData {

			private List<List<Long>> data;

			private String name;

			public List<List<Long>> getData() {
				return data;
			}

			public void setData(List<List<Long>> data) {
				this.data = data;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			@Override
			public String toString() {
				return "SubData [data=" + data + ", name=" + name + "]";
			}

		}
	}
	
	public String getDownDay() {
		if(code != 10000) return null;
		StringBuffer sb = new StringBuffer();
		data.list.get(0).data.stream().forEach((x)->sb.append(x.get(1)).append(","));
		if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
}
