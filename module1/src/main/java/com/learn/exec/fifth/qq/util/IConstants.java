package com.learn.exec.fifth.qq.util;

/**
 * 常量接口
 *
 * @author Colm
 * @create 2019/10/31
 */
public class IConstants {
    // 通道的阻塞模式
    private static final String QQ_SERVER_CHANNEL_BLOCKING_MODE_NAME = "qq.server.channel.blocking.mode";
    public static final boolean QQ_SERVER_CHANNEL_BLOCKING_MODE = PropertiesUtil.getBooleanVal(QQ_SERVER_CHANNEL_BLOCKING_MODE_NAME);

    // 服务器主机地址
    private static final String QQ_SERVER_BIND_HOST_NAME = "qq.server.bind.host";
    public static final String QQ_SERVER_BIND_HOST = PropertiesUtil.getStringVal(QQ_SERVER_BIND_HOST_NAME);

    // 服务器端口号
    private static final String QQ_SERVER_BIND_PORT_NAME = "qq.server.bind.port";
    public static final int QQ_SERVER_BIND_PORT = PropertiesUtil.getIntVal(QQ_SERVER_BIND_PORT_NAME);

    // 线程池核心数
    private static final String QQ_SERVER_THREAD_POOL_CORES_NAME = "qq.server.thread.pool.cores";
    public static final int QQ_SERVER_THREAD_POOL_CORES = PropertiesUtil.getIntVal(QQ_SERVER_THREAD_POOL_CORES_NAME);

    // 客户端配置的服务器 ip
    private static final String QQ_CLIENT_SERVER_IP_NAME = "qq.client.server.ip";
    public static final String QQ_CLIENT_SERVER_IP = PropertiesUtil.getStringVal(QQ_CLIENT_SERVER_IP_NAME);

    // 客户端配置的服务器 port
    private static final String QQ_CLIENT_SERVER_PORT_NAME = "qq.client.server.port";
    public static final int QQ_CLIENT_SERVER_PORT = PropertiesUtil.getIntVal(QQ_CLIENT_SERVER_PORT_NAME);
}
