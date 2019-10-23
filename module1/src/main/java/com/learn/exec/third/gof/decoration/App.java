package com.learn.exec.third.gof.decoration;

/**
 * 装饰方法测试
 *
 * @author Colm
 * @create 2019/10/21
 */
public class App {

    public static void main(String[] args){
        Basic basic = new Basic();
        WrapperBasic wrapperBasic = new WrapperBasic(basic);
        wrapperBasic.introduce();
    }
}
