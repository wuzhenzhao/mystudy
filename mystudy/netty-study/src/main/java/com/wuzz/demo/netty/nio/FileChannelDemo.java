package com.wuzz.demo.netty.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/31
 * Time: 15:06
 * Description 描述:
 */
public class FileChannelDemo {
    public static void main(String[] args) throws Exception {

        /*-------从buffer往fileChannel中写入数据-------------------------*/
        File file =new File("D:/nio.data");
        if(!file.exists()) {//判断文件是否存在，不存在则创建
            file.createNewFile();
        }
        //获取输出流
        FileOutputStream outputStream = new FileOutputStream(file);
        //从输出流中获取channel
        FileChannel writeFileChannel = outputStream.getChannel();
        //开辟新的字节空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        //写入数据
        byteBuffer.put("fileChannel hello".getBytes("UTF-8"));
        //刷新指针
        byteBuffer.flip();
        //进行写操作
        writeFileChannel.write(byteBuffer);
        byteBuffer.clear();
        outputStream.close();
        writeFileChannel.close();

        /*-------从fileChannel往buffer中写入数据-------------------------*/
        Path path = Paths.get("D:/nio.data");
        FileChannel readFileChannel = FileChannel.open(path);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate((int)readFileChannel.size()+1);
        readFileChannel.read(byteBuffer2);
        byteBuffer2.flip();
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(byteBuffer2);
        System.out.println(charBuffer.toString());
        byteBuffer2.clear();
        readFileChannel.close();
    }
}
