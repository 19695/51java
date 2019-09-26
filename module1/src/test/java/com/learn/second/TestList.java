package com.learn.second;

import org.junit.Test;

import java.util.ArrayList;

public class TestList {
    /**
     * 调试模式下对 ArrayList 的添加原理及扩容原理跟踪流程学习
     */
    @Test
    public void testArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        /*
        先对int装箱得到Integer，泛型参数是引用类型不能是基本类型（primitive type）

         */
        arrayList.add(1); // 断点 1
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11); // 断点 2
    }
}
