package com.learn.exec.third.gof.adaptor;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 适配器
 *
 * @author Colm
 * @create 2019/10/21
 */
public class App {
    public static void main(String[] args){
        JFrame jf = new JFrame();
//        jf.setBounds(0, 0, 1920, 1080);
        jf.setBounds(0, 0, 1080, 720);
        jf.setTitle("title");
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(-1);
            }
        });
        jf.setVisible(true);
    }
}
