package com.learn;

import org.junit.Test;

import java.io.*;

public class TestIO {

    /**
     * 使用FileOutputStream向文件中以指定字符集写入
     */
    @Test
    public void test6() throws Exception {
        FileOutputStream fout = new FileOutputStream("src/test/IOFile/使用unicode字符集.txt");
        fout.write("a中b".getBytes("unicode"));
        fout.close();
    }

    /**
     * 使用InputStreamReader读取字符
     * 使用InputStreamReader可以指定字符集，保证读取字符的完整性
     */
    @Test
    public void test5() throws Exception {
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/test/IOFile/字符.txt"), "UTF-8");
        char[] buf = new char[2];
        int len = 0;
        while((len = isr.read(buf)) != -1){
            System.out.println(new String(buf, 0, len));
        }
        isr.close();
    }

    /**
     * 使用FileReader读取字符
     */
    @Test
    public void test4() throws IOException {
        FileReader fr = new FileReader("src/test/IOFile/字符.txt");
        int len = 0;
        char[] c = new char[2];
        while ((len = fr.read(c)) != -1){
            System.out.println(new String(c, 0, len));
        }
        fr.close();
    }

    /**
     * 使用FileInputStream读取字符
     */
    @Test
    public void test3() throws IOException {
        FileInputStream fin = new FileInputStream("src/test/IOFile/字符.txt");
        int len = 0;
        byte[] buf = new byte[3];//unicode两个字节，第二个字符读取时被拆开
        while((len = fin.read(buf)) != -1){
            System.out.println(new String(buf, 0, len));
        }
        fin.close();
    }

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
