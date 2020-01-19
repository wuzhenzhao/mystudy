package com.wuzz.demo.netty.file;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/11
 * Time: 9:37
 * Description:
 */
public class UploadFileServerHandler extends ChannelInboundHandlerAdapter {
    private int byteRead;
    private volatile int start = 0;
    private String file_dir = "D:/a";
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadFileServerHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
        LOGGER.info("服务端：channelActive()");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        LOGGER.info("服务端：channelInactive()");
        ctx.flush();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("收到客户端发来的文件,正在处理....");
        if (msg instanceof UploadFile) {
            UploadFile ef = (UploadFile) msg;
            byte[] bytes = ef.getBytes();
            byteRead = ef.getEndPos();
            String fileName = ef.getFileName();//文件名
            String path = file_dir + File.separator + fileName;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");//r: 只读模式 rw:读写模式
            randomAccessFile.seek(start);//移动文件记录指针的位置,
            randomAccessFile.write(bytes);//调用了seek（start）方法，是指把文件的记录指针定位到start字节的位置。也就是说程序将从start字节开始写数据
            start = start + byteRead;
            if (byteRead > 0) {
                ctx.writeAndFlush(start);//向客户端发送消息
                randomAccessFile.close();
                if(byteRead!=1024 * 10){
                    Thread.sleep(1000);
                    channelInactive(ctx);
                }
            } else {
                ctx.close();
            }
            LOGGER.info("处理完毕,文件路径:"+path+","+byteRead);
        }
    }

    //异常相关处理
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("get server exception :" + cause.getMessage());

        cause.printStackTrace();

        if (ctx.channel().isActive()) {
            ctx.writeAndFlush("ERR: " +
                    cause.getClass().getSimpleName() + ": " +
                    cause.getMessage() + '\n').addListener(ChannelFutureListener.CLOSE);
        }
    }
}
