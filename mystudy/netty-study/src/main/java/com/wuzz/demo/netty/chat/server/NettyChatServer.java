package com.wuzz.demo.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/24
 * Time: 15:48
 * Description 描述:
 */
public class NettyChatServer {
    int port;

    public NettyChatServer(int port) throws InterruptedException {
        this.port = port;
        start_Server();
    }

    public void start_Server() throws InterruptedException {
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel arg0) throws Exception {
//                        arg0.pipeline().addLast(new StringEncoder());
//                        arg0.pipeline().addLast(new StringDecoder());
                        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                        arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                        arg0.pipeline().addLast(new StringDecoder());
                        arg0.pipeline().addLast(new StringEncoder());
                        arg0.pipeline().addLast(new ServerHandler());
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        System.out.println("Server.start_Server()");
        channelFuture.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        NettyChatServer server = new NettyChatServer(9978);

    }
}
