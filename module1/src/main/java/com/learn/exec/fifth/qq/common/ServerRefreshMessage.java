package com.learn.exec.fifth.qq.common;

import com.learn.exec.fifth.qq.util.ConversionUtil;

import java.io.ByteArrayOutputStream;

/**
 * 服务器端刷新好友
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ServerRefreshMessage extends BaseMessage {
    // 好友列表
    private byte[] friendBytes;

    public byte[] getFriendBytes() {
        return friendBytes;
    }

    public void setFriendBytes(byte[] friendBytes) {
        this.friendBytes = friendBytes;
    }

    @Override
    public int getMessageType() {
        return SERVER_TO_CLIENT_REFRESH_FRIENDS;
    }

    @Override
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 消息类型
        baos.write(getMessageType());
        // 好友列表消息长度
        baos.write(ConversionUtil.int2Bytes(friendBytes.length));
        // 好友列表消息
        baos.write(friendBytes);
        return baos.toByteArray();
    }
}
