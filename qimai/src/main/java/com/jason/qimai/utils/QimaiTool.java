package com.jason.qimai.utils;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.jason.qimai.bean.DownData;
import com.jason.qimai.bean.DownLoad;
import com.jason.qimai.bean.SearchData;

public class QimaiTool {

	public static SearchData getDataForSearchAndroid(int page, String search) {
		try {
			String analysis = AnalysisTool.getAnalysisForSearchAndroid(page, search);
			
			String url = "https://api.qimai.cn/search/android?analysis="+analysis+"&search="+URLEncoder.encode(search, "UTF-8")+"&page="+page;
			System.out.println("=================================>>>>>>");
			System.out.println(url);
			System.out.println("=================================>>>>>>");
			String text = HttpClient.doGet(url);
			System.out.println("=================================>>>>>>");
			System.out.println(text);
			System.out.println("=================================>>>>>>");
			return JSON.parseObject(text, SearchData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static DownData getDataForAndappDownDay(String appid, String sdate, String edate) {
		try {
			String analysis = AnalysisTool.getAnalysisForAndappDownDay(appid, sdate, edate);
			
			String url = "https://api.qimai.cn/andapp/downDay?analysis="+analysis+"&appid="+appid+"&sdate="+sdate+"&edate="+edate;
			System.out.println("=================================>>>>>>");
			System.out.println(url);
			System.out.println("=================================>>>>>>");
			String text = HttpClient.doGet(url);
			System.out.println("=================================>>>>>>");
			System.out.println(text);
			System.out.println("=================================>>>>>>");
			return JSON.parseObject(text, DownData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static DownLoad getDataForAndappDownLoad(String appid, String type, String sdate, String edate) {
		try {
			String analysis = AnalysisTool.getAnalysisForAndappDownLoad(appid, type, sdate, edate);
			
			String url = "https://api.qimai.cn/andapp/downLoad?analysis="+analysis+"&appid="+appid+"&type="+type+"&sdate="+sdate+"&edate="+edate;
			
			String text = HttpClient.doGet(url);
			System.out.println(text);
			return JSON.parseObject(text, DownLoad.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
