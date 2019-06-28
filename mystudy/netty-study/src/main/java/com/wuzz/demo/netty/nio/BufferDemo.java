package com.wuzz.demo.netty.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/31
 * Time: 11:33
 * Description 描述:
 */
public class BufferDemo {

    public static void decode(String str) throws UnsupportedEncodingException {
        // 开辟一个长度为128的字节空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        //写入数据
        byteBuffer.put(str.getBytes("UTF-8"));
        //写完数据以后要进行读取，需要设置 limit 为 position 的值，然后 position 置为0。
        byteBuffer.flip();
        /*获取utf8的编解码器*/
        Charset utf8 = Charset.forName("UTF-8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);/*对bytebuffer中的内容解码*/

        /*array()返回的就是内部的数组引用，编码以后的有效长度是0~limit*/
        char[] charArr = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(charArr);
    }

    public static void encode(String str){
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(str);
        charBuffer.flip();

        /*对获取utf8的编解码器*/
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer); /*对charbuffer中的内容解码*/

        /*array()返回的就是内部的数组引用，编码以后的有效长度是0~limit*/
        byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        System.out.println(Arrays.toString(bytes));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        BufferDemo.decode("解码测试");
        BufferDemo.encode("编码测试");
    }
}
