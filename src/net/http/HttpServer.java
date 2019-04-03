package net.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpServer implements Runnable {

	private int port = 8081;

	public void create() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b//
					.group(bossGroup, workerGroup)//
					.channel(NioServerSocketChannel.class)//
					.childHandler(new ChildChannelHandler())//
			// .option(ChannelOption.SO_BACKLOG, 128)
			// .childOption(ChannelOption.SO_KEEPALIVE, true)
			;

			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(port).sync();

			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ChannelPipeline cp = ch.pipeline();
			cp.addLast(new HttpRequestDecoder());
			cp.addLast(new HttpObjectAggregator(65536));
			cp.addLast(new HttpResponseEncoder());
			cp.addLast(new ChunkedWriteHandler());
//			cp.addLast(new HttpStreamHandler());
		}
	}

}
