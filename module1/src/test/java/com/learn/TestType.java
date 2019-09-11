package com.learn;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class TestType {

    /**
     * 修改 vm options 尝试从 console 输入进行junit测试
     * -Deditable.java.test.console=true
     */
    @Test
    public void test5(){
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.next());
    }

    /**
     * idea 默认的junit自动化测试，取消了从控制台手动输入
     * 修改默认配置后，成功模拟出 native2ascii的功能
     */
    @Test
    public void test4(){
        Scanner sc = new Scanner(System.in);
        String str = null; //定义一个 String ，避免在循环体中每次创建
        StringBuffer stringBuffer = new StringBuffer(); //拼装多个十六进制码
        while(true){
            str = sc.next();
            if("exit!".equalsIgnoreCase(str)){
                break;
            }
            char[] chars = str.toCharArray();
            for(char c : chars){
                stringBuffer.append(String2Hex(c));
            }
            System.out.println(stringBuffer);
            // 输入对应的 Unicode 码表示的字符
            stringBuffer.setLength(0);
        }
    }

    /**
     * 得到字符“中”的十六进制
     */
    @Test
    public void test3(){
        char c = '中';
        System.out.println(String2Hex(c));
    }

    /**
     * 手动得到字符的十六进制
     */
    private String String2Hex(char c) {
        char[] hexNums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int intC = (int)c; //将 char 转为 int 型，再将 int 后两个字节转为十六进制数
        StringBuffer sb = new StringBuffer("\\u");
        sb.append(hexNums[c >> 12 & 0xf]); //高字节的前四位
        sb.append(hexNums[c >> 8 & 0xf]); //高字节的后四位
        sb.append(hexNums[c >> 4 & 0xf]); //低字节的前四位
        sb.append(hexNums[c & 0xf]); //低字节的后四位
        return sb.toString();
    }

    /**
     * 测试字符集
     */
    @Test
    public void test2() throws Exception {
        char a = 'a';
        System.out.println((int)a);//97
        char A = 'A';
        System.out.println((int)A);//65
        char space = ' ';
        System.out.println((int)space);//32
        char mark = '?';
        System.out.println((int)mark);//63

        char midd = '中';
        String hexMidd = Integer.toHexString((int)midd);
        System.out.println(hexMidd);//4e2d

        String str = "a中b";
        byte[] bytes = str.getBytes("ascii");
        System.out.println(new String(bytes, "ascii"));//a?b
        System.out.println(bytes);//[B@22927a81
        for(byte b : bytes){
            System.out.print(b + " ");//97 63 98
        }

        String str1 = "word中文";
        //w o r d 中 文，每个字符两个字节，Unicode头占两个字节
        str1.getBytes("unicode");//14字节
        //英文占一个字节，中文占三个字节
        str1.getBytes("utf-8");//10字节
        //英文占一个字节，中文占两个字节
        str1.getBytes("gbk");//8字节
        str1 = "word";
        //w o r d 各占两个字节，Unicode头占两个字节
        str1.getBytes("unicode");//10字节
        str1 = "中文";
        //中文两个字节，Unicode头占两个字节
        str1.getBytes("unicode");//6字节
    }

    /**
     * 各种进制的表示方式
     */
    @Test
    public void test1() {
        System.out.println(0b101);//二进制:5  （0b开头的）
        System.out.println(0e1011);//0.0
        System.out.println(011);//八进制:9   (0开头的)
        System.out.println(11);//十进制:11
        System.out.println(0x11C);//十六进制:284   （0x开头的）

        System.out.printf("%010x\n",7);//0000000007   按10位十六进制输出，向右靠齐，左边用0补齐
        System.out.printf("%010o\n",13);//0000000015    按10位八进制输出，向右靠齐，左边用0补齐

        System.out.printf("%x\n",7);//7   按16进制输出
        System.out.printf("%o\n",13);//15   按8进制输出

        System.out.println(Integer.toBinaryString(11));//1011 二进制

    }
}
