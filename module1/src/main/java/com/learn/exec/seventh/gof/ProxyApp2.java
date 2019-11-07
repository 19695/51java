package com.learn.exec.seventh.gof;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 多接口代理
 *
 * @author Colm
 * @create 2019/11/7
 */
public class ProxyApp2 {
    public static void main(String[] args){
        // 目标类
        final WelcomeServiceImpl2 target = new WelcomeServiceImpl2();
        // 类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // 接口集合
        Class[] interfaces = {WelcomeService.class, WelcomeService2.class};
        // 处理器
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.nanoTime();
                Object res = method.invoke(target, args);
                long dur = System.nanoTime() - start;
                System.out.println(method.getName() + " 耗时：" + dur);
                return res;
            }
        };
        // 创建代理对象
        WelcomeService proxy = (WelcomeService) Proxy.newProxyInstance(loader, interfaces, handler);
        // 访问代理对象的方法
        proxy.sayHello("WelcomeService");
        ((WelcomeService2)proxy).sayHello2("WelcomeService2");

        proxy.sayHello("WelcomeService");
        ((WelcomeService2)proxy).sayHello2("WelcomeService2");
        proxy.sayHello("WelcomeService");
        ((WelcomeService2)proxy).sayHello2("WelcomeService2");
        proxy.sayHello("WelcomeService");
        ((WelcomeService2)proxy).sayHello2("WelcomeService2");
        proxy.sayHello("WelcomeService");
        ((WelcomeService2)proxy).sayHello2("WelcomeService2");
    }
}
