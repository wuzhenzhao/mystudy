package com.wuzz.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/17
 * Time: 17:29
 * Description 描述:
 */
public class PooledByteBufTest {

    public static void main(String[] args) {
        final byte[] CONTENT = new byte[1024];
        final byte[] CONTENT2 = new byte[1024];
        int loop = 1800000;
        long startTime = System.currentTimeMillis();
        ByteBuf poolBuffer = null;
        for (int i = 0; i < loop; i++) {
            poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolBuffer.writeBytes(CONTENT);
            poolBuffer.release();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("内存池分配缓冲区耗时" + (endTime - startTime) + "ms.");

        long startTime2 = System.currentTimeMillis();
        ByteBuf buffer = null;
        for (int j = 0; j < loop; j++) {
            buffer = Unpooled.directBuffer(1024);
            buffer.writeBytes(CONTENT2);
            buffer.release();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("非内存池分配缓冲区耗时" + (endTime2 - startTime2) + "ms.");
    }
}
