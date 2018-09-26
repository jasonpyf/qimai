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
		
	}

	@Override
	public void onFailure(Throwable cause) {
		
	}
}
