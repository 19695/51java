package com.learn.exec.fifth.qq.util;

import com.learn.exec.fifth.qq.common.BaseMessage;
import com.learn.exec.fifth.qq.common.ServerChatMessage;
import com.learn.exec.fifth.qq.common.ServerChatsMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 消息的工厂类
 *
 * @author Colm
 * @create 2019/11/1
 */
public class MessageFactory {

    // 从通道中解析客户端消息，转换成服务器端消息
    public static BaseMessage parseClientMessageFromChannel(SocketChannel sc) throws IOException {
        ByteBuffer buffer1 = ByteBuffer.allocate(1);
        int len = sc.read(buffer1);
        buffer1.flip();
        // 获取消息类型
        int msgType = buffer1.get(0);
        switch (msgType){
            // 群聊
            case BaseMessage.CLIENT_TO_SERVER_CHATS:
            // 加上作用域范围限制
            {
                ServerChatsMessage scsm = new ServerChatsMessage();
                // 四字节缓冲区
                ByteBuffer buffer4 = ByteBuffer.allocate(4);
                sc.read(buffer4);
                // 获取消息长度
                int msgLen = IntBytesConversion.bytes2Int(buffer4.array());
                // n 个字节缓冲区
                ByteBuffer bufferN = ByteBuffer.allocate(msgLen);
                sc.read(bufferN);
                // 组装服务器端消息
                scsm.setMessage(bufferN.array());
                // 组装服务器端发送者地址
                scsm.setSendAddr(RemoteAddrUtil.getRemoteAddrBytes(sc.socket()));
                return scsm;
            }
            // 私聊
            case BaseMessage.CLIENT_TO_SERVER_CHAT:
            {
                ServerChatMessage scm = new ServerChatMessage();
                buffer1.clear();
                sc.read(buffer1);
                buffer1.flip();
                // 获取接收者地址长度
                int recvAddrLen = buffer1.get(0);
                ByteBuffer bufferAddr = ByteBuffer.allocate(recvAddrLen);
                // 获取接收者地址
                sc.read(bufferAddr);
                bufferAddr.flip();
                // 组装服务器端接收者地址
                scm.setRecvAddr(bufferAddr.array());
                // 组装服务器端发送者地址
                scm.setSendAddr(RemoteAddrUtil.getRemoteAddrBytes(sc.socket()));
                // 四字节缓冲区
                ByteBuffer buffer4 = ByteBuffer.allocate(4);
                sc.read(buffer4);
                buffer4.flip();
                // 获取内容长度
                int msgLen = IntBytesConversion.bytes2Int(buffer4.array());
                ByteBuffer bufferN = ByteBuffer.allocate(msgLen);
                bufferN.flip();
                // 组装服务器端消息
                scm.setMessage(bufferN.array());
                return scm;
            }
            // 刷新好友
            case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS:
                break;
        }
        return null;
    }
}
