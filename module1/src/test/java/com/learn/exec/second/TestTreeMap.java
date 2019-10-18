package com.learn.exec.second;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Colm
 * @create 2019/10/8
 */
public class TestTreeMap {

    /**
     * 二叉树的遍历
     */
    @Test
    public void test5() throws Exception {
        TreeMap map = getTreeMap(10);
        getRoot(map); // 断点，看entry
        System.out.println(getEntryKey(getRoot(map)));
        // 前序
        prevOrderTravel(getRoot(map));
        System.out.println();
        // 中序
        middOrderTravel(getRoot(map));
        System.out.println();
        // 后序
        backOrderTravle(getRoot(map));
        System.out.println();
        // 层序
        levelOrderTravel(getRoot(map));
    }

    // 将层序遍历入参封装，与前中后序遍历看齐
    public static void levelOrderTravel(Map.Entry entry) throws Exception {
        List<Map.Entry> list = new ArrayList<>();
        list.add(entry);
        levelTravel(list);
    }

    // 层序遍历
    public static void levelTravel(List<Map.Entry> entries) throws Exception {
        if(!entries.isEmpty()){
//            list.size() == 0 和 list.isEmpty() 是什么关系？区别？
            for(Map.Entry entry : entries){
                System.out.print(getEntryKey(entry) + " ");
            }
            System.out.println();
            levelTravel(getChildren(entries));
        }
    }

    // 获取子节点，包括左右两个
    public static List getChildren(List<Map.Entry> entries) throws Exception {
        if(entries.size() != 0){
            List<Map.Entry> list = new ArrayList<>();
            for(Map.Entry entry : entries){
                if(getLeft(entry) != null){
                    list.add(getLeft(entry));
                }
                if(getRight(entry) != null){
                    list.add(getRight(entry));
                }
            }
            return list;
        }
        return null;
    }

    // 递归实现前序遍历—— 根左右
    public static void prevOrderTravel(Map.Entry entry) throws Exception {
        if(entry != null){
            System.out.print(getEntryKey(entry) + " ");
            prevOrderTravel(getLeft(entry));
            prevOrderTravel(getRight(entry));
        }
    }

    // 中序——左根右
    public static void middOrderTravel(Map.Entry entry) throws Exception {
        if(entry != null){
            middOrderTravel(getLeft(entry));
            System.out.print(getEntryKey(entry) + " ");
            middOrderTravel(getRight(entry));
        }
    }

    // 后序——左右根
    public static void backOrderTravle(Map.Entry entry) throws Exception {
        if(entry != null){
            backOrderTravle(getLeft(entry));
            backOrderTravle(getRight(entry));
            System.out.print(getEntryKey(entry) + " ");
        }
    }

    // 获取指定长度的 TreeMap
    public static TreeMap getTreeMap(int n){
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for(int i = 1; i <= n; i ++){
            treeMap.put(i, Integer.toString(i));
        }
        return treeMap;
    }

    // 获取节点的 key
    public static Object getEntryKey(Map.Entry entry) throws Exception {
        if(entry != null){
            Field f = entry.getClass().getDeclaredField("key");
            f.setAccessible(true);
            Object obj = f.get(entry);
            return obj;
        }
        return null;
    }

    // 获取根节点
    public static Map.Entry getRoot(Map map) throws Exception {
        Field f = map.getClass().getDeclaredField("root");
        f.setAccessible(true);
        Object obj = f.get(map);
        return (Map.Entry) obj;
    }

    // 获取右节点
    public static Map.Entry getRight(Map.Entry entry) throws Exception {
        return getEntry(entry, "right");
    }

    // 获取左节点
    public static Map.Entry getLeft(Map.Entry entry) throws Exception {
        return getEntry(entry, "left");
    }

    // 根据传入的 fieldName 获取 entry，获取左右节点的通用方法
    public static Map.Entry getEntry(Map.Entry entry, String fieldName) throws Exception {
        Field f = entry.getClass().getDeclaredField(fieldName) ;
        f.setAccessible(true);
        Object obj = f.get(entry) ;
        return (Map.Entry) obj;
    }

