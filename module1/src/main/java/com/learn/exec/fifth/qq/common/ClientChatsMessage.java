package com.learn.exec.fifth.qq.common;

import com.learn.exec.fifth.qq.util.ConversionUtil;

import java.io.ByteArrayOutputStream;

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

    @Override
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /*
            Writes the specified byte to this byte array output stream.
            buf[count] = (byte) b;
            将 int 强转 byte
         */
        // 消息类型: 1 字节
        baos.write(getMessageType());
        // 消息长度: 4 字节
        /*
//          baos.write(ConversionUtil.int2Bytes(message.toCharArray().length));
            使用这个方法 message.toCharArray().length 会出现中文显示的不全及乱码问题
         */
        baos.write(ConversionUtil.int2Bytes(message.getBytes().length));
        // 消息内容
        baos.write(message.getBytes());
        return baos.toByteArray();
    }
}
