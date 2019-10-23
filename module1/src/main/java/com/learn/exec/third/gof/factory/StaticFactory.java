package com.learn.exec.third.gof.factory;

/**
 * 静态工厂方法
 *
 * @author Colm
 * @create 2019/10/21
 */
public class StaticFactory {

    public static TVSet newTVSet(){
        TVSet tvSet = new TVSet();
        tvSet.setBrand("xiaomi");
        tvSet.setSize(72.3);
        tvSet.setColor("white");
        return tvSet;
    }

}
