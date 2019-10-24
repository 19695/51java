package com.learn.exec.fourth.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务器端
 * NIO 非阻塞模式是控制自己的这一端的，对方是不是非阻塞没关系
 *
 * @author Colm
 * @create 2019/10/24
 */
public class MyNIOServer {
    public static void main(String[] args) throws Exception {
        // 开启服务器套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定地址
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", 8888);
        ssc.bind(address);
        // 配置非阻塞
        ssc.configureBlocking(false);
        // 开启挑选器
        Selector selector = Selector.open();
        // 在挑选器中注册服务器通道
        ssc.register(selector, SelectionKey.OP_ACCEPT);


        for (; ; ) {
            try {
                // 开始挑选
//            System.out.println("开始挑选...");
                selector.select();
                // 得到挑选出来的集合
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    // 是否是可接受的
                    if (key.isAcceptable()) {
                        // 接受连接
                        SocketChannel sc = ssc.accept();
                        System.out.println("接受了新连接！");
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    // 是否可读
                    if (key.isReadable()) {
                        SocketChannel sc0 = (SocketChannel) key.channel();
                        System.out.println(getRemoteAddress(sc0) + " 可读了。");
                        String msg = readStringFromChannel(sc0);
                        System.out.println(getRemoteAddress(sc0) + " 发来了消息：" + msg);
                    }
                    // 是否可连接
                    if (key.isConnectable()) {
                        SocketChannel sc0 = (SocketChannel) key.channel();
                        System.out.println(getRemoteAddress(sc0) + "可连接");
                        sc0.finishConnect();
                    }
                    // 是否可写
                    if (key.isWritable()) {
                        SocketChannel sc0 = (SocketChannel) key.channel();
//                        System.out.println("可写了。");
                        Date now = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String dateStr = sdf.format(now);
                        ByteBuffer buffer = ByteBuffer.wrap(dateStr.getBytes());
                        sc0.write(buffer);
                    }
                    // 移除当前 key
                    it.remove();
                }
            } catch (Exception e){
//                e.printStackTrace();
            }
        }

    }

    /*
    从 SocketChannel 中读取 String
     */
    public static String readStringFromChannel(SocketChannel socketChannel) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(socketChannel.read(buffer) != 0){
            buffer.flip();
            baos.write(buffer.array(), 0 , buffer.limit());
            buffer.clear();
        }
        return new String(baos.toByteArray());
    }

    /*
    得到远程地址
     */
    public static String getRemoteAddress(SocketChannel socketChannel) throws IOException {
        InetSocketAddress address = (InetSocketAddress) socketChannel.getRemoteAddress();
        String hostName = address.getHostName();
        int port = address.getPort();
        return hostName + " : " + port;
    }
}
