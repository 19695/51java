package com.learn.exec.fourth.concurrent;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * submit 是异步的，通过 get 实现同步的效果
 *
 * @author Colm
 * @create 2019/10/28
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        pool.submit(new MyRunnable("1")).get();
        pool.submit(new MyRunnable("2")).get();
        pool.submit(new MyRunnable("3")).get();
        pool.submit(new MyRunnable("4")).get();
//        pool.submit(new MyRunnable("1"));
//        pool.submit(new MyRunnable("2"));
//        pool.submit(new MyRunnable("3"));
//        pool.submit(new MyRunnable("4"));
        pool.shutdown();
    }

    static class MyRunnable implements Runnable{
        private String name;
        public MyRunnable(String name){
            this.name = name;
        }
        @Override
        public void run() {
            Random random = new Random();
            int i = random.nextInt(5) + 1;
            tcSleep(i);
            System.out.println(Thread.currentThread().getName()
                    + " : " + i + " : " + name);
        }
    }

    static void tcSleep(int i){
        try {
            Thread.sleep(( + 1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
