package com.learn.exec.fourth.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 体验线程池
 *
 * @author Colm
 * @create 2019/10/28
 */
public class ThreadPoolDemo {
    public static void main(String[] args){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        // 固定数量线程池
        ThreadPoolExecutor e = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        e.execute(r);
        e.execute(r);
        e.execute(r);
        e.execute(r);
        e.execute(r);
        e.execute(r);
//        e.submit(r);
        e.shutdown();
    }
}
