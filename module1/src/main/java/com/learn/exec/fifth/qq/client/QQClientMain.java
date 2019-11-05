package com.learn.exec.fifth.qq.client;

/**
 * QQ 客户端入口程序
 *
 * @author Colm
 * @create 2019/11/4
 */
public class QQClientMain {
    public static void main(String[] args){
        QQClientChatsUI ui = new QQClientChatsUI();
        QQClientCommThread thread = new QQClientCommThread(ui);
        thread.start();
        ui.commThread = thread;
    }
}
