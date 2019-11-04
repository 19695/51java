package com.learn.exec.fifth.qq.common;

/**
 * 客户端群聊消息
 *
 * @author Colm
 * @create 2019/10/30
 */
public class ClientChatsMessage extends BaseMessage {
    // 消息内容
    private String message;
    // 发送者地址
    private String sendAddress;

    public String getSendAddress(){
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getMessageType() {
        return CLIENT_TO_SERVER_CHATS;
    }
}
