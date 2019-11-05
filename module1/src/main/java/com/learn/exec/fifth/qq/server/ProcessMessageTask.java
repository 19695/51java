package com.learn.exec.fifth.qq.server;

import com.learn.exec.fifth.qq.common.BaseMessage;
import com.learn.exec.fifth.qq.common.ServerChatMessage;
import com.learn.exec.fifth.qq.util.ConversionUtil;
import com.learn.exec.fifth.qq.util.MessageFactory;
import com.learn.exec.fifth.qq.util.AddressUtil;

import java.io.IOException;
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

        QQServer server = QQServer.getInstance();
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
            try {
                BaseMessage msg = MessageFactory.parseClientMessageFromChannel(sc);
                if (msg != null) {
                    switch (msg.getMessageType()) {
                        // 群聊
                        case BaseMessage.SERVER_TO_CLIENT_CHATS:
                            server.broadCastMessage(msg);
                            break;
                        // 私聊
                        case BaseMessage.SERVER_TO_CLIENT_CHAT:
                            server.forwardMessage(msg, new String(((ServerChatMessage) msg).getRecvAddr()));
                            break;
                        // 刷新好友列表
                        case BaseMessage.SERVER_TO_CLIENT_REFRESH_FRIENDS:
                            server.forwardMessage(msg, AddressUtil.getRemoteAddr(sc.socket()));
                            break;
                    }
                }
            } catch (IOException ioe){
                String addr = AddressUtil.getRemoteAddr(sc.socket());
                //todo print > 远端链接已关闭
                System.out.println(addr + " - 远端链接已关闭!");
                server.removeClient(addr);
                server.broadcastFriendList();
                //todo 停下已关闭链接的线程
                Thread.currentThread().stop();
            } catch (Exception e){
                e.printStackTrace();
            }

            // 拿到锁以后要记得解锁
            lock.unlock();
        }
    }
}
