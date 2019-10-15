package com.learn.exec.second;

import org.junit.Test;

/**
 * @author Colm
 * @create 2019/10/12
 */
public class TestThread {

    /*
    用 main 方法测试
     */
    public static void main(String[] args) {
        Saler s1 = new Saler("s1") ;
        Saler s2 = new Saler("s2") ;
        s1.start();
        s2.start();
    }

    static class Saler extends Thread {
        public static Object lock = new Object() ;
        public String sname ; // 千万别写 static
        public static int tickets = 100 ;

        public Saler (String sname) {
            this.sname = sname ;
        }

        public void run() {
            while(true) {
                synchronized (lock) {
                    if(tickets > 0) {
                        System.out.println(sname + " : " + tickets);
                        tickets -- ;
                        Thread.yield();
                    } else {
                        break;
                    }
                    notifyAll();
                }
            }
        }

//        public void run(){
            /*while(tickets > 0) {
                System.out.println(sname + " : " + tickets);
                tickets -- ;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }*/
            /*while(true) {
                if(tickets > 0) {
                    System.out.println(sname + " : " + tickets);
                    tickets-- ;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread.yield();
                } else {
                    break;
                }
            }*/
            /*while(true) {
                int tmp = tickets ;
                if(tmp > 0) {
                    System.out.println(sname + " : " + tmp);
                    tickets-- ;
                    Thread.yield();
                } else {
                    break ;
                }
            }*/
//        }
    }

    /*
    主线程中使用 Thread.currentThread().join();
    可以让子线程顺利执行，但是不会自己停下
     */
    @Test
    public void testJoin() throws InterruptedException {
        Saler s1 = new Saler("s1") ;
        Saler s2 = new Saler("s2") ;
        s1.start();
        s2.start();
        Thread.currentThread().join();
    }

    /*
    idea 多线程用单元测试啥都不输出就结束了
    记得 myeclipse 多线程用单元测试做也有问题
    好像是单元测试对多线程的支持不好
     */
    @Test
    public void test() {
        Saler s1 = new Saler("s1") ;
        Saler s2 = new Saler("s2") ;
        s1.start();
        s2.start();
    }
}
