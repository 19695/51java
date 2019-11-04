package com.learn.exec.fifth.qq.client;

import com.learn.exec.fifth.qq.common.BaseMessage;
import com.learn.exec.fifth.qq.common.ClientChatsMessage;
import com.learn.exec.fifth.qq.common.ClientRefreshMessage;
import com.learn.exec.fifth.qq.util.IConstants;
import com.learn.exec.fifth.qq.util.MessageFactory;

import java.io.IOException;
import java.net.Socket;

/**
 * client 通信线程
 *
 * @author Colm
 * @create 2019/11/4
 */
public class QQClientCommThread extends Thread {

    public QQClientChatsUI ui;

    private Socket sock;

    @Override
    public void run() {
        try{
            sock = new Socket(IConstants.QQ_CLIENT_SERVER_IP, IConstants.QQ_CLIENT_SERVER_PORT);
            for(;;){
                BaseMessage msg = MessageFactory.parseServerMessageFromSocket(sock);
                if(msg != null){
                    System.out.println("收到服务器消息");
                    switch (msg.getMessageType()){
                        // 群聊
                        case BaseMessage.CLIENT_TO_SERVER_CHATS:
                        {
                            ClientChatsMessage ccsm = (ClientChatsMessage) msg;
                            ui.updateHistory(ccsm.getSendAddress(), ccsm.getMessage());
                            break;
                        }
                        // 私聊
                        case BaseMessage.CLIENT_TO_SERVER_CHAT:
                        {
                            break;
                        }
                        // 更新好友列表
                        case BaseMessage.CLIENT_TO_SERVER_REFRESH_FRIENDS:
                        {
                            ClientRefreshMessage crm = (ClientRefreshMessage) msg;
                            ui.refreshFriendList(crm.getFriendList());
                            break;
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param msg
     * @throws Exception
     */
    public void sendMessage(BaseMessage msg) throws Exception {
        sock.getOutputStream().write(msg.popPack());
        sock.getOutputStream().flush();
    }
}
