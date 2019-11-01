package com.learn.exec.fifth.qq.util;

import java.io.*;

/**
 * 类型转换工具类
 *
 * @author Colm
 * @create 2019/11/1
 */
public class ConversionUtil {
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

    /*
        serialize + deserialize = deeply_copy
     */

    // serialize
    public static byte[] serialObject(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        baos.close();
        return baos.toByteArray();
    }

    // deserialize
    public static Object deserialBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        ois.close();
        bais.close();
        return obj;
    }
}
