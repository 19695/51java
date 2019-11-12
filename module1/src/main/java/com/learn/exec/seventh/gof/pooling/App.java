package com.learn.exec.seventh.gof.pooling;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Colm
 * @create 2019/11/12
 */
public class App {
    public static void main(String[] args){
        final DataSource dataSource = new MyDataSource();
        new Thread(){
            public void run(){
                try {
                    Connection conn = dataSource.getConnection(); // 断点
                    conn.close(); // 断点
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Connection conn = dataSource.getConnection(); // 断点
                    conn.close(); // 断点
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Connection conn = dataSource.getConnection(); // 断点
                    conn.close(); // 断点
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    Connection conn = dataSource.getConnection(); // 断点
                    conn.close(); // 断点
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
