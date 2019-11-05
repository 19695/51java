package com.learn.exec.fifth.qq.util;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 属性工具类
 *
 * @author Colm
 * @create 2019/10/30
 */
public class PropertiesUtil {
    private static Properties prope;
    static { // 通过类加载器来加载
        try {
            /*
            从类路径下加载
                > resources、java 是类路径的根
             */
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
            prope = new Properties();
            prope.load(in);
        } catch (Exception e){
            System.out.println("配置文件解析异常" + new Date().toString());
        }
    }

    /**
     * 返回字符串值
     * @param name
     * @return
     */
    public static String getStringVal(String name){
        return prope.getProperty(name);
    }

    /**
     * 返回整型值
     * @param name
     * @return
     */
    public static int getIntVal(String name){
        try {
            return Integer.parseInt(prope.getProperty(name));
        } catch (Exception e) {
            System.out.println("int 类型转换异常");
        }
        return 0;
    }

    /**
     * 返回 bool 值
     * @param name
     * @return
     */
    public static boolean getBooleanVal(String name){
        try {
            return prope.getProperty(name).equalsIgnoreCase("true");
        } catch (Exception e){
            System.out.println("boolean 类型转换异常");
        }
        return false; // 获取失败后，默认给 false
    }

    /**
     * 返回 long 型值
     */
    public static long getLongVal(String name){
        try {
            return Long.parseLong(prope.getProperty(name));
        } catch (Exception e){
            System.out.println("long 类型转换异常");
        }
        return 0;
    }
}
