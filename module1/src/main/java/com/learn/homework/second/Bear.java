package com.learn.homework.second;

/**
 * 熊类
 *
 * @author Colm
 * @create 2019/10/15
 */
public class Bear extends Thread {
    private String name;
    private Bucket bucket;

    public Bear(String name, Bucket bucket){
        this.name = name;
        this.bucket = bucket;
    }

    public void run(){
        while(true){
            bucket.eat(name);
        }
    }
}
