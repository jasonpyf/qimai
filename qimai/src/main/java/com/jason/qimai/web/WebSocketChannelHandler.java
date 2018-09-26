package com.jason.qimai.web;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.jason.qimai.bean.AnalysisData;
import com.jason.qimai.bean.DataRspData;
import com.jason.qimai.bean.DownLoad;
import com.jason.qimai.bean.MsgRspData;
import com.jason.qimai.bean.ReqData;
import com.jason.qimai.bean.SearchData;
import com.jason.qimai.bean.SearchData.AppData;
import com.jason.qimai.bean.SearchData.AppData.AppInfo;
import com.jason.qimai.bean.SearchReqData;
import com.jason.qimai.utils.AverageTool;
import com.jason.qimai.utils.QimaiTool;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * Echoes uppercase content of text frames.
 */
@ChannelHandler.Sharable
public class WebSocketChannelHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final Map<String, Channel> SESSIONS = new ConcurrentHashMap<>();
	
	public static final AtomicBoolean started = new AtomicBoolean();
	
	public static List<AnalysisData> list1 = new ArrayList<>(); // 360
	
	public static List<AnalysisData> list2 = new ArrayList<>(); // 百度
	
	public static List<AnalysisData> list3 = new ArrayList<>(); // 应用宝
	
	public static List<AnalysisData> list4 = new ArrayList<>(); // 豌豆荚
	
	public static List<AnalysisData> list5 = new ArrayList<>(); // 华为
	
	public static List<AnalysisData> list6 = new ArrayList<>(); // 魅族
	
	public static List<AnalysisData> list7 = new ArrayList<>(); // VIVO
	
	public static List<AnalysisData> list8 = new ArrayList<>(); // OPPO
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
		if (frame instanceof TextWebSocketFrame) {
			String text = ((TextWebSocketFrame) frame).text();
			logger.info("rev msg is {}.", text);
			
			ReqData data = JSON.parseObject(text, ReqData.class);
			if(data.getType() == ReqData.TYPE_SEARCH) {
				doSearch(ctx, text);
			} else {
				String message = "unsupported type: " + data.getType();
				throw new UnsupportedOperationException(message);
			}
		} else {
			String message = "unsupported frame type: " + frame.getClass().getName();
			throw new UnsupportedOperationException(message);
		}
	}
	
	private void doSearch(ChannelHandlerContext ctx, String text) {
		if(started.compareAndSet(false, true)) {
			new Thread(()-> {
				SearchReqData data = JSON.parseObject(text, SearchReqData.class);
				doAnalysis(data.getTitle());
			}).start();
		} else {
			MsgRspData data = new MsgRspData();
			data.setType(MsgRspData.TYPE_MSG);
			String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
		    data.setMsg("["+format+"]" + "搜索还没有完成，等待结果完成");
			TextWebSocketFrame rspFrame = new TextWebSocketFrame(JSON.toJSONString(data));
			ctx.channel().writeAndFlush(rspFrame);
		}
	}
	
	private void doAnalysis(String title) {
		sendMsgBroadcast("开始搜索------------>>>>>>>");
		
		clearData();
		
		int page = 1;
		int maxPage = 0;
		SearchData searchData = null;
		do {
			 sendMsgBroadcast("正在搜索，" + title + "第" + page + "页数据");
			 searchData = QimaiTool.getDataForSearchAndroid(page,title);
			 if(searchData != null && searchData.getCode() == 10000) {
				searchData.getAppList().stream().filter((x)->x.getAppInfo().getAppName().contains(title)).forEach((y)->reqDown(y));
				if(page == 1) {
					maxPage = searchData.getMaxPage();
				}
			 } else {
				String text = "搜索错误，错误码10001，错误消息：" + searchData.getMsg();
				sendMsgBroadcast(text);
			 }
			 page++;
		}while(page <= maxPage);
		
		// 发送结果数据
		List<AnalysisData> analysisDatas1 = list1.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas1, 1);
		
		List<AnalysisData> analysisDatas2 = list2.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas2, 2);
		
		List<AnalysisData> analysisDatas3 = list3.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas3, 3);
		
		List<AnalysisData> analysisDatas4 = list4.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas4, 4);
		
		List<AnalysisData> analysisDatas5 = list5.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas5, 5);
		
		List<AnalysisData> analysisDatas6 = list6.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas6, 6);
		
		List<AnalysisData> analysisDatas7 = list7.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas7, 7);
		
		List<AnalysisData> analysisDatas8 = list8.stream().sorted((e1, e2)->-Integer.compare(e1.getAverage(), e2.getAverage())).collect(Collectors.toList());
		sendDataBroadcast(analysisDatas8, 8);
		started.compareAndSet(true, false);
		sendMsgBroadcast("搜索结束------------>>>>>>>");
	}
	
	public void reqDown(AppData appData) {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String edate = sdf.format(current); // 开始日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		String sdate = sdf.format(calendar.getTime()); // 结束日期
		AppInfo appInfo = appData.getAppInfo();
		String appId = appInfo.getAppId();
		String type = "day";
		DownLoad downLoad = QimaiTool.getDataForAndappDownLoad(appId, type, sdate, edate);
		if(downLoad != null && downLoad.getCode() == 10000) {
			StringBuffer sb = new StringBuffer("[" + appInfo.getAppName() + "]\r\n");
			downLoad.getData().stream().forEach((t)->{
				String ret = t.getData().stream().map((x)->x.get(1)).collect(Collectors.joining(","));
				AnalysisData data = AnalysisData.from(appInfo).setAverage(AverageTool.getAverage(ret));
				sb.append(t.getName()).append("下载量:").append( data.getAverage()).append("\r\n");
				// 添加数据
				addData(t.getName(), data);
			});
			sendMsgBroadcast(sb.toString());
		} else {
			String text = "搜索错误，错误码10002，错误消息：" + downLoad.getMsg();
			sendMsgBroadcast(text);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void clearData() {
		list1.clear();
		
		list2.clear();
		
		list3.clear();
		
		list4.clear();
		
		list5.clear();
		
		list6.clear();
		
		list7.clear();
		
		list8.clear();
	}
	
	private void addData(String name, AnalysisData data) {
		switch (name) {
		case "360":
			list1.add(data);
			break;
		case "百度":
			list2.add(data);
			break;
		case "应用宝":
			list3.add(data);
			break;
		case "豌豆荚":
			list4.add(data);
			break;
		case "华为":
			list5.add(data);
			break;
		case "魅族":
			list6.add(data);
			break;
		case "VIVO":
			list7.add(data);
			break;
		case "OPPO":
			list8.add(data);
			break;
		default:
			break;
		}
	}
	/**
	 * 发送广播
	 * @param text
	 */
	private void sendDataBroadcast(List<AnalysisData> analysisDatas, int subType) {
		SESSIONS.values().stream().forEach((x)->{
			DataRspData data = new DataRspData();
			data.setType(MsgRspData.TYPE_DATA);
			data.setSubType(subType);
		    data.setData(analysisDatas);
			TextWebSocketFrame rspFrame = new TextWebSocketFrame(JSON.toJSONString(data));
			x.writeAndFlush(rspFrame);
		});
	}
	
	/**
	 * 发送广播
	 * @param text
	 */
	private void sendMsgBroadcast(String text) {
		SESSIONS.values().stream().forEach((x)->{
			MsgRspData data = new MsgRspData();
			data.setType(MsgRspData.TYPE_MSG);
			String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
		    data.setMsg("["+format+"]" + text);
			TextWebSocketFrame rspFrame = new TextWebSocketFrame(JSON.toJSONString(data));
			x.writeAndFlush(rspFrame);
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		String id = ctx.channel().id().asLongText();
		cause.printStackTrace();
		ctx.close();
		logger.info("ctx exception caught, id is {}.", id);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asLongText();
		SESSIONS.putIfAbsent(id, ctx.channel());
		logger.info("ctx channel active, id is {}.", id);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asLongText();
		SESSIONS.remove(id);
		logger.info("ctx channel inactive, id is {}.", id);
	}
}