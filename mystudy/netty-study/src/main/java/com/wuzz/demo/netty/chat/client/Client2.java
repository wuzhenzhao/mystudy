//package com.wuzz.demo.netty.chat.client;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.http.HttpClientCodec;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
///**
// * Create with IntelliJ IDEA
// * User: Wuzhenzhao
// * Date: 2019/7/24
// * Time: 15:52
// * Description 描述:
// */
//public class Client2 {
//    int port;
//    public Client2(int port) throws InterruptedException, IOException {
//        this.port = port;
//        connect();
//    }
//
//    public void connect() throws InterruptedException, IOException {
//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(eventLoopGroup)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<Channel>() {
//
//                    @Override
//                    protected void initChannel(Channel arg0) throws Exception {
//                        ChannelPipeline pipeline = arg0.pipeline();
//                        pipeline.addLast("stringD", new StringDecoder());
//                        pipeline.addLast("stringC", new StringEncoder());
//                        pipeline.addLast("http", new HttpClientCodec());
//                        pipeline.addLast("chat", new ClientHandler());
//
//                    }
//                });
//        Channel channel = bootstrap.connect("127.0.0.1",port).sync().channel();
//        while (true) {
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(System.in));
//            String input = reader.readLine();
//            if (input != null) {
//                if ("quit".equals(input)) {
//                    System.exit(1);
//                }
//                channel.writeAndFlush(input);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        Client2 client = new Client2(9978);
//    }
//}
