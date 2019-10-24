package com.learn.exec.fourth.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端，套接字版本，没有使用 NIO
 *
 * @author Colm
 * @create 2019/10/24
 */
public class MyNIOClient2 {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8888);
        OutputStream out = s.getOutputStream();
        int index = 0;
        for (;;){
            out.write(("hello" + index++).getBytes());
            Thread.sleep(300L);
        }
    }
}
