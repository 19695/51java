package com.learn.exec.fifth;

import com.learn.exec.fifth.qq.util.PropertiesUtil;
import org.junit.Test;

/**
 * qq 工具类测试类
 *
 * @author Colm
 * @create 2019/10/30
 */
public class TestProperties {
    @Test
    public void testPropRead(){
        System.out.println(PropertiesUtil.getStringVal("qq.server.channel.blocking.mode"));
    }
}
