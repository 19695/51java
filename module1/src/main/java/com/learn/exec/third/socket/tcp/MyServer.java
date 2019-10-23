package com.learn.exec.third.socket.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 服务器类——阻塞模式
 *
 * @author Colm
 * @create 2019/10/22
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        // 服务器套接字
        // 绑定到 0.0.0.0，通配地址
        ServerSocket ss = new ServerSocket(8888);

        // 端口最大到 65535
        // 绑定到指定地址
        ServerSocket ss1 = new ServerSocket();
        ss1.bind(new InetSocketAddress("127.0.0.1", 8888));

        while (true){
            System.out.println("开始接收...");
            // 客户端套接字，阻塞模式
            Socket s = ss.accept();

            SocketAddress socketAddress = s.getRemoteSocketAddress();
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            String ip = inetSocketAddress.getHostName();
            int port = inetSocketAddress.getPort();
            System.out.printf("%s:%d 连进来了！！！\r\n", ip, port);

            InputStream in = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.printf("%s:%d 发来消息：%s\r\n", ip, port, line);
            }
        }

        // 方法测试
       /* while (true){
            // 客户端套接字，阻塞模式
            Socket s = ss.accept();
            System.out.println(s.getInetAddress()); // /127.0.0.1
            System.out.println(s.getRemoteSocketAddress()); // /127.0.0.1:61333
            SocketAddress socketAddress = s.getRemoteSocketAddress();
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            System.out.println(inetSocketAddress.getAddress()); // /127.0.0.1
            System.out.println(inetSocketAddress.getHostName()); // 127.0.0.1
            System.out.println(inetSocketAddress.getHostString()); // 127.0.0.1
        }*/
    }
}
