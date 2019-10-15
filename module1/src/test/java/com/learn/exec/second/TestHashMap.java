package com.learn.exec.second;

import org.junit.Test;

import java.util.HashMap;

public class TestHashMap {

    /**
     * HashMap流程跟踪
     */
    @Test
    public void test(){
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "first"); // 断点
        hashMap.put(2, "second"); //断点
        hashMap.put(3, "third");
    }
}
