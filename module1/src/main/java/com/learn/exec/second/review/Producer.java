package com.learn.exec.second.review;

/**
 * 生产者
 * atguigu review
 *
 * @author Colm
 * @create 2019/10/15
 */
public class Producer implements Runnable {
    private Clerk clerk;
    private String pname;

    public Producer(String name, Clerk clerk){
        this.clerk = clerk;
        this.pname = name;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produce(pname);
        }
    }
}
