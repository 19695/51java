package com.learn.exec.fifth.qq.util;

import com.learn.exec.fifth.qq.common.*;
import com.learn.exec.fifth.qq.server.QQServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

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
        // 如果没有获取到类型
        if(len != 1){
            return null;
        }
        buffer1.flip();
        // 获取消息类型
        int msgType = buffer1.get(0);
        switch (msgType){
            // 群聊
            case BaseMessage.CLIENT_TO_SERVER_CHATS:
            // 加上作用域范围限制
            {
                // 四字节缓冲区
                ByteBuffer buffer4 = ByteBuffer.allocate(4);
                sc.read(buffer4);
                // 获取消息长度
                int msgLen = ConversionUtil.bytes2Int(buffer4.array());
                // n 个字节缓冲区
                ByteBuffer bufferN = ByteBuffer.allocate(msgLen);
                sc.read(bufferN);
                // 组装服务器端消息
                ServerChatsMessage scsm = new ServerChatsMessage();
                scsm.setMessage(bufferN.array());
                // 组装服务器端发送者地址
                scsm.setSendAddr(AddressUtil.getRemoteAddrBytes(sc.socket()));
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
                scm.setSendAddr(AddressUtil.getRemoteAddrBytes(sc.socket()));
                // 四字节缓冲区
                ByteBuffer buffer4 = ByteBuffer.allocate(4);
                sc.read(buffer4);
                buffer4.flip();
                // 获取内容长度
                int msgLen = ConversionUtil.bytes2Int(buffer4.array());
                ByteBuffer bufferN = ByteBuffer.allocate(msgLen);
                bufferN.flip();
                // 组装服务器端消息
                scm.setMessage(bufferN.array());
                //todo print > scm.msg
                System.out.println(scm.getMessage());
                return scm;
            }

            // 刷新好友
            case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS:
            {
                ServerRefreshMessage srm = new ServerRefreshMessage();
                /*
                 通过 QQServer 单例模式可以直接操作 QQServer 中的好友列表（allClients）
                 QQServer 可能被很多地方调用，每次调用都新建一个不合适
                 使用串行化
                 */
                srm.setFriendBytes(QQServer.getInstance().getFriendsBytes());
                return srm;
            }
        }
        return null;
    }

    // 从 socket 中解析服务器消息,转换成客户端消息
    public static BaseMessage parseServerMessageFromSocket(Socket sock) throws IOException, ClassNotFoundException {
        // 从 socket 中获取 InputStream
        InputStream in = sock.getInputStream();
        // 一个字节用来存储消息类型
        byte[] byte1 = new byte[1];
        in.read(byte1);
        // 消息类型
        int msgType = byte1[0];
        switch (msgType){
            // 群聊
            case BaseMessage.SERVER_TO_CLIENT_CHATS :
            {
                // 发送者地址长度
                in.read(byte1);
                int sendAddrLen = byte1[0];
                // 发送者地址
                byte[] sendAddrBytes = new byte[sendAddrLen];
                in.read(sendAddrBytes);
                String sendAddr = new String(sendAddrBytes);
                // 消息内容长度
                byte[] bytes4 = new byte[4];
                in.read(bytes4);
                int msgLen = ConversionUtil.bytes2Int(bytes4); // 内容长度字节数组转为整数
                // 消息内容
                byte[] bytesN = new byte[msgLen];
                in.read(bytesN);
                String msg = new String(bytesN);
                // 转为客户端群聊消息, 拼装各属性
                ClientChatsMessage ccsm = new ClientChatsMessage();
                ccsm.setSendAddress(sendAddr);
                ccsm.setMessage(msg);
                return ccsm;
            }
            // 私聊
            case BaseMessage.SERVER_TO_CLIENT_CHAT :
            {
                // 发送者地址长度
                in.read(byte1);
                int sendAddrLen = byte1[0];
                // 发送者地址
                byte[] sendAddrBytes = new byte[sendAddrLen];
                in.read(sendAddrBytes);
                String sendAddr = new String(sendAddrBytes);
                // 消息内容长度
                byte[] bytes4 = new byte[4];
                in.read(bytes4);
                int msgLen = ConversionUtil.bytes2Int(bytes4); // 内容长度字节数组转为整数
                // 消息内容
                byte[] bytesN = new byte[msgLen];
                in.read(bytesN);
                String msg = new String(bytesN);
                // 转为客户端群聊消息, 拼装各属性
                ClientChatMessage ccm = new ClientChatMessage();
                ccm.setSendAddress(sendAddr);
                ccm.setMessage(msg);
                return ccm;
            }
            // 好友列表
            case BaseMessage.SERVER_TO_CLIENT_REFRESH_FRIENDS :
            {
                // 好友列表消息长度
                byte[] bytes4 = new byte[4];
                in.read(bytes4);
                int len = ConversionUtil.bytes2Int(bytes4);
                // 好友列表消息
                byte[] bytesN = new byte[len];
                in.read(bytesN);
                /*
                    服务器端使用串行化将好友列表信息进行传输
                    客户端使用反串行化将好友列表信息进行读取
                 */
                List<String> friendList = (List<String>) ConversionUtil.deserialBytes(bytesN);
                ClientRefreshMessage crm = new ClientRefreshMessage();
                crm.setFriendList(friendList);
                return crm;
            }
        }
        return null;
    }
}
