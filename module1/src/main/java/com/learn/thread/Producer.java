package com.learn.thread;

/**
 * @author Colm
 * @create 2019/10/12
 */
public class Producer extends Thread {
    private String pname;
    private Pool pool;
    static int index = 1;

    public Producer(String pname, Pool pool){
        this.pname = pname;
        this.pool = pool;
    }

    public void run (){
        while(true){
            pool.add(index);
            System.out.println(pname + " +++ " + index++);
        }
    }
}
