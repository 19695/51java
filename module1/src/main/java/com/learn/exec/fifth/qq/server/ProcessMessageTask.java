package com.learn.exec.fifth.qq.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程的方式处理消息
 *
 * @author Colm
 * @create 2019/10/31
 */
public class ProcessMessageTask implements Runnable {
    // 将 key 以及和 key 绑定的 lock 通过 key 传入
    private SelectionKey key;

    public ProcessMessageTask(SelectionKey key){
        this.key = key;
    }

    @Override
    public void run() {
        // 获取套接字通道
        SocketChannel sc = (SocketChannel) key.channel();
        // 获取和 key 绑定的 lock
        ReentrantLock lock = (ReentrantLock) key.attachment();
        /*
            尝试拿到锁，拿不到就放弃
            lock.lock() 拿不到锁就等待，有可能拿到锁时通道已经被读取过了
         */
        boolean b = lock.tryLock();
        if(b){

            //todo 没做完的任务写上 todo 可以从 IDEA 的 TODO　工具窗口找到

            // 拿到锁以后要记得解锁
            lock.unlock();
        }
    }
}
