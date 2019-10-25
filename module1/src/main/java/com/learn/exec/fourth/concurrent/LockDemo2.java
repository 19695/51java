package com.learn.exec.fourth.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * ReentrantLock：独占锁
 *
 * @author Colm
 * @create 2019/10/25
 */
public class LockDemo2 {

    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();
        new Thread(){
            @Override
            public void run() {
                lock.lock(); // 断点
                System.out.println("aaa");
                lock.unlock();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
//                lock.lock(); // 断点
                boolean b = lock.tryLock();
                System.out.println("aaa");
                lock.unlock();
            }
        }.start();
    }
}
