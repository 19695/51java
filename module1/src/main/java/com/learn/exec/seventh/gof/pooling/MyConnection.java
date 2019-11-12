package com.learn.exec.seventh.gof.pooling;

import java.sql.*;

/**
 * 装饰连接
 *
 * @author Colm
 * @create 2019/11/12
 */
public class MyConnection extends ConnectionAdaptor {

    private ConnectionPool pool;
    // 原生连接
    private Connection rawConnect; // raw connect -- 原始连接

    public MyConnection(Connection conn, ConnectionPool pool){
        this.rawConnect = conn;
        this.pool = pool;
    }
    @Override
    public Statement createStatement() throws SQLException {
        return rawConnect.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return rawConnect.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return rawConnect.prepareCall(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        rawConnect.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return rawConnect.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        rawConnect.commit();
    }

    @Override
    public void rollback() throws SQLException {
        rawConnect.rollback();
    }

    /**
     * 释放自己到连接池中
     * @throws SQLException
     */
    @Override
    public void close() throws SQLException {
        pool.backConnection(this);
    }
}
