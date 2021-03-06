package com.learn.homework.second;

/**
 * 蜜蜂和熊的问题
 *
 *      100 只蜜蜂，2 头熊，每只蜜蜂每次生产的蜂蜜量是 1，
 *      有个一罐子容量是 50，罐子的蜂蜜量一旦达到 20，
 *      熊就一次性吃光。使用多线程模拟。
 *
 * @author Colm
 * @create 2019/10/15
 */
public class BeeBear {
    public static void main(String[] args){
        int beeNum = 30;
        Bucket bucket = new Bucket();
        for(int i = 1; i <= beeNum; i++){
            Bee bee = new Bee("bee" + i + " \t==> ", bucket);
            bee.start();
        }
        Bear bear1 = new Bear(">>>>>>>>>>\tbear1", bucket);
        Bear bear2 = new Bear(">>>>>>>>>>\tbear2", bucket);
        bear1.start();
        bear2.start();
    }
}
