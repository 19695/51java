package com.learn.exec.fifth.qq.common;

/**
 * 消息的基本类
 *
 * @author Colm
 * @create 2019/10/30
 */
public abstract class BaseMessage {
    /*
    客户端发给服务器
     */
    protected static final int CLIENT_TO_SERVER_CHATS = 0; // 群聊
    protected static final int CLIENT_TO_SERVER_CHAT = 1; // 私聊
    protected static final int CLIENT_TO_SERVER_REFRESH_FRIENDS = 2; // 刷新好友
    /*
    服务器发给客户端
     */
    protected static final int SERVER_TO_CLIENT_CHATS = 3;
    protected static final int SERVER_TO_CLIENT_CHAT = 4;
    protected static final int SERVER_TO_CLIENT_REFRESH_FRIENDS = 5;

    public abstract int getMessageType();
}
