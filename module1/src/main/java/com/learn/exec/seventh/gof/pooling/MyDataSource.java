package com.learn.exec.seventh.gof.pooling;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据源
 *
 * @author Colm
 * @create 2019/11/12
 */
public class MyDataSource extends DataSourceAdaptor {

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
