package com.wuzz.demo.netty.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/4
 * Time: 11:53
 * Description 描述:
 */
public class UploadFileClientHandler extends ChannelInboundHandlerAdapter {

    private int byteRead;
    private volatile int start = 0;
    private volatile int lastLength = 0;
    public RandomAccessFile randomAccessFile;
    private UploadFile fileUploadFile;
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadFileClientHandler.class);
//    public FileMyClient(FileUploadFile ef) {
//        if (ef.getFile().exists()) {
//            if (!ef.getFile().isFile()) {
//                System.out.println("Not a file :" + ef.getFile());
//                return;
//            }
//        }
//        this.fileUploadFile = ef;
//    }


    public void channelActive(ChannelHandlerContext ctx) {
        LOGGER.info("客户端上线，执行channelActive()方法.....");
        fileUploadFile = new UploadFile();//创建传输类
        //注入文件
        File file = new File("D:/acsncg_1.3.0001.20191220114031_Win.zip");//
        String fileName = file.getName();// 文件名
        fileUploadFile.setFile(file);
        fileUploadFile.setFileName(fileName);
        fileUploadFile.setStarPos(0);// 第一次，文件开始位置开始传输
        try {//通过RandomAccessFile类来包装我们的文件，然后通过seek将指针指定到之前发生中断的位置进行读写
            randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(),
                    "r");
            //第一次指向0
            randomAccessFile.seek(fileUploadFile.getStarPos());
            // lastLength = (int) randomAccessFile.length() / 10;
            //每次发送10240 byte的数据
            lastLength = 1024 * 10;
            byte[] bytes = new byte[lastLength];
            //将数据读进byte
            if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                //设置本次发送长度
                fileUploadFile.setEndPos(byteRead);
                fileUploadFile.setBytes(bytes);
                //发送
                ctx.writeAndFlush(fileUploadFile);//发送消息到服务端
            } else {
            }
            LOGGER.info("channelActive()文件已经读完 " + byteRead);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        LOGGER.info("channelActive()方法执行结束");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        //此次服务端发送过来的数据是告诉客户端他读到哪里了，接下去要从这个start的点开始读
        if (msg instanceof Integer) {
            start = (Integer) msg;
            if (start != -1) {
                randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(), "r");
                randomAccessFile.seek(start); //将文件定位到start
                LOGGER.info("长度：" + (randomAccessFile.length() - start));
                //省下来的字节数
                int a = (int) (randomAccessFile.length() - start);
                if (a < lastLength) {//如果剩下的小于1024*10，那么值为a
                    lastLength = a;
                }
                LOGGER.info("文件剩余长度：" + a );
                byte[] bytes = new byte[lastLength];
                LOGGER.info("bytes的长度是=" + bytes.length);
                //文件没传输完，并且开始的字节位置要小于文件长度
                if ((byteRead = randomAccessFile.read(bytes)) != -1 && (randomAccessFile.length() - start) > 0) {
                    LOGGER.info("byteRead = " + byteRead);
                    fileUploadFile.setEndPos(byteRead);
                    fileUploadFile.setBytes(bytes);
                    try {
                        ctx.writeAndFlush(fileUploadFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    randomAccessFile.close();
                    ctx.close();
                    LOGGER.info("文件已经读完channelRead()--------" + byteRead);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("get client exception :" + cause.getMessage());
    }

//    public static void main(String[] args) {
//        // 源文件与目标文件
//        File sourceFile = new File("D:/bic-sso-client-1.6.7.RELEASE.jar");
//        File targetFile = new File("D:/a/bic-sso-client-1.6.7.RELEASE.jar");
//        // 输入输出流
//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        // 数据缓冲区
//        byte[] buf = new byte[1];
//
//        try {
//            fis = new FileInputStream(sourceFile);
//            fos = new FileOutputStream(targetFile);
//            // 数据读写
//            while (fis.read(buf) != -1) {
//                System.out.println("write data...");
//                fos.write(buf);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("指定文件不存在");
//        } catch (IOException e) {
//            // TODO: handle exception
//        } finally {
//            try {
//                // 关闭输入输出流
//                if (fis != null)
//                    fis.close();
//
//                if (fos != null)
//                    fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
