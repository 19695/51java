package com.learn.exec.first;

import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TestIO {
    /**
     * 深度复制——使用内存流的方式
     */
    @Test
    public void test12() throws Exception {
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");
        Person p3 = new Person("p3");
        Person p4 = new Person("p4");
        Person enemy = new Person("enemy");
        //设置关联关系
        p1.setFriend(p2);
        p2.setFriend(p3);
        p3.setFriend(p4);
        p2.setEnemy(enemy);

        Person person = (Person) deepCopy(p2);
    }
    /**
     * 深度复制——使用内存流的方式
     */
    public Object deepCopy(Object src){
        try{
            // 串行
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(src);
            oos.close();
            baos.close();

            // 反串行
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            ois.close();
            bais.close();
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 深度复制——使用读写磁盘文件的方式
     */
    @Test
    public void test11() throws Exception{
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");
        Person p3 = new Person("p3");
        Person p4 = new Person("p4");
        //设置关联关系
        p1.setFriend(p2);
        p2.setFriend(p3);
        p3.setFriend(p4);
        //通过串行化实现深度复制
        FileOutputStream fos = new FileOutputStream("src/test/IOFile/person-copy.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p2);
        oos.close();
        fos.close();
    }

    /**
     * 反串行化
     */
    @Test
    public void testDeserial() throws Exception {
        // 反串行化 ObjectOutputStream-p.dat ，学习反串行化
//        FileInputStream fis = new FileInputStream("src\\test\\IOFile\\ObjectOutputStream-p.dat");
        // 反串行化 person-copy.dat ， 学习深度复制
        FileInputStream fis = new FileInputStream("src/test/IOFile/person-copy.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person p = (Person)ois.readObject();
        ois.close();
        fis.close();
        System.out.println(p.getAge()); // 串行前赋值的
        System.out.println(p.toString()); // 串行后新增的方法
        System.out.println(p.getNum()); // 串行后新增的属性num的getter

    }

    /**
     * 串行化
     */
    @Test
    public void test10() throws Exception {
        Person p = new Person(1, "name", 23);
        FileOutputStream fout = new FileOutputStream("src\\test\\IOFile\\ObjectOutputStream-p.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(p);
        oos.close();
        fout.close();
    }

    /**
     * 压缩流——解压缩
     */
    @Test
    public void  test9() throws Exception{
        FileInputStream fis = new FileInputStream("src/test/IOFile/zos.zip"); // 压缩文件名
        ZipInputStream zis = new ZipInputStream(fis); // 解压缩
        byte[] buf = new byte[1024];
        int len = 0;
        ZipEntry entry; // 压缩文件里的一个条目就是一个entry
        String fileDir = "src\\test\\IOFile\\TestUnzipFiles"; // parent path
        while((entry = zis.getNextEntry()) != null){
            String name = entry.getName(); // child path
            File f = new File(fileDir, name); // 与parent path组成完整的文件路径
            FileOutputStream fout = new FileOutputStream(f);
            while((len = zis.read(buf)) != -1){
                fout.write(buf, 0, len);
            }
            fout.close();
        }
        zis.close();
        fis.close();
    }

    /**
     * 压缩流——压缩
     * 使用“好压”压缩后的TestZipFiles.zip 451 字节
     * 使用压缩流压缩后的 zos.zip 391 字节
     */
    @Test
    public void test8() throws Exception{
        FileOutputStream fos = new FileOutputStream("src/test/IOFile/zos.zip"); // 压缩文件名
        ZipOutputStream zos = new ZipOutputStream(fos); // 压缩流
        byte[] buf = new byte[1024];
        int len = -1; // 为什么视频中有时 len = 0，有时 len = -1 ？
        File dir = new File("src\\test\\IOFile\\TestZipFiles");
        File[] files = dir.listFiles();
        for(File f : files){
            if(f.isFile()){
                String fileName = f.getName(); // 文件名
                FileInputStream fin = new FileInputStream(f);
                zos.putNextEntry(new ZipEntry(fileName)); //压缩后的单个文件名
                while((len = fin.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                fin.close(); // 及时关闭循环中的输入流
            }
        }
        zos.close();
        fos.close();
    }

    /**
     * 内存流
     */
    @Test
    public void test7() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("abc".getBytes("unicode"));
        byte[] bytes = baos.toByteArray();
        baos.close();
        System.out.println(bytes.length); // 8
    }

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
     * 升格补符号位
     */
    @Test
    public void test2_1(){
        byte b = -1 ;
        System.out.println((int) b ) ;
    }

    /**
     * 将byte全部值转为正数
     * 256个输出太长，-10 ~ 10看一下输出就行
     */
    @Test
    public void test2(){
        int i = 0;
        for(byte b = -10; b <= 10; b++){
            i = (int)b & 0xff; // 升格补符号位，与上 0xff 将前三个字节变成 0
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