    /*
    for 各个部分执行顺序
     */
    @Test
    public void testFor(){
        boolean f = false ;
        // f = true , f != false
        System.out.println( f = true == false );

        System.out.println("==========");
        System.out.println(f);
        System.out.println("==========");
        int i = 1 ;
        for (System.out.println(1); f = true ; System.out.println(3), i++) {
            System.out.println(f);
            System.out.println(2);
            if(i == 10)
                break;
        }
    }

    /* **********************************
     *
     * TreeMap 添加流程
     *
     * **********************************
     */
    @Test
    public void test4(){
        Map<Integer, String> map = new TreeMap<>();
        map.put(0, "aaa"); // 断点 1，首次 root
        map.put(4, "aaa"); // 断点 2，添加到右侧
        map.put(8, "aaa"); // 断点 3，左旋将 4 设为root
        map.put(12, "aaa"); // 断点4，12 为 8 的右节点
        map.put(5, "aaa"); // 断点 5，5 为 8 的左节点
        map.put(2, "aaa"); // 断点 6，2 为 0 的右节点
        map.put(6, "aaa"); // 断点 7，6 为 5 的右节点
        map.put(7, "aaa"); // 断点 8，7 为 6 的右节点，5 对 6 左旋，6 为 8 的左节点
//        map.put(null, null); // key 不能为 null
        map.put(9, null); // 断点 9，9 为  12 左节点
        map.put(18, null); // 断点 10，18 为 12 的右节点
        map.put(20, null); // 断点 11，20 为 18 的右节点，4 绕 8 左旋
        travelMap(map);
    }

    /**
     * boolean
     */
    @Test
    public void test(){
        boolean a = Boolean.parseBoolean("true");
        System.out.println(a);
        a = Boolean.parseBoolean("false");
        System.out.println(a);
        a = Boolean.parseBoolean("aaaa");
        System.out.println(a);
//        boolean b = 0 ; // Error:(21, 21) java: 不兼容的类型: int无法转换为boolean
//        boolean c = 1 ; // Error:(22, 21) java: 不兼容的类型: int无法转换为boolean
//        System.out.println(b);
//        System.out.println(c);
        boolean aa = Boolean.valueOf("true");
        System.out.println(aa);
        aa = Boolean.valueOf("false");
        System.out.println(aa);
        aa = Boolean.valueOf("aaa");
        System.out.println(aa);
    }

    /**
     * TreeMap 排序，key没有实现 Comparable 的情况下
     * 使用匿名内部类的方式进行降序排列
     */
    @Test
    public void test3(){
        Map map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                MyKey k1 = (MyKey) o1;
                MyKey k2 = (MyKey) o2;
                return k2.key - k1.key ;
            }
        });
        map.put(new MyKey(2), "aaa");
        map.put(new MyKey(5), "bbb");
        map.put(new MyKey(3), "ccc");
        map.put(new MyKey(1), "ddd");
        map.put(new MyKey(4), "rrr");
        travelMap(map);
    }

    /**
     * TreeMap 排序，key没有实现 Comparable 的情况下
     * java.lang.ClassCastException: MyKey cannot be cast to java.lang.Comparable
     */
    @Test
    public void test2(){
        Map map = new TreeMap();
        map.put(new MyKey(2), "aaa");
        map.put(new MyKey(5), "bbb");
        map.put(new MyKey(3), "ccc");
        map.put(new MyKey(1), "ddd");
        map.put(new MyKey(4), "rrr");
        travelMap(map);
    }

    /**
     * TreeMap 排序，自然排序
     * key 本身可以 Comparable
     * public final class Integer extends Number implements Comparable<Integer> {...}
     */
    @Test
    public void test1(){
        Map map = new TreeMap();
        map.put(4, "aaa");
        map.put(1, "bbb");
        map.put(3, "ccc");
        map.put(2, "ddd");
        map.put(5, "rrr");
        travelMap(map);
    }

    /**
     * 遍历 map 打印
     * @param map
     */
    public void travelMap(Map map){
        for(Object object : map.entrySet()){
            Map.Entry entry = (Map.Entry) object;
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }
}
