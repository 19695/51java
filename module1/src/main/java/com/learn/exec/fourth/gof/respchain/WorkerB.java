package com.learn.exec.fourth.gof.respchain;

/**
 * 开发工作人员
 *
 * @author Colm
 * @create 2019/10/23
 */
public class WorkerB extends Worker {

    @Override
    public void doWork() {
        System.out.println("B 写代码");
    }
}
