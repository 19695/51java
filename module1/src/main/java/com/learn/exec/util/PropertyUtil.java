package com.learn.exec.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Colm
 * @create 2019/11/12
 */
public class PropertyUtil {
    private static Properties properties;
    static {
        try {
            InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            System.out.println("db.properties loading exception !");
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}

/*
private static Properties prope;
static { // 通过类加载器来加载
    try {
        // 从类路径下加载
        //  > resources、java 是类路径的根
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
        prope = new Properties();
        prope.load(in);
    } catch (Exception e){
        System.out.println("配置文件解析异常" + new Date().toString());
    }
}
*/
