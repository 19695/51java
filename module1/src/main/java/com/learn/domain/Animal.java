package com.learn.domain;

public abstract class Animal {
    protected abstract void run();
    public abstract String cc();
    abstract int bb();
//    private abstract char aa();
//    抽象方法不能是private修饰，子类都访问不到，不能重写
    public Animal(String name){
        System.out.println(name);
    }
    public Animal(){

    }
}
