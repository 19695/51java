package com.learn.exec.third.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Colm
 * @create 2019/10/23
 */
public class VirtualMemory {
    public static void main(String[] args) throws Exception {
        String filePath = "module1/src/test/IOFile/nio/VirtualMemory.txt";
        // 随机访问文件
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        MappedByteBuffer buffer = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 999);

        for(int i = 0; i < 26; i++ ){
            // putChar 有空格
            buffer.putChar((char)(65 + (i % 26)));
            buffer.putChar((char)(65 + (i % 26)));
            buffer.putChar((char)(65 + (i % 26)));
            // put 没有空格
            buffer.put((byte)(97 + (i % 26)));
            buffer.put((byte)(97 + (i % 26)));
            buffer.put((byte)(97 + (i % 26)));

            if(i % 50 == 0){
                buffer.clear();
            }
        }
    }
}
