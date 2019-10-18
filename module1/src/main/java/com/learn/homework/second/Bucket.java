package com.learn.homework.second;

/**
 * 蜂蜜容器类
 * 打印前后 sleep 都影响 熊吃的数量
 * sleep 一般都是 50 吃
 * 不加  吃的数量更分散
 *
 * @author Colm
 * @create 2019/10/15
 */
public class Bucket {
    private static final int MAX_CAPACITY = 50;
    private static final int THRESHOLD = 20;
    private static final int EMPTY_CAPACITY = 0;
    private int capacity = EMPTY_CAPACITY;

    public synchronized void load(String name) {
        // 当前容量大于等于最大容量的时候，进入等待
        while(capacity >= MAX_CAPACITY){
            tryCatchWait();
        }
        capacity++;
        System.out.println(name + " +++ " + capacity);
        notify();
        // 不加 wait 大概率是一只蜜蜂干活，n-1只蜜蜂看着
        tryCatchWait();
    }

    public synchronized void clear(String name) {
        while(capacity < THRESHOLD){
            tryCatchWait();
        }
        System.out.println(name + " --- " + capacity);
        capacity = EMPTY_CAPACITY;
        notifyAll();
    }

    private void tryCatchWait(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tryCatchSleep(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
