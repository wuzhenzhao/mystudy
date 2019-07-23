package com.wuzz.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/11
 * Time: 9:34
 * Description:
 */
public class NettyServer {
    private static final String IP = "127.0.0.1";
    private static final int port = 6666;
    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int BIZTHREADSIZE = 100;
    //创建两个EventLoopGroup对象，创建boss线程组 ⽤于服务端接受客户端的连接
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    //创建 worker 线程组 ⽤于进⾏ SocketChannel 的数据读写
    private static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void start() throws Exception {

        //启动类初始化
        ServerBootstrap serverBootstrap = initServerBootstrap();
        // 绑定端⼝，并同步等待成功，即启动服务端
        ChannelFuture channelFuture = serverBootstrap.bind(IP, port).sync();
        //成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程。
        channelFuture.channel().closeFuture().sync();
        System.out.println("server start");

    }

    private static ServerBootstrap initServerBootstrap() {
        //一个Netty应用通常由一个Bootstrap开始
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //添加两个组，设置使⽤的EventLoopGroup
        serverBootstrap.group(bossGroup, workGroup)
                //初始化 channel,设置要被实例化的为 NioServerSocketChannel 类
                .channel(NioServerSocketChannel.class)
                //初始化channelHandler，设置连⼊服务端的 Client 的 SocketChannel 的处理器
                .childHandler(new ChannelInitializer<Channel>() {
                    //我们再来设置下相应的过滤条件。 这⾥需要继承Netty中ChannelInitializer 类，
                    //然后重写 initChannel 该⽅法，进⾏添加相应的设置，传输协议设置，以及相应的业务实现类
                    protected void initChannel(Channel ch) throws Exception {
                        //配置pipeline相关属性
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        // 相关处理 Handler
                        pipeline.addLast(new TcpServerHandler());
                    }
                });
        return serverBootstrap;
    }

    protected static void shutdown() {
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("启动Server...");
        NettyServer.start();
    }
}
