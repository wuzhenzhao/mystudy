package com.wuzz.demo.netty.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/24
 * Time: 15:49
 * Description 描述:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    /*
     * 对channel进行管理
     */
    public static final ChannelGroup group = new DefaultChannelGroup(
            GlobalEventExecutor.INSTANCE);

    /**
     * 有消息进来
     * @param arg0
     * @param arg1
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception {
        Channel channel = arg0.channel();

        for (Channel ch : group) {
            if (ch == channel) {
                arg0.writeAndFlush("[你说]:" + arg1 + "$_\n");
            } else {
                ch.writeAndFlush("[" + channel.remoteAddress() + "]" + arg1 + "\n");
            }
        }
        ReferenceCountUtil.release(arg1);
    }

    /**
     * 上线
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "]" + "上线了");
    }

    /**
     * 下线
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "]" + "下线了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(
                "[" + ctx.channel().remoteAddress() + "]" + "退出房间");
        ctx.close().sync();
    }

    /**
     * 当有新的连接的时候进行通知
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel ch : group) {
            ctx.writeAndFlush("[" + channel.remoteAddress() + "] " + "加入聊天");
        }
        group.add(channel);
    }

    /**
     * 退出聊天
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel ch : group) {
            ch.writeAndFlush(
                    "[" + channel.remoteAddress() + "] " + "退出聊天");
        }
        group.remove(channel);
    }

}
