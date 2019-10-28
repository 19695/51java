package com.learn.exec.fourth.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 轻量锁对比同步锁
 * 同步锁
 *
 * @author Colm
 * @create 2019/10/25
 */
public class SaleDome2 {

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        List<Saler2> saler2s = new ArrayList<>();
        for(int i = 0; i < n; i++){
            saler2s.add(new Saler2("s2-" + i));
        }

        long start = System.currentTimeMillis();
        for (Saler2 s : saler2s){
            s.start();
        }

        for (Saler2 s : saler2s){
            s.join();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    // 票池，单例模式
    static class TicketPool2{
        private int tickets = 200000;
        private static TicketPool2 instance;
        private TicketPool2(){ }

        public static TicketPool2 getInstance() {
            if(instance != null){
                return instance;
            }
            synchronized (TicketPool2.class){
                if(instance == null){
                    instance = new TicketPool2();
                }
            }
            return instance;
        }

        // 出票
        public int getTicket(){
            synchronized (TicketPool2.class){
                if(tickets == 0){ // 票卖完了
                    return -1;
                }
                int tmp = tickets;
                tickets--;
                return tmp;
            }
        }
    }

    // 卖票员
    static class Saler2 extends Thread{
        private String name;
        public Saler2(String name){
            this.name = name;
        }
        public void run(){
            TicketPool2 pool = TicketPool2.getInstance();
            for(;;){
                int res = pool.getTicket();
                if(res == -1) { // 没票了
                    break;
                } else {
                    System.out.printf("%s : %d\r\n", name, res); // 卖票
                }
            }
        }
    }
}
