package com.learn.thread;

/**
 * @author Colm
 * @create 2019/10/12
 */
public class MainThread {

    public static void main(String[] args){
        Pool pool = new Pool();
        Producer p = new Producer("P", pool);
        Consumer c = new Consumer("C", pool);
        p.start();
        c.start();
    }
}
