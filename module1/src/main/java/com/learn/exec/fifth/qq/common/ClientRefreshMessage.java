package com.learn.exec.fifth.qq.common;

/**
 * 客户端刷新好友
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ClientRefreshMessage extends BaseMessage {

    @Override
    public int getMessageType() {
        return CLIENT_TO_SERVER_REFRESH_FRIENDS;
    }
}
