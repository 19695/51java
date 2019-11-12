package com.learn.exec.seventh.jdbc;

import com.learn.exec.util.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 事务隔离级别
 * 这个电脑没有数据库
 *
 * @author Colm
 * @create 2019/11/8
 */
public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        // 反射创建驱动
        Class.forName(PropertyUtil.get("sql.driver"));
        String url = PropertyUtil.get("sql.url");
        String user = PropertyUtil.get("sql.user");
        String pass = PropertyUtil.get("sql.password");
        // 获取连接
        Connection conn = DriverManager.getConnection(url, user, pass);
        // 关闭自动提交
        conn.setAutoCommit(false);
        // 修改事务隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery("SELECT * FROM table WHERE id = 1");
        if(res.next()){
            int age = res.getInt(1);
            System.out.println(age);
        }
        res.close();
        conn.commit();
        conn.close();
    }
}
