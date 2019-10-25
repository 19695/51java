package com.learn.exec.fourth.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入锁
 * ReentrantReadWriteLock：读写锁
 *      readLock
 *      writeLock
 *
 * @author Colm
 * @create 2019/10/25
 */
public class LockDemo {

    public static void main(String[] args){
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        new Thread(){
            @Override
            public void run() {
                rwLock.readLock().lock(); // 断点
                System.out.println("aaa");
                rwLock.readLock().unlock();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                rwLock.writeLock().lock(); // 断点
                System.out.println("aaa");
                rwLock.writeLock().unlock();
            }
        }.start();
    }
}
