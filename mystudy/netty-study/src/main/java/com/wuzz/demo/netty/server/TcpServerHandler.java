package com.wuzz.demo.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/11
 * Time: 9:37
 * Description:
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {
    //建⽴连接时，发送⼀条庆祝消息
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("chanelActive>>>>>>>");
    }

    //业务逻辑处理
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server receive message:" + msg);
        ctx.channel().writeAndFlush("accept message " + msg);
//        ctx.channel().close();
        ctx.close();
    }

    //异常相关处理
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("get server exception :" + cause.getMessage());
    }
}
