package com.learn.exec.fifth.qq.common;

/**
 * 客户端私聊消息
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ClientChatMessage extends BaseMessage {
    // 消息内容
    private String message;
    // 接收端地址
    private String recvAddr;
    // 发送者地址
    private String sendAddress;

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getRecvAddr() {
        return recvAddr;
    }

    public void setRecvAddr(String recvAddr) {
        this.recvAddr = recvAddr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getMessageType() {
        return CLIENT_TO_SERVER_CHAT;
    }
}
