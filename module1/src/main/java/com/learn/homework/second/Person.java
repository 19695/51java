package com.learn.homework.second;

/**
 * 将四个 int 属性作为 hashCode 值
 *
 * @author Colm
 * @create 2019/10/14
 */
public class Person {
    private int age;
    private int weight;
    private int height;
    private int blood;
    public Person(int age, int weight, int height, int blood){
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    @Override
    public int hashCode() {
        byte highest = (byte)(age & 0xff);
        byte higher = (byte)(weight & 0xff);
        byte lower = (byte)(height & 0xff);
        byte lowest = (byte)(blood & 0xff);
        System.out.println(highest + "\t" + higher + "\t" + lower + "\t" + lowest);
        return (highest << 24) | (higher << 16) | (lower << 8) | lowest;
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) ? true : (obj == null) ? false : (this.hashCode() == obj.hashCode());
    }
}
