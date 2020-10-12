package com.wuzz.demo.netty.websocket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/10/12 11:17
 * @since 1.0
 **/
public class NettyServer {

    public static void main(String[] args) throws Exception{
        //服务器启动
        new NettyServer().start(9978);
    }

    public void start(int port) throws Exception{
        //用于监听连接的线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        //用于发送接收消息的线程组
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try{
            //启动类引导程序
            ServerBootstrap b=new ServerBootstrap();
            //绑定两个线程组
            b.group(bossGroup,workGroup);
            //设置非阻塞,用它来建立新accept的连接,用于构造serverSocketChannel的工厂类
            b.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel){
                    ChannelPipeline channelPipeline=channel.pipeline();
                    // HttpServerCodec：将请求和应答消息解码为HTTP消息
                    channelPipeline.addLast(new HttpServerCodec());
                    // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
                    channelPipeline.addLast(new HttpObjectAggregator(65536));
                    // ChunkedWriteHandler：向客户端发送HTML5文件
                    channelPipeline.addLast(new ChunkedWriteHandler());
                    //在管道中添加自己实现的Handler处理类
                    channelPipeline.addLast(new WebsocketServerHandler2());
                }
            });
            Channel channel=b.bind(port).sync().channel();
            System.out.println("服务器启动端口:"+port);
            channel.closeFuture().sync();
        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}
