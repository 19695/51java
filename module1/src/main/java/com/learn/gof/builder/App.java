package com.learn.gof.builder;

/**
 * builder 模式测试
 *
 * @author Colm
 * @create 2019/10/21
 */
public class App {
    public static void main(String[] args){
        Computer computer = new Computer.Builder().setCpu("i7").setMemory("16G").setHardDisk("1T").build();
        System.out.println(computer.toString());
    }
}
