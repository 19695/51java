package com.learn.exec.seventh.gof;

/**
 * 代理模式
 *
 * @author Colm
 * @create 2019/11/7
 */
public class WelcomeServiceImpl implements WelcomeService {
    @Override
    public void sayHello(String msg) {
        System.out.println("hello : " + msg);
    }
}
