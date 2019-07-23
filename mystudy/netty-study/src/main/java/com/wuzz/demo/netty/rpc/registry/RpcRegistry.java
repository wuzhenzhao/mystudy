package com.wuzz.demo.netty.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class RpcRegistry {  
    private int port;  
    public RpcRegistry(int port){  
        this.port = port;  
    }  
    public void start(){  
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
          
        try {  
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
            		.channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
  
                        @Override  
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //自定义协议解码器
                            /** 入参有5个，分别解释如下
                             （1） maxFrameLength - 发送的数据包最大长度；
                             （2） lengthFieldOffset - 长度域偏移量，指的是长度域位于整个数据包字节数组中的下标；
                             （3） lengthFieldLength - 长度域的自己的字节数长度。
                             （4） lengthAdjustment – 长度域的偏移量矫正。 如果长度域的值，除了包含有效数据域的长度外，
                                    还包含了其他域（如长度域自身）长度，那么，就需要进行矫正。矫正的值为：包长 - 长度域的值 – 长度域偏移 – 长度域长。
                             （5） initialBytesToStrip – 丢弃的起始字节数。丢弃处于有效数据前面的字节数量。比如前面有4个节点的长度域，则它的值为4。
                             */
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            //自定义协议编码器
                            pipeline.addLast(new LengthFieldPrepender(4));
                            //对象参数类型编码器
                            pipeline.addLast("encoder",new ObjectEncoder());
                            //对象参数类型解码器
                            pipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(new RegistryHandler());
                        }  
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = b.bind(port).sync();
            System.out.println("Wuzz RPC Registry start listen at " + port );
            future.channel().closeFuture().sync();    
        } catch (Exception e) {  
             bossGroup.shutdownGracefully();    
             workerGroup.shutdownGracefully();  
        }  
    }
    
    
    public static void main(String[] args) throws Exception {    
        new RpcRegistry(8888).start();
    }    
}  
