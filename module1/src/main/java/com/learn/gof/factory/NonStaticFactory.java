package com.learn.gof.factory;

/**
 * 非静态工厂方法
 *
 * @author Colm
 * @create 2019/10/21
 */
public class NonStaticFactory {

    public TVSet newTVSet(){
        TVSet tvSet = new TVSet();
        tvSet.setBrand("xiaomi");
        tvSet.setSize(72.3);
        tvSet.setColor("white");
        return tvSet;
    }

}
