package com.learn.exec.second;

import org.junit.Test;

public class TestCalc {

    /**
     * 五亿正整数去重统计
     */
    @Test
    public void test(){
        int[] arr = new int[]{1,2,3,4,Integer.MAX_VALUE,1,2,3,4,1,1,1,1} ;
        System.out.println(getCount(arr));
    }

    /**
     * 亿级正整数去重统计
     * 300 M 内存
     */
    public static int getCount(int[] arr) {
        /*
        五亿个数每个数占一个位，五亿位 = 62,500,000‬字节 = 59.6 MB
        正整数的最大值 2^31 = 2,147,483,648‬，存下该数需要 2,147,483,649 （0是首位）
        2,147,483,648 位 = 268,435,456‬ B = 256 MB
         */
        int len = Integer.MAX_VALUE % 8 + 1 ; // 需要的数组长度
        byte[] bytes = new byte[len] ; // 看似 row 是个变量，实际上编译时已经是个常数
        int count = 0 ; // 计数
        for(int i : arr) {
            int row = i % 8 ; // 行数
            int col = i / 8 ; // 列数
            // 对应的 bit 的值（ 0 或 1 ）
            int val = (byte) (bytes[row] & 1 << col) ; // 移位运算比与运算的优先级高
            if(val == 0) {
                bytes[row] = (byte) (bytes[row] | 1 << col) ; // 移位运算比或运算的优先级高
                count ++ ;
            }
        }
        return count;
    }
}
