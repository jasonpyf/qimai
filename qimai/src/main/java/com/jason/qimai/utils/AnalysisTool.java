package com.jason.qimai.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.codec.binary.Base64;

import com.jason.qimai.app.Application;

public class AnalysisTool {
	
	private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	
	private static final String separator = "@#";
	
	private static final String defStr = "1";
	
	static {
		InputStream is = Application.class.getResourceAsStream("/script.js");
		InputStreamReader isr = new InputStreamReader(is);
		try {
			engine.eval(isr);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	public static String getAnalysisForSearchAndroid(int page, String search) throws Exception{
		
		String[] arr = {String.valueOf(page), search};
		Arrays.sort(arr);
		
		String merge = "";
		for (String str : arr) {
			merge += str;
		}
		
		String searchBase64 = Base64.encodeBase64String(merge.getBytes(StandardCharsets.UTF_8));
		
		String path = "/search/android";
		
		long diffDate = new Date().getTime() - 1515125653845L;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(searchBase64).append(separator).append(path).append(separator).append(diffDate).append(separator).append(defStr);
		
		Invocable invocable = (Invocable) engine;
		
		Object obj = invocable.invokeFunction("toCharCode", sb.toString());
		
		return Base64.encodeBase64String(obj.toString().getBytes(StandardCharsets.UTF_8));
	}
	
	public static String getAnalysisForAndappDownDay(String appid, String sdate, String edate) throws Exception{
		
		String[] arr = {appid, sdate, edate};
		Arrays.sort(arr);
		
		String merge = "";
		for (String str : arr) {
			merge += str;
		}
		
		String searchBase64 = Base64.encodeBase64String(merge.getBytes(StandardCharsets.UTF_8));
		
		String path = "/andapp/downDay";
		
		long diffDate = new Date().getTime() - 1515125653845L;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(searchBase64).append(separator).append(path).append(separator).append(diffDate).append(separator).append(defStr);
		
		Invocable invocable = (Invocable) engine;
		
		Object obj = invocable.invokeFunction("toCharCode", sb.toString());
		
		return Base64.encodeBase64String(obj.toString().getBytes(StandardCharsets.UTF_8));
	}
	
	public static String getAnalysisForAndappDownLoad(String appid, String type, String sdate, String edate) throws Exception{
		
		String[] arr = {appid, type, sdate, edate};
		Arrays.sort(arr);
		
		String merge = "";
		for (String str : arr) {
			merge += str;
		}
		
		String searchBase64 = Base64.encodeBase64String(merge.getBytes(StandardCharsets.UTF_8));
		
		String path = "/andapp/downLoad";
		
		long diffDate = new Date().getTime() - 1515125653845L;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(searchBase64).append(separator).append(path).append(separator).append(diffDate).append(separator).append(defStr);
		
		Invocable invocable = (Invocable) engine;
		
		Object obj = invocable.invokeFunction("toCharCode", sb.toString());
		
		return Base64.encodeBase64String(obj.toString().getBytes(StandardCharsets.UTF_8));
	}
	
}
