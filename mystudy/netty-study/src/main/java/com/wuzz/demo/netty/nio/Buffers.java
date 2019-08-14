package com.wuzz.demo.netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteBuffer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/7/4
 * Time: 16:32
 * Description 描述:
 */
/*自定义Buffer类中包含读缓冲区和写缓冲区，用于注册通道时的附加对象*/
public class Buffers {

    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity){
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }
    public ByteBuffer getReadBuffer(){
        return readBuffer;
    }
    public ByteBuffer gerWriteBuffer(){
        return writeBuffer;
    }

    public static void main(String[] args) {
        PooledByteBufAllocator allocator =new PooledByteBufAllocator();
        ByteBuf buffer = allocator.buffer();
    }
}
