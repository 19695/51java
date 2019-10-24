package com.learn.exec.fourth.gof.respchain;

/**
 * 抽象类
 *
 * @author Colm
 * @create 2019/10/23
 */
public abstract class Worker {

    private Worker nextWorker;

    public Worker(){ }

    public Worker(Worker nextWorker){
        this.nextWorker = nextWorker;
    }

    public void setNextWorker(Worker nextWorker){
        this.nextWorker = nextWorker;
    }

    public void work(){
        doWork();
        if (nextWorker != null){
            nextWorker.work();
        }
    }

    public abstract void doWork();
}
