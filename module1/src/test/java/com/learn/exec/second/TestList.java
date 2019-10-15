package com.learn.exec.second;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class TestList {

    /**
     * ArrayList contains
     */
    @Test
    public void test4(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.contains(1); // 断点
    }

    /**
     * 对比 ArrayList & LinkedList 读取速度
     * 十万级数据读取其中一个
     */
    @Test
    public void test3() {
        // 定义一个十万数据的 ArrayList
        ArrayList<Integer> arrayList = new ArrayList();
        int num = 100000;
        for(int i = 0; i < num; i++){
            arrayList.add(i);
        }
        // 读取 ArrayList
        long start = System.nanoTime(); // 纳秒
        arrayList.get(50000); // 断点 1
        long end = System.nanoTime(); // 纳秒
        System.out.println("arrayList.get(i);\t" + (end - start));

        // 定义一个十万数据的LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0; i < num; i++){
            linkedList.add(i);
        }
        // 读取 LinkedList
        start = System.nanoTime(); // 纳秒
        linkedList.get(50000); // 断点 2
        end = System.nanoTime(); // 纳秒
        System.out.println("linkedList.get(i);\t" + (end - start));
    }

    /**
     * 对比 ArrayList & LinkedList 读取速度
     * 十万级数据遍历读取
     */
    @Test
    public void test2() {
        // 定义一个十万数据的 ArrayList
        ArrayList<Integer> arrayList = new ArrayList();
        int num = 100000;
        for(int i = 0; i < num; i++){
            arrayList.add(i);
        }
        // 读取 ArrayList
        long start = System.currentTimeMillis();
        for(int i = 0; i < num; i++){
            arrayList.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("arrayList.get(i);\t" + (end - start));

        // 定义一个十万数据的LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0; i < num; i++){
            linkedList.add(i);
        }
        // 读取 LinkedList
        start = System.currentTimeMillis();
        for(int i = 0; i < num; i++){
            linkedList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("linkedList.get(i);\t" + (end - start));
    }

    /**
     * 对比 ArrayList & LinkedList 追加（尾部插入）速度
     */
    @Test
    public void test1() {
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
