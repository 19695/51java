package com.learn.exec.seventh.gof.pooling;

import com.learn.exec.util.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池
 *
 * @author Colm
 * @create 2019/11/12
 */
public class ConnectionPool {

    private static int INITICAL = 2;
    private static int MAXIMUM = 3;
    // 容器
    private List<Connection> pool = new LinkedList<>();
    // 原子量记录 busy_connection 忙连接个数
    private AtomicInteger busies = new AtomicInteger(0);
    // 单例
    private static ConnectionPool instance = null;

    /**
     * 构造器中初始化
     */
    private ConnectionPool(){
        initPool();
    }

    public static ConnectionPool getInstance(){
        if(instance != null){
            return instance;
        }
        synchronized (ConnectionPool.class){
            if(instance == null){
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    /**
     * 初始化池子
     */
    private void initPool() {
        for(int i = 0; i < INITICAL; i++){
            Connection conn = openNewWrappedConnection();
            if(conn != null){
                pool.add(conn);
            }
        }
    }

    /**
     * 开启装饰连接
     * @return
     */
    private Connection openNewWrappedConnection() {
        try {
            Class.forName(PropertyUtil.get("sql.driver"));
            String url = PropertyUtil.get("sql.url");
            String user = PropertyUtil.get("sql.user");
            String pass = PropertyUtil.get("sql.password");
            Connection conn = DriverManager.getConnection(url, user, pass);
            return new MyConnection(conn, this);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取连接
     * @return 从连接池中获取连接
     */
    public Connection getConnection() throws InterruptedException {
        // 连接池不为空，检出首元素
        if(!pool.isEmpty()){
            busies.incrementAndGet();
            return pool.remove(0);
        }
        // 已达最大连接数，进行等待。也可以使用 wait()
        while(busies.get() >= MAXIMUM){
            this.wait(10);
        }
        // 没有达到最大连接数的时候，打开一个新的连接，直接返回，在回收连接的时候加到线程池即可
        Connection conn = openNewWrappedConnection();
        busies.incrementAndGet();
        return conn;
    }

    // 归还连接到连接池
    public synchronized void backConnection(Connection conn){
        pool.add(conn);
        busies.decrementAndGet();
        this.notify();
    }
}
