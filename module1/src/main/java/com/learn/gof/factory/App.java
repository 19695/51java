package com.learn.gof.factory;

/**
 * @author Colm
 * @create 2019/10/21
 */
public class App {

    public static void main(String[] args){
        System.out.println(StaticFactory.newTVSet().getBrand());

        NonStaticFactory non = new NonStaticFactory();
        System.out.println(non.newTVSet().getColor());
    }
}
