package com.learn.exec.seventh.gof;

/**
 * 多接口代理
 *
 * @author Colm
 * @create 2019/11/7
 */
public class WelcomeServiceImpl2 implements WelcomeService, WelcomeService2 {
    @Override
    public void sayHello(String msg) {
        System.out.println("implement_1_hello : " + msg);
    }

    @Override
    public void sayHello2(String msg) {
        System.out.println("implement_2_hello : " + msg);
    }
}
