package com.learn.exec.third.gof.singleton;

/**
 * 单例模式——懒汉式
 *
 * @author Colm
 * @create 2019/10/21
 */
public class LazySingleton {

    // 测试
    public static void main(String[] args){
        new Thread(){
            public void run() {
                LazySingleton ls = LazySingleton.getInstance(); // breakpoint
                System.out.println(ls);
            }
        }.start();
        new Thread(){
            public void run() {
                    LazySingleton ls = LazySingleton.getInstance(); // breakpoint
                System.out.println(ls);
            }
        }.start();
    }

    // 单例 懒汉式
    private static LazySingleton instance;

    private LazySingleton(){ }

    // 同步代码块
    public static LazySingleton getInstance(){
        if(instance != null ){
            return instance;
        }
        synchronized (LazySingleton.class){
            if(instance == null){
                instance = new LazySingleton();
            }
        }
        return instance;
    }

    // 同步方法效率不高
/*    public synchronized static LazySingleton getInstance(){
        if(instance == null){ // breakpoint
            instance = new LazySingleton();
        }
        return instance;
    }*/
}
