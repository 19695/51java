package com.learn.exec.fourth.gof.respchain;

/**
 * 需求工作人员
 *
 * @author Colm
 * @create 2019/10/23
 */
public class WorkerA extends Worker {

    @Override
    public void doWork() {
        System.out.println("A 做需求");
    }
}
