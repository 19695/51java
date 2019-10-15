package com.learn.homework.second;

import com.learn.homework.second.Person;
import org.junit.Test;

/**
 * 测试 Person 的 hashCode 值
 * Person 中四个 int 作为一个 hashCode
 *
 * @author Colm
 * @create 2019/10/14
 */
public class TestPerson {

    @Test
    public void test1(){
        Person person = new Person(12,80,120,4);
        int hash = person.hashCode();
        int tmp = hash;

        for(int i = 3; i >= 0; i--){
//            tmp = tmp >>> (i * 8);
            tmp = hash >>> (i * 8);
            System.out.print((byte)(tmp & 0xff) + "\t");
//            tmp = tmp >>> 8;
        }

        System.out.println();

        for(int i = 3; i >= 0; i--){
            System.out.print((byte)(tmp & 0xff) + "\t");
            tmp = tmp >>> 8;
        }
    }

    /*@Before
    public void init(){
        Person person1 = new Person(12,80,120,4);
        Person person1_1 = new Person(12,80,120,4);
        Person person2 = new Person(21,150,180,2);
    }*/

    @Test
    public void test(){
        Person person1 = new Person(12,80,120,4);
        Person person1_1 = new Person(12,80,120,4);
        Person person2 = new Person(21,150,180,2);
        System.out.println(person1.hashCode());
        System.out.println(person1.equals(person1_1));
        System.out.println(person1 == person1_1);
        System.out.println(person1.equals(person2));
        System.out.println(person1 == person2);
    }
}
