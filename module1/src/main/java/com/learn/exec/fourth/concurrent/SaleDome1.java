package com.learn.exec.fourth.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 轻量锁性能对比同步锁
 * 轻量锁
 *
 * @author Colm
 * @create 2019/10/25
 */
public class SaleDome1 {

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        List<Saler1> saler1s = new ArrayList<>();
        for(int i = 0; i < n; i++){
            saler1s.add(new Saler1("s1-" + i));
        }

        long start = System.currentTimeMillis();
        for (Saler1 s : saler1s){
            s.start();
        }
        for (Saler1 s : saler1s){
            s.join();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    // 票池，单例模式
    static class TicketPool1{
        private int tickets = 200000;
        private static TicketPool1 instance;
        private static ReentrantLock lock = new ReentrantLock();
        private TicketPool1(){ }

        public static TicketPool1 getInstance() {
            if(instance != null){
                return instance;
            }
            lock.lock();
            if(instance == null){
                instance = new TicketPool1();
            }
            lock.unlock();
            return instance;
        }

        // 出票
        public int getTicket(){
            boolean b = lock.tryLock(); // 上锁
            if(!b){ // 没拿到锁
//                return getTicket(); // 再次尝试出票
                return 0; // 代表没有拿到锁
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
    static class Saler1 extends Thread{
        private String name;
        public Saler1(String name){
            this.name = name;
        }
        public void run(){
            TicketPool1 pool = TicketPool1.getInstance();
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
