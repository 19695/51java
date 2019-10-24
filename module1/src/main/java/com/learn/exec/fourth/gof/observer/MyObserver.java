package com.learn.exec.fourth.gof.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 通知模式
 *
 * @author Colm
 * @create 2019/10/23
 */
public class MyObserver {
    public static void main(String[] args){
        Thief thief = new Thief();
        thief.addObserver(new PoliceB());
        thief.addObserver(new PoliceA());

        thief.steal("pc");
    }

    static class Thief extends Observable{
        public void steal(String str){
            System.out.println("偷了" + str);
            setChanged();
            notifyObservers();
        }
    }

    static class PoliceA implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("A 出警");
        }
    }

    static class PoliceB implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("B 出警");
        }
    }
}
