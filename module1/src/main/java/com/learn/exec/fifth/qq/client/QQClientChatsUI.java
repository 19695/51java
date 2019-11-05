package com.learn.exec.fifth.qq.client;

import com.learn.exec.fifth.qq.common.ClientChatsMessage;
import com.learn.exec.fifth.qq.util.AddressUtil;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 客户端群聊界面
 * copy
 *
 * @author Colm
 * @create 2019/11/4
 */
public class QQClientChatsUI extends JFrame implements ActionListener {

    // 所有私聊窗口集合
    public Map<String, QQClientChatSingleUI> chatMap = new HashMap<>();

    // 通信线程
    public QQClientCommThread commThread;

    //历史聊天区
    private JTextArea taHistory;

    //好友列表
    private JList<String> lstFriends;

    //消息输入区
    private JTextArea taInputMessage;

    //发送按钮
    private JButton btnSend;

    //刷新好友列表按钮
    private JButton btnRefresh;

    public QQClientChatsUI() {
        init();
        this.setVisible(true);
    }

    /**
     * 初始化布局
     */
    private void init() {
        this.setTitle("QQClient");
        this.setBounds(100, 100, 800, 600);
        this.setLayout(null);

        //历史区
        taHistory = new JTextArea();
        taHistory.setBounds(0, 0, 600, 400);

        JScrollPane sp1 = new JScrollPane(taHistory);
        sp1.setBounds(0, 0, 600, 400);
        this.add(sp1);

        //lstFriends
        lstFriends = new JList<String>();
        lstFriends.setBounds(620, 0, 160, 400);
        // 添加鼠标监听事件
        lstFriends.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // 双击事件
                if(e.getClickCount() == 2){
                    String recvAddr = lstFriends.getSelectedValue();
                    // 若私聊窗口集合中没有
                    if(!chatMap.containsKey(recvAddr)){
                        chatMap.put(recvAddr, new QQClientChatSingleUI(recvAddr, commThread));
                    }
                    chatMap.get(recvAddr).setVisible(true);
                }
            }
        });
        this.add(lstFriends);

        //taInputMessage
        taInputMessage = new JTextArea();
        taInputMessage.setBounds(0, 420, 540, 160);
        this.add(taInputMessage);

        //btnSend
        btnSend = new JButton("发送");
        btnSend.setBounds(560, 420, 100, 160);
        btnSend.addActionListener(this);
        this.add(btnSend);

        //btnRefresh
        btnRefresh = new JButton("刷新");
        btnRefresh.setBounds(680, 420, 100, 160);
        btnRefresh.addActionListener(this);
        this.add(btnRefresh);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//                System.exit(-1);
                // 父窗口关闭时退出
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    /**
     * 按钮的点击事件
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // 发送按钮
        if(source == btnSend){
            String text = taInputMessage.getText();
            if(text != null && !text.trim().equalsIgnoreCase("")){
                ClientChatsMessage ccsm = new ClientChatsMessage();
                ccsm.setMessage(text);
                taInputMessage.setText("");
                try {
                    commThread.sendMessage(ccsm);
                    System.out.println("发送成功");
                } catch (Exception ex) {
                    System.out.println("发送失败" + ex.getMessage());
                }
            }
        }
    }

    /**
     * 刷新好友列表
     */
    public void refreshFriendList(List<String> list) {
        // 拿到自己的地址
        String localAddr = AddressUtil.getLocalAddr(commThread.getSocket());
        // 从列表中删除自己
        list.remove(localAddr);
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : list) {
            listModel.addElement(s);
            System.out.println(s);
        }
        lstFriends.setModel(listModel);
    }

    /**
     * 更新历史区域内容
     */
    public void updateHistory(String name ,String msg) {
        // 相当于 log
        System.out.println("历史区: " + name + " : " + msg);
        taHistory.append("[" + name + "]说:\r\n");
        String formatStr = msg.replace("\n", "\n\t");
        formatStr = "\t" + formatStr + "\r\n";
        taHistory.append(formatStr);
    }
}

