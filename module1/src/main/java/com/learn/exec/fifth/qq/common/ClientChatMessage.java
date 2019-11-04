package com.learn.exec.fifth.qq.common;

import com.learn.exec.fifth.qq.util.ConversionUtil;

import java.io.ByteArrayOutputStream;

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

    @Override
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 消息类型: 1 字节
        baos.write(getMessageType());
        // 接收者地址长度
        baos.write(recvAddr.toCharArray().length);
        // 接收者地址
        baos.write(recvAddr.getBytes());
        // 消息内容长度
        baos.write(ConversionUtil.int2Bytes(message.toCharArray().length));
        // 消息内容
        baos.write(message.getBytes());
        return baos.toByteArray();
    }
}
