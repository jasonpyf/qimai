package com.jason.qimai.app;

import com.jason.qimai.web.Listener;
import com.jason.qimai.web.WebsocketServer;

public class Application implements Listener{
	
	public static void main(String[] args) throws Exception {
		Application app = new Application();
		app.runApp(args);
	}
	
	public void runApp(String[] args) {
		WebsocketServer server = new WebsocketServer(8008);
		server.init();
		server.start(this);
	}

	@Override
	public void onSuccess(Object... args) {
		System.out.println("服务启动成功...");
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("服务启动失败..." + cause.getMessage());
	}
}
