package com.learn.gof.singleton;

/**
 * 单例模式——饿汉式
 *
 * @author Colm
 * @create 2019/10/21
 */
public class HungrySingleton {

    // 测试
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                HungrySingleton hs = HungrySingleton.getInstance();
                System.out.println(hs);
            }
        }.start();
        new Thread(){
            public void run(){
                HungrySingleton hs = HungrySingleton.getInstance();
                System.out.println(hs);
            }
        }.start();
    }

    // 单例 饿汉式
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){ }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
