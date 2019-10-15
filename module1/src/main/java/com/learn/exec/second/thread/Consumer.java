package com.learn.exec.second.thread;

/**
 * @author Colm
 * @create 2019/10/12
 */
public class Consumer extends Thread {
    private String cname;
    private Pool pool;

    public Consumer(String cname, Pool pool){
        this.cname = cname;
        this.pool = pool;
    }

    public void run (){
        int i ;
        while(true){
            i = pool.remove();
            System.out.println(cname + " --- " + i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
