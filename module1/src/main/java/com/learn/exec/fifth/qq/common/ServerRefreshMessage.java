package com.learn.exec.fifth.qq.common;

/**
 * 服务器端刷新好友
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ServerRefreshMessage extends BaseMessage {
    // 好友列表
    private byte[] friends;

    public byte[] getFriends() {
        return friends;
    }

    public void setFriends(byte[] friends) {
        this.friends = friends;
    }

    @Override
    public int getMessageType() {
        return SERVER_TO_CLIENT_REFRESH_FRIENDS;
    }
}
