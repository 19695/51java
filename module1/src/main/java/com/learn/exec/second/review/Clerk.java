package com.learn.exec.second.review;

/**
 * 售货员
 * atguigu review
 *
 * @author Colm
 * @create 2019/10/15
 */
public class Clerk {
    private static int MAX = 20;
    int product;

    public synchronized void produce(String pname){
        if(product >= MAX){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
//            product++;
            System.out.println(pname + " : " + Thread.currentThread().getName() + " +++ " + ++product);
            notifyAll();
        }
    }

    public synchronized void consume(String cname){
        if(product <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(cname + " : " + Thread.currentThread().getName() + " --- " + product--);
//            product--;
            notifyAll();
        }
    }
}
