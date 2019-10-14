package com.learn.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Colm
 * @create 2019/10/12
 */
public class Pool {
    private List<Integer> pool = new LinkedList<>();

    private static final int MAX = 100;

    public synchronized void add(Integer i) {
        while(pool.size() >= MAX){
            try {
//                Thread.currentThread().wait();
                this.wait();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        pool.add(i);
        this.notify();
    }

    public synchronized Integer remove() {
        while(pool.isEmpty()){
            try {
//                Thread.currentThread().wait();
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.notify();
        return pool.remove(0);
    }
}
