package com.learn.homework.second;

/**
 * 蜜蜂类
 *
 * @author Colm
 * @create 2019/10/15
 */
public class Bee extends Thread {
    private String name;
    private Bucket bucket;

    public Bee(String name, Bucket bucket){
        this.name = name;
        this.bucket = bucket;
    }

    public void run(){
        while(true){
            bucket.load(name);
        }
    }
}
