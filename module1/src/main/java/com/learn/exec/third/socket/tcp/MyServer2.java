package com.learn.exec.third.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器类——多线程模式
 *
 * @author Colm
 * @create 2019/10/22
 */
public class MyServer2 {

    private static final String HOSTNAME = "hostName";
    private static final String PORT = "port";

    public static void main(String[] args) throws Exception {
        // 服务器套接字
        ServerSocket ss = new ServerSocket(8888);

        while(true){
            System.out.println("开始接收...");

            // 客户端套接字
            Socket s = ss.accept();
            System.out.printf("%s:%d 连进来了！！！\r\n",
                    getRemoteAddr(s).get(MyServer2.HOSTNAME),
                    getRemoteAddr(s).get(MyServer2.PORT));

            new commThread(s).start();
        }
    }

    static class commThread extends Thread{
        private Socket socket;

        public commThread(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try {
                Map<String, Object> addr = getRemoteAddr(socket);
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while((line = reader.readLine()) != null){
                    System.out.printf("%s:%d 发来了一条消息：%s\r\n",
                            addr.get(MyServer2.HOSTNAME), addr.get(MyServer2.PORT), line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Object> getRemoteAddr(Socket socket){
        InetSocketAddress address = (InetSocketAddress) socket.getRemoteSocketAddress();
        String hostName = address.getHostName();
        int port = address.getPort();
        Map<String, Object> addrMap = new HashMap<>();
        addrMap.put(MyServer2.HOSTNAME, hostName);
        addrMap.put(MyServer2.PORT, port);
        return addrMap;
    }
}
