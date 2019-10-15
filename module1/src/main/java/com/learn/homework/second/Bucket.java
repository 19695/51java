package com.learn.homework.second;

/**
 * @author Colm
 * @create 2019/10/15
 */
public class Bucket {
    private static final int MAX_CAPACITY = 50;
    private static final int THRESHOLD = 20;
    private static final int EMPTY_CAPACITY = 0;
    private int capacity = EMPTY_CAPACITY;

    public synchronized void load(String name) {
        // 当容量大于等于 20 时候给熊个吃的机会
        while(capacity >= THRESHOLD){
            // 当前容量大于等于最大容量的时候，进入等待
            while(capacity >= MAX_CAPACITY){
                tryCatchWait();
            }
            tryCatchSleep(1000L);
            capacity++;
            System.out.println(name + " +++ " + capacity);
            notifyAll();
            tryCatchWait();
        }
        capacity++;
        System.out.println(name + " +++ " + capacity);
        tryCatchWait();
    }

    public synchronized void eat(String name) {
        if(capacity >= 20){ // 大于等于 20 一口吃掉
            tryCatchSleep(100L);
            System.out.println(name + " --- " + capacity);
            capacity = 0;
            notifyAll();
        } else if(capacity <= 0){ // 小于等于 0，空的，进入等待
            tryCatchWait();
        } else { // 唤醒
            notifyAll();
        }
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
