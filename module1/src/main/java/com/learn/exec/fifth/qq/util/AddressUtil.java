package com.learn.exec.fifth.qq.util;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 获取远端地址
 *
 * @author Colm
 * @create 2019/11/1
 */
public class AddressUtil {
    // 获取远端主机
    public static String getRemoteHost(Socket sock){
        return ((InetSocketAddress)sock.getRemoteSocketAddress()).getAddress().getHostAddress();
    }

    // 获取远端端口
    public static int getRemotePort(Socket sock){
        return ((InetSocketAddress)sock.getRemoteSocketAddress()).getPort();
    }

    // 获取远端地址
    public static String getRemoteAddr(Socket sock){
        return getRemoteHost(sock) + " : " + getRemotePort(sock);
    }

    // 获取远端地址的字节数组
    public static byte[] getRemoteAddrBytes(Socket sock){
        return getRemoteAddr(sock).getBytes();
    }

    // 获取本地地址
    public static String getLocalAddr(Socket sock){
        /*
         * return sock.getLocalAddress() + " : " + sock.getLocalPort();
         * /127.0.0.1 : 56046
         * 多一个 /
         */
        return sock.getLocalAddress().getHostAddress() + " : " + sock.getLocalPort(); // 127.0.0.1 : 56046
    }
}
