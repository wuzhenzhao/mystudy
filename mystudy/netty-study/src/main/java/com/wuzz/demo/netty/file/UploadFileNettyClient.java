package com.wuzz.demo.netty.file;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/4
 * Time: 11:52
 * Description 描述:
 */
public class UploadFileNettyClient implements Runnable {

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null))); // 最大长度
                            pipeline.addLast("handler", new UploadFileClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect("127.0.0.1", 6666).sync();
            f.channel().writeAndFlush("hello service !" + Thread.currentThread().getName());
            f.channel().closeFuture().sync();
//            bootstrap.connect("127.0.0.1", 6666).addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    System.out.println("connect success in port: 6666");
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
            new Thread(new UploadFileNettyClient(), ">>> this thread ").start();

    }
}

