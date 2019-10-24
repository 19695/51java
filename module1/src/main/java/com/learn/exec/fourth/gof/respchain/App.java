package com.learn.exec.fourth.gof.respchain;

/**
 * @author Colm
 * @create 2019/10/23
 */
public class App {
    public static void main(String[] args){
        WorkerA a = new WorkerA();
        WorkerB b = new WorkerB();
        WorkerC c = new WorkerC();

        a.setNextWorker(b);
        b.setNextWorker(c);

        Program p = new Program();
        p.handledBy(a);
    }
}
