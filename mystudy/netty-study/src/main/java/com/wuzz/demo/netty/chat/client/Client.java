package com.wuzz.demo.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/24
 * Time: 15:52
 * Description 描述:
 */
public class Client {
    int port;
    public Client(int port) throws InterruptedException, IOException {
        this.port = port;
        connect();
    }

    public void connect() throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel arg0) throws Exception {
                        ChannelPipeline pipeline = arg0.pipeline();
//                        pipeline.addLast("stringD", new StringDecoder());
//                        pipeline.addLast("stringC", new StringEncoder());
//                        pipeline.addLast("http", new HttpClientCodec());
//                        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//                        arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
//                        arg0.pipeline().addLast(new StringDecoder());
//                        pipeline.addLast(new LineBasedFrameDecoder(1024));
                        pipeline.addLast("stringD", new StringDecoder());
                        pipeline.addLast("stringC", new StringEncoder());
                        pipeline.addLast("chat", new ClientHandler());

                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1", port).sync();
        while (true) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input = reader.readLine();
            if (input != null) {
                if ("quit".equals(input)) {
                    System.exit(1);
                }
                future.channel().writeAndFlush(Unpooled.copiedBuffer(input.getBytes())).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                        System.out.println("消息发送成功");
                    }
                });
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Client client = new Client(9978);
    }
}
