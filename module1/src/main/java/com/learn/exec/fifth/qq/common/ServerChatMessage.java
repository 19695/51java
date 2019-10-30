package com.learn.exec.fifth.qq.common;

/**
 * 服务器端私聊
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ServerChatMessage extends BaseMessage {
    // 消息内容
    private byte[] message;
    // 发送者地址
    private byte[] sendAddr;

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public byte[] getSendAddr() {
        return sendAddr;
    }

    public void setSendAddr(byte[] sendAddr) {
        this.sendAddr = sendAddr;
    }

    @Override
    public int getMessageType() {
        return SERVER_TO_CLIENT_CHAT;
    }
}
