package com.learn.domain;

import java.io.Serializable;

public class Person implements Serializable {
    public static final long serialVersionUID = 2588443849177891759L;
    private int id;
    private String name;
    private int age;
    private int num;
    private Person friend;
    private transient Person enemy; // 临时属性，不进行序列化

    public Person getEnemy() {
        return enemy;
    }

    public void setEnemy(Person enemy) {
        this.enemy = enemy;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Person(String name){
        this.name = name;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
