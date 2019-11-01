package com.learn.exec.fifth.qq;

import com.learn.exec.fifth.qq.util.IntBytesConversion;
import com.learn.exec.fifth.qq.util.PropertiesUtil;
import org.junit.Test;

/**
 * qq 工具类测试类
 *
 * @author Colm
 * @create 2019/10/30
 */
public class TestProperties {
    /*
        测试 PropertiesUtil 读取配置文件
     */
    @Test
    public void testPropRead(){
        System.out.println(PropertiesUtil.getStringVal("qq.server.channel.blocking.mode"));
    }

    /*
        测试 int bytes 互转功能
     */
    @Test
    public void testIntBytesConversion(){
        System.out.println(IntBytesConversion.bytes2Int(IntBytesConversion.int2Bytes(-1)));
    }
}
