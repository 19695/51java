package com.learn.exec.fourth.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 *
 * @author Colm
 * @create 2019/10/24
 */
public class MyNIOClient {
    public static void main(String[] args) throws Exception{
        // 打开套接字通道
        SocketChannel sc = SocketChannel.open();
        // 设置非阻塞
        sc.configureBlocking(false);
        // 连接到远程服务器
        InetSocketAddress address = new InetSocketAddress("localhost", 8888);
        sc.connect(address);
        // 打开筛选器
        Selector selector = Selector.open();
//        只有服务器才可以注册 OP_ACCEPT
//        SelectionKey key = sc.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_WRITE);
        // 注册
        SelectionKey key = sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE);

        int index = 1;

        while (true){
            selector.select();
            if(key.isConnectable()){
                System.out.println("可连接。");
                sc.finishConnect();
            }
            if(key.isWritable()){
                System.out.println("可写");
                String msg = "number" + index;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 发送信息给服务器
                sc.write(buffer);
                index++;
            }
            // 清空挑选集
            selector.selectedKeys().clear();
            Thread.sleep(1000);
        }

    }
}
