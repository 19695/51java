package com.learn;

import org.junit.Test;

import java.io.*;

public class TestIO {

    /**
     * 写入到文件一个字节（byte b = -1）
     */
    @Test
    public void testWrite() throws IOException {
        FileOutputStream fout = new FileOutputStream("src/test/IOFile/WR.txt");
        fout.write(new byte[]{-1});
        fout.close();
    }

    /**
     * 读取一个字节从上面的文件
     */
    @Test
    public void testRead() throws IOException {
        FileInputStream fin = new FileInputStream("src/test/IOFile/WR.txt");
        System.out.println(fin.read()); // 255
        fin.close();
    }

    /**
     * 将byte全部值转为正数
     * 256个输出太长，-10 ~ 10看一下输出就行
     */
    @Test
    public void test2(){
        byte b = -10;
        int i = 0;
        for( ; b <= 10; b++){
            i = (int)b & 0xff;
            if(i == 0)  System.out.println();
            System.out.print(i + "\t");
        }
    }

    /**
     * 复习文件的输入输出
     *
     */
    @Test
    public void test1() throws Exception {
        //相对路径：src\test\IOFile\中文.txt
        FileInputStream fin = new FileInputStream("D:\\Workspaces\\IntelliJIDEA\\51java\\module1\\src\\test\\IOFile\\中文.txt");
        //相对路径：src\test\IOFile\English.txt
        FileOutputStream fout = new FileOutputStream("D:\\Workspaces\\IntelliJIDEA\\51java\\module1\\src\\test\\IOFile\\English.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fin.read(bytes)) != -1){
//            System.out.println(new String(bytes));
            fout.write(bytes, 0, len);
        }
        fin.read();
        fin.close();
        fout.close();
    }

    /**
     * 存在的问题：
     * 相对路径的 ../ 和 . 测试的时候不好使
     */
    @Test
    public void test0(){
        /*
        new File(""); 中间为空非空格
        D:\Workspaces\IntelliJIDEA\51java\module1
         */
        System.out.println(new File("").getAbsolutePath());
        System.out.println(new File("../").getAbsolutePath());
        System.out.println(new File("src/test").exists());
        File file = new File(".");
        for(String str : file.list()){
            System.out.println(str);
        }
        System.out.println(file.getParent());
        File child = new File("D:\\Workspaces\\IntelliJIDEA\\51java\\module1\\src\\test\\java\\com\\learn");
        System.out.println(child.getAbsolutePath());
        System.out.println(child.getParent());
    }
}
