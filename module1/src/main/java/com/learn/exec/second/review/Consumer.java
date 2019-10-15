package com.learn.exec.second.review;

/**
 * 消费者
 * atguigu review
 *
 * @author Colm
 * @create 2019/10/15
 */
public class Consumer implements Runnable {
    private Clerk clerk;
    private String cname;

    public Consumer(String name, Clerk clerk){
        this.clerk = clerk;
        this.cname = name;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consume(cname);
        }
    }
}
