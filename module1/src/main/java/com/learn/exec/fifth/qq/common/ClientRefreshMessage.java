package com.learn.exec.fifth.qq.common;

import java.util.List;

/**
 * 客户端刷新好友
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ClientRefreshMessage extends BaseMessage {

    // 好友列表
    private List<String> friendList;

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

    @Override
    public int getMessageType() {
        return CLIENT_TO_SERVER_REFRESH_FRIENDS;
    }
}
