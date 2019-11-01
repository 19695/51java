package com.learn.exec.fifth.qq.util;

/**
 * int / bytes 互转工具类
 *
 * @author Colm
 * @create 2019/11/1
 */
public class IntBytesConversion {
    // int to bytes
    public static byte[] int2Bytes(int num){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (num >> 24);
        bytes[1] = (byte) (num >> 16);
        bytes[2] = (byte) (num >> 8);
        bytes[3] = (byte) (num >> 0);
        return bytes;
    }

    // bytes to int
    public static int bytes2Int(byte[] bytes){
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff) << 0;
    }
}
