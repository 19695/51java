package com.learn.nio;

import org.junit.Test;

import java.io.File;

/**
 * Junit 与 main 方法下的绝对路径测试
 *
 * @author Colm
 * @create 2019/10/22
 */
public class RelativePath {
    public static void main(String[] args){
        File file = new File("."); // D:\Workspaces\IntelliJIDEA\51java\.

        file = file.listFiles()[4]; // D:\Workspaces\IntelliJIDEA\51java\.\module1
        file = file.listFiles()[2]; // D:\Workspaces\IntelliJIDEA\51java\.\module1\src
        file = file.listFiles()[1]; // D:\Workspaces\IntelliJIDEA\51java\.\module1\src\test

        System.out.println(file.getAbsolutePath());
        System.out.println();

        dir(file);
    }

    @Test
    public void test() {
        File file = new File("."); // D:\Workspaces\IntelliJIDEA\51java\module1\.

        file = file.listFiles()[2]; // D:\Workspaces\IntelliJIDEA\51java\module1\.\src
        file = file.listFiles()[1]; // D:\Workspaces\IntelliJIDEA\51java\module1\.\src\test

        System.out.println(file.getAbsolutePath());
        System.out.println();

        dir(file);
    }

    // 模拟 cmd 的 dir 方法
    public static void dir(File file){
        String[] files = file.list();
        for(String f : files){
            System.out.println(f);
        }
    }
}
