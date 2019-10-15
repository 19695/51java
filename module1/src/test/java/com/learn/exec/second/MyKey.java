package com.learn.exec.second;

/**
 * @author Colm
 * @create 2019/10/8
 */
public class MyKey {
    public int key ;

    public MyKey(int key){
        this.key = key ;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
