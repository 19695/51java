package com.learn.exec.fourth.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用可重入锁写一个买票类
 *
 * @author Colm
 * @create 2019/10/25
 */
public class SaleDome {

    public static void main(String[] args){
        new Saler("s1").start();
        new Saler("s2").start();
    }

    // 票池，单例模式
    static class TicketPool{
        private int tickets = 100;
        private static TicketPool instance;
        private static ReentrantLock lock = new ReentrantLock();
        private TicketPool(){ }

        public static TicketPool getInstance() {
            if(instance != null){
                return instance;
            }
            lock.lock();
            if(instance == null){
                instance = new TicketPool();
            }
            lock.unlock();
            return instance;
        }

        // 出票
        public int getTicket(){
            boolean b = lock.tryLock(); // 上锁
            if(!b){ // 没拿到锁
                return getTicket(); // 再次尝试出票
//                return 0; // 代表没有拿到锁
            }
            if(tickets == 0){ // 票卖完了
                lock.unlock(); // 解锁
                return -1;
            }
            int tmp = tickets;
            tickets--;
            lock.unlock(); // 解锁
            return tmp;
        }
    }

    // 卖票员
    static class Saler extends Thread{
        private String name;
        public Saler(String name){
            this.name = name;
        }
        public void run(){
            TicketPool pool = TicketPool.getInstance();
            for(;;){
                int res = pool.getTicket();
                if(res == -1) { // 没票了
                    break;
                }
                if(res == 0){
                    continue; // 没拿到锁
                } else {
                    System.out.printf("%s : %d\r\n", name, res); // 卖票
                }
            }
        }
    }
}
