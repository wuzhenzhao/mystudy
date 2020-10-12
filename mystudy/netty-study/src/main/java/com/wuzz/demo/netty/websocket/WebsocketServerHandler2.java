package com.wuzz.demo.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/10/12 11:10
 * @since 1.0
 **/
@RestController
public class WebsocketServerHandler2 extends ChannelInboundHandlerAdapter {

//    private WebSocketServerHandshaker handshaker;

    private static final AttributeKey<WebSocketServerHandshaker> ATTR_HANDSHAKER = AttributeKey.newInstance("ATTR_KEY_CHANNELID");

    /*
     * 对channel进行管理
     */
    public static final ChannelGroup group = new DefaultChannelGroup(
            GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //传统的http接入
        if (o instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        }
        //webSocket接入
        else if (o instanceof WebSocketFrame) {
            handleWebsocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        //构造握手响应返回

//        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:8082/", null, false);
//        handshaker = webSocketServerHandshakerFactory.newHandshaker(req);
//        handshaker.handshake(ctx.channel(), req);


        String subProtocols = request.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);

        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws://localhost:9978", subProtocols, false);

        WebSocketServerHandshaker handshaker = factory.newHandshaker(request);

        if (handshaker == null) {

            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());

        } else {
            //响应请求
            handshaker.handshake(ctx.channel(), request);
            //将handshaker绑定给channel
            ctx.channel().attr(ATTR_HANDSHAKER).set(handshaker);
        }
        return;


    }

    private void handleWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断链路是否关闭
        if (frame instanceof CloseWebSocketFrame) {
            WebSocketServerHandshaker handshaker = ctx.channel().attr(ATTR_HANDSHAKER).get();

            if (handshaker == null) {
                return;
            }

            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }

        //ping 回复 pong
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if (frame instanceof PongWebSocketFrame) {
            return;
        }
        //处理消息
        if (frame instanceof TextWebSocketFrame) {
            String text = ((TextWebSocketFrame) frame).text();
            for (Channel ch : group) {
                if (ctx.channel().equals(ch)) {
                    continue;
                }
                TextWebSocketFrame rsp = new TextWebSocketFrame(text + "发送方：" + ctx.channel().remoteAddress());
                ch.writeAndFlush(rsp);
            }
        }


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "]" + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "]" + "下线了");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.add(channel);
    }

}
