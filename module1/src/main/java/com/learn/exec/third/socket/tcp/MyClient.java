package com.learn.exec.third.socket.tcp;

import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端类
 *
 * @author Colm
 * @create 2019/10/22
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8888);
        OutputStream out = s.getOutputStream();
        int i = 0;
        while (true){
            out.write(("hello " + i++ + "\r\n").getBytes());
            out.flush();
            Thread.sleep(1000);
        }
    }
}
