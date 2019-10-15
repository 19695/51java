package com.learn.exec.second.review;

/**
 * 生产者消费和
 * atguigu review
 *
 * @author Colm
 * @create 2019/10/15
 */
public class ProducerConsumer {
    public static void main(String[] args){
        Clerk clerk = new Clerk();
        Producer p1 = new Producer("p1", clerk);
        Consumer c1 = new Consumer("c1", clerk);

        Thread proThread = new Thread(p1);
        Thread conThread = new Thread(c1);
        proThread.setName("producerThread");
        conThread.setName("consumerThread");
        proThread.start();
        conThread.start();
    }
}
