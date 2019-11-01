package com.learn.exec.fifth.qq.util;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 获取远端地址
 *
 * @author Colm
 * @create 2019/11/1
 */
public class RemoteAddrUtil {
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
}
