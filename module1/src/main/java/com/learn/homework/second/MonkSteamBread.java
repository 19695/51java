package com.learn.homework.second;

/**
 * 和尚吃馒头问题
 *
 *      30 个和尚，100 个馒头，每个和尚最多吃 4 个馒头，
 *      最少吃 1 个馒头，一次只能吃一个馒头。
 *      满足上述条件情况下尽快吃光馒头。
 *
 * @author Colm
 * @create 2019/10/15
 */
public class MonkSteamBread {

    public static void main(String[] args){
        int monkNum = 10;
        int steamedBreadNum = 30;

        Boss boss = new Boss(monkNum, steamedBreadNum);
        for(int i = 1; i <= monkNum; i++){
            new Monk("monk" + i, boss).start();
        }
    }

    /*
    发放馒头的人，为了让馒头数量和和尚数量灵活不在内部写死数量，
    通过构造器传参初始化馒头和和尚数量
     */
    static class Boss{
        private static int steamedBreadNumber; //通知老板做多少馒头
        private static int uneatMonk ; //通知老板有多少和尚吃馒头

        public Boss(int uneatMonk, int steamedBreadNumber){
            this.steamedBreadNumber = steamedBreadNumber;
            this.uneatMonk = uneatMonk;
        }

        public synchronized int getSteamedBread(Monk monk) {
            // 加一个数量验证，加强程序健壮性
            if(steamedBreadNumber < 0 || uneatMonk < 0){
                return -1; // 一般出错返回 -1
            }
            // 不足最小值
            if(monk.eatNum < Monk.MIN_EAT){
                if(monk.eatNum == 0){ // 第一次来吃馒头
                    uneatMonk--;
                }
                return steamedBreadNumber--;
            }
            // 已经吃了最大值
            if(monk.eatNum >= Monk.MAX_EAT){
                return 0;
            }
            // 防止吃了小于最大值的和尚将馒头吃了导致有和尚吃不满足最小值
            if(steamedBreadNumber > uneatMonk * Monk.MIN_EAT){
                return steamedBreadNumber--;
            }
            return 0;
        }
    }

    /*
    和尚类
    行为很简单，在自己能吃的数量范围内吃就行
    MIN_EAT < eatNum < MAX_EAT
     */
    static class Monk extends Thread{
        public static final int MIN_EAT = 1;
        public static final int MAX_EAT = 4;

        private String name;
        private Boss boss;
        public int eatNum = 0;
        private String numStr = "";

        public Monk(String name, Boss boss){
            this.name = name;
            this.boss = boss;
        }
        public void run(){
            for(;;){
                int tem = boss.getSteamedBread(this);
                if(tem == -1){
                    System.out.println("和尚数量和馒头数量必须大于0");
                    break;
                }
                if(tem == 0){
                    System.out.printf("%s 吃的馒头数为：%d--(%s)\n", name, eatNum, numStr);
                    break;
                }
                eatNum++;
                numStr = numStr + "," + tem;
            }
        }
    }
}
