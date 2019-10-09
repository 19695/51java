package com.learn.first;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TestType {

    /**
     * int 转 16进制
     */
    @Test
    public void testInt2Hex(){
        System.out.println(int2Hex(0));
        System.out.println(int2Hex(-1));
        System.out.println(int2Hex(Integer.MAX_VALUE));
        System.out.println(int2Hex(Integer.MIN_VALUE));
    }

    public static String int2Hex(int i) {
        StringBuffer sb = new StringBuffer() ;
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'} ;
        sb.append(chars[i >> 28 & 0xf]) ;
        sb.append(chars[i >> 24 & 0xf]) ;
        sb.append(chars[i >> 20 & 0xf]) ;
        sb.append(chars[i >> 16 & 0xf]) ;
        sb.append(chars[i >> 12 & 0xf]) ;
        sb.append(chars[i >> 8 & 0xf]) ;
        sb.append(chars[i >> 4 & 0xf]) ;
        sb.append(chars[i >> 0 & 0xf]) ;
        return sb.toString();
    }

    // ~1 = -2
    /**
     * int 与 字节数组互转
     */
    @Test
    public void test8(){
        // 将 int 拆分成4个字节
        byte[] bytes = getInt2bytes(Integer.MAX_VALUE);
        for(byte b : bytes){
            System.out.println(b);
        }
        // 将字节数组组成 int
        System.out.println(getBytes2Int(getInt2bytes(Integer.MAX_VALUE)));
    }

    /**
     * 将字节数组组成 int
     */
    public int getBytes2Int(byte[] bytes){
        int intNum = (bytes[0] & 0xff) << 0 | // 低 0-7 位
                (bytes[1] & 0xff) << 8 | // 低 8-15 位
                (bytes[2] & 0xff) << 16 | // 高 8-15 位
                (bytes[3] & 0xff) << 24; // 高 0-7 位
        return intNum;
    }

    /**
     * 将 int 拆分成4个字节
     */
    public byte[] getInt2bytes(int i){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i >> 0);
        bytes[1] = (byte) (i >> 8);
        bytes[2] = (byte) (i >> 16);
        bytes[3] = (byte) (i >> 24);
        return bytes;
    }

    /**
     * 练习：
     * 定义函数，取出整数内存中的存储形态对应的2进制字符串
     */
    @Test
    public void test7(){
        System.out.println(getInt2Bin(-1));
        System.out.println(getInt2Bin(-2));
        System.out.println(getInt2Bin(0));
        System.out.println(getInt2Bin(Integer.MAX_VALUE));
        System.out.println(getInt2Bin(Integer.MIN_VALUE));

//        System.out.println();a
//        for(int i = 0; i <= 20; i++){
//            System.out.println(i + " : " + getInt2Bin(i));
//        }
    }

    /**
     * 将int转为二进制表示形式
     * @param num
     * @return
     */
    private String getInt2Bin(int num){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 31; i >= 0; i--){
            stringBuffer.append(num >> i & 0b1);
        }
        return stringBuffer.toString();
    }

    /**
     * Java 字符全量输出
     * printf :
     * %md : 右对齐，当位数少于 m 时左侧补充空格
     * %-ms ： - 左对齐，位数小于 m 时右侧补充空格
     */
    @Test
    public void test6(){
        int bits = 4; // 左移量，当 = 0 时输出的是全部 Unicode，每增加 1 输出减半
        int cols = 0; // 每列数量，超出后换行
        for(int i = 0; i <= (0xffff >> bits); i++){ // java 中字符采用Unicode编码存储，两字节
            if(i != 8 && i != 9 && i != 10 && i != 11 && i != 12 && i != 13){
                System.out.printf("%5d\t: %-10s", i, (char)i);
            }else {
                if(i == 8){
                    System.out.printf("%5d\t: %-10s", i, "少个\\t");
                }
                if(i == 9){
                    System.out.printf("%5d\t: %-10s", i, "\\t");
                }
                if(i == 10){ //10 是换行
                    System.out.printf("%5d\t: %-10s", i, "\\n");
//                System.out.println();
                }
                if(i == 11 || i == 12 || i == 13){
                    System.out.printf("%5d\t: %-7s", i, "不显示");
                }
            }

            cols++;
            if(cols > 10){
                System.out.println();
                cols = 0;
            }
        }
    }

    /**
     * md5：字节值转为16进制
     */
    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] bytes = md.digest("abc".getBytes());
        char[] hexNums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuffer sb = new StringBuffer();
        for(byte b : bytes){
            sb.append(hexNums[b >> 4 & 0xf]);
            sb.append(hexNums[b & 0xf]);
        }
        System.out.println(sb.toString());
        //输出：900150983cd24fb0d6963f7d28e17f72
        //百度：900150983cd24fb0d6963f7d28e17f72
    }

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
        System.out.println(String2Hex(c)); // \u4e2d
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
