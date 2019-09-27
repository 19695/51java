package com.learn.second;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestList {

    /**
     * 对比 ArrayList & LinkedList 追加（尾部插入）速度
     */
    @Test
    public void test1(){
        ArrayList<Integer> arrayList = new ArrayList();
        int num = 1000000;
        long start = System.currentTimeMillis();
        for(int i = 0; i < num; i++){
            arrayList.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        LinkedList<Integer> linkedList = new LinkedList<>();
        start = System.currentTimeMillis();
        for(int i = 0; i < num; i++){
            linkedList.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 调试模式下对LinkedList的add(element)和add(index, element)
     * 进行跟踪流程学习
     */
    @Test
    public void testLinkedList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        /*
        先对int装箱得到Integer，泛型参数是引用类型不能是基本类型（primitive type）
         */
        linkedList.add(1); // 断点 1
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(0, 1111); // 断点 2
    }

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
