package com.learn.gof.decoration;

/**
 * Basic 类的装饰方法
 *
 * @author Colm
 * @create 2019/10/21
 */
public class WrapperBasic extends Basic {
    private Basic basic;

    public WrapperBasic(Basic basic){
        this.basic = basic;
    }

    public void introduce(){
        System.out.println("调用前修饰");
        basic.introduce();
        System.out.println("调用后修饰");
    }
}
