package com.learn.exec.seventh;

import org.junit.Test;

/**
 *
 *
 * @author Colm
 * @create 2019/11/12
 */
public class TestJVM {

    /**
     * test  jvisualvm
     */
    @Test
    public void test1(){
        System.out.println();
    }

    public static void main(String[] args){
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试栈溢出
     * 栈溢出时可以调用多少个方法
     */
    @Test
    public void test(){
        callSelf(1);
    }
    public void callSelf(int n){
        System.out.println(n);
        n++;
        callSelf(n);
    }
}
