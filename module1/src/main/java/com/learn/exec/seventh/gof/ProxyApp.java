package com.learn.exec.seventh.gof;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 *
 * @author Colm
 * @create 2019/11/7
 */
public class ProxyApp {
    public static void main(String[] args){
        // 目标对象
        final WelcomeService target = new WelcomeServiceImpl();
        // 类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // 接口集合
        Class[] interfaces = {WelcomeService.class};
        // 处理器
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("InvocationHandler");
                Object res = method.invoke(target, args);
                return res;
            }
        };


        // 创建代理对象
        WelcomeService proxy = (WelcomeService) Proxy.newProxyInstance(loader, interfaces, handler);
        // 访问代理对象方法
        proxy.sayHello("proxy");
    }
}
