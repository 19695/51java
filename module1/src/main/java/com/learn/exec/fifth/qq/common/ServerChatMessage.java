package com.learn.exec.fifth.qq.common;

import com.learn.exec.fifth.qq.util.ConversionUtil;

import java.io.ByteArrayOutputStream;

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
    // 接收者地址
    private byte[] recvAddr;

    public byte[] getRecvAddr() {
        return recvAddr;
    }

    public void setRecvAddr(byte[] recvAddr) {
        this.recvAddr = recvAddr;
    }

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

    // 组装报文
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 消息类型
        baos.write(getMessageType());
        // 发送者地址长度
        baos.write(sendAddr.length);
        // 发送者地址
        baos.write(sendAddr);
        // 消息长度
        baos.write(ConversionUtil.int2Bytes(message.length));
        // 消息内容
        baos.write(message);
        return baos.toByteArray();
    }
}
