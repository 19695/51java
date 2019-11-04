package com.learn.exec.fifth.qq.server;

import com.learn.exec.fifth.qq.common.BaseMessage;
import com.learn.exec.fifth.qq.common.ServerRefreshMessage;
import com.learn.exec.fifth.qq.util.ConversionUtil;
import com.learn.exec.fifth.qq.util.IConstants;
import com.learn.exec.fifth.qq.util.RemoteAddrUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * QQ 服务器，单例模式
 *
 * @author Colm
 * @create 2019/10/30
 */
public class QQServer {

    //单例模式懒汉式
    private static QQServer instance;

    private QQServer(){

    }

    public static QQServer getInstance(){
        if(instance != null){
            return instance;
        }
        synchronized (QQServer.class){
            if(instance == null){
                instance = new QQServer();
            }
        }
        return instance;
    }

    // 所有客户端集合
    private HashMap<String, SocketChannel> allClients = new HashMap<>();

    /*
        启动服务器
     */
    public void start(){
        try {
            // 启动固定数量的线程池
            ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(IConstants.QQ_SERVER_THREAD_POOL_CORES);

            //  开启服务器套接字通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 设置通道阻塞模式
            ssc.configureBlocking(IConstants.QQ_SERVER_CHANNEL_BLOCKING_MODE);
            // 绑定服务器地址
            InetSocketAddress addr = new InetSocketAddress(IConstants.QQ_SERVER_BIND_HOST, IConstants.QQ_SERVER_BIND_PORT);
            ssc.bind(addr);
            // 开启挑选器
            Selector selector = Selector.open();
            // 将通道注册到挑选器
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            for(;;){
               // 开始挑选
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                // 遍历 key
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    try {

                        // 接受新连接，注册通道。只有服务器通道用 isAcceptable
                        if(key.isAcceptable()){
                            SocketChannel sc0 = ssc.accept();
                            System.out.println("有人上线了!!!");
                            // 设置通道阻塞模式
                            sc0.configureBlocking(IConstants.QQ_SERVER_CHANNEL_BLOCKING_MODE);
                            // 将通道注册到挑选器
                            SelectionKey key0 = sc0.register(selector, SelectionKey.OP_READ);
                            // 创建独占锁
                            ReentrantLock lock = new ReentrantLock();
                            // 将 key 和 lock 关联
                            key0.attach(lock);
                            // 得到远端地址字符串
                            String remoteAddr = RemoteAddrUtil.getRemoteAddr(sc0.socket());
                            // 将信息放置到 allClients 集合
                            allClients.put(remoteAddr, sc0);
                            // 广播好友列表
                            broadCastMessage(getFriendListMessage());
                        }

                        // 常规通道
                        if(key.isReadable()){
                            // 将处理的过程交给线程池
                            pool.execute(new ProcessMessageTask(key));
                        }
                    } catch (Exception e){
                        // 出错时注销 key
                        key.cancel();
                        // key 出现异常时被注销，即用户被下线。应该将用户从 allClients 集合移除
                        allClients.remove(RemoteAddrUtil.getRemoteAddr(((SocketChannel)key.channel()).socket()));
                    } finally {
                        // 无论成功还是失败都将 key 从迭代器中移除
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            // 抓取通道连接、挑选器注册异常
            e.printStackTrace();
        }
    }

    // 获取好友列表
    public List<String> getFriendsList(){
        return new ArrayList<String>(allClients.keySet());
    }

    // 返回通过串行化得到的好友列表字节数组
    public byte[] getFriendsBytes() throws IOException {
        return ConversionUtil.serialObject(getFriendsList());
    }

    // 向所有 client 广播消息
    public void broadCastMessage(BaseMessage msg){
        System.out.println("广播消息");
        for(SocketChannel sc : allClients.values()){
            try {
                sc.write(ByteBuffer.wrap(msg.popPack()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 转发消息(单发消息)
    public void forwardMessage(BaseMessage msg, String addr) throws Exception {
        // 从所有客户端中找到对应的 SocketChannel
        SocketChannel sc = allClients.get(addr);
        // 存在对应的 SocketChannel
        if(sc != null){
            sc.write(ByteBuffer.wrap(msg.popPack()));
        }
    }

    // 获取好友列表信息
    public ServerRefreshMessage getFriendListMessage() throws IOException {
        ServerRefreshMessage srm = new ServerRefreshMessage();
        srm.setFriendBytes(getFriendsBytes());
        return srm;
    }
}
