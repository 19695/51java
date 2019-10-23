package com.learn.exec.third.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio
 *
 * @author Colm
 * @create 2019/10/22
 */
public class NIODemo {
    public static void main(String[] args) throws Exception {
        File inFile = new File("D:/Workspaces/IntelliJIDEA/51java/module1/src/test/IOFile/nio/source");
        FileInputStream fin = new FileInputStream(inFile);
        // 通道接到输入流
        FileChannel finChannel = fin.getChannel();

        File outFile = new File("D:\\Workspaces\\IntelliJIDEA\\51java\\module1\\src\\test\\IOFile\\nio\\target.txt");
        FileOutputStream fout = new FileOutputStream(outFile);
        //通道接到输出流
        FileChannel foutChannel = fout.getChannel();

        // 得到一个 buffer， 1K
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(finChannel.read(buffer) != -1){
            buffer.flip(); // 从头读缓冲
            foutChannel.write(buffer);
            buffer.clear(); // 所有指针都重置
        }

        foutChannel.close();
        finChannel.close();
        fout.close();
        fin.close();
    }
}

