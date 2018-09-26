package com.jason.qimai.web;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

public class WebsocketServer extends NettyTCPServer {

	public WebsocketServer(int port) {
		super(port);
	}
	
	public WebsocketServer(int port, String host) {
		super(port, host);
	}

	@Override
	protected void initPipeline(ChannelPipeline pipeline) {
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerCompressionHandler());
		pipeline.addLast(new WebSocketServerProtocolHandler(getWebSocketPath(), null, true));
		pipeline.addLast(new WebSocketIndexHandler());
		pipeline.addLast(new WebSocketChannelHandler());
	}

	private String getWebSocketPath() {
		return "/data";
	}
}
