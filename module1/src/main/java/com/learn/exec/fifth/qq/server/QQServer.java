package com.learn.exec.fifth.qq.server;

import com.learn.exec.fifth.qq.util.IConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * QQ 服务器
 *
 * @author Colm
 * @create 2019/10/30
 */
public class QQServer {
    /*
        启动服务器
     */
    public void start(){
        try {
            // 启动固定数量的线程池
            ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(IConstants.QQ_SERVER_THREAD_POOL_CORES);

            //  开启服务器套接字通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 设置通道阻塞模式
            ssc.configureBlocking(IConstants.QQ_SERVER_CHANNEL_BLOCKING_MODE);
            // 绑定服务器地址
            InetSocketAddress addr = new InetSocketAddress(IConstants.QQ_SERVER_BIND_HOST, IConstants.QQ_SERVER_BIND_PORT);
            ssc.bind(addr);
            // 开启挑选器
            Selector selector = Selector.open();
            // 将通道注册到挑选器
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            for(;;){
               // 开始挑选
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                // 遍历 key
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    try {
                        // 接受新连接，注册通道。只有服务器通道用 isAcceptable
                        if(key.isAcceptable()){
                            SocketChannel sc0 = ssc.accept();
                            // 设置通道阻塞模式
                            sc0.configureBlocking(IConstants.QQ_SERVER_CHANNEL_BLOCKING_MODE);
                            // 将通道注册到挑选器
                            SelectionKey key0 = sc0.register(selector, SelectionKey.OP_READ);
                            // 创建独占锁
                            ReentrantLock lock = new ReentrantLock();
                            // 将 key 和 lock 关联
                            key0.attach(lock);
                        }
                        // 常规通道
                        if(key.isReadable()){
                            // 将处理的过程交给线程池
                            pool.execute(new ProcessMessageTask(key));
                        }
                    } catch (Exception e){
                        // 出错时注销 key
                        key.cancel();
                    } finally {
                        // 无论成功还是失败都将 key 从迭代器中移除
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            // 抓取通道连接、挑选器注册异常
            e.printStackTrace();
        }
    }
}
