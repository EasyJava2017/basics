package com.easy.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class ArrayBasics {
    public static void main(String[] params) {
        ArrayDemo ArrayDemo = new ArrayDemo();
        //ArrayDemo.test();
        ArrayDemo.testMultiArray();
    }
}

class ArrayDemo {
    void test() {
        int[] numbers = null;
        //int[] numbers;
        println(numbers instanceof Object);
        println(numbers);

        Person[] persons = new Person[2];
        Person jack = new Person();
        jack.name = "Jack";
        jack.age = 18;
        persons[0] = jack;
        persons[1] = new Person();
        println(Arrays.toString(persons));

        int[] x = {1,2,3};
        List<int[]> xlist = Arrays.asList(x);
        println("xlist size: " + xlist.size());
        int[] elementOfXList = xlist.get(0);
        println(JSON.toJSONString(xlist));

        Integer[] bigX = {1,2,3};
        List<Integer> bigXlist = Arrays.asList(bigX);
        println("bigXlist size: " + bigXlist.size());
        println(JSON.toJSONString(bigXlist));

        String[] s = {"a","b","c"};
        List slist = Arrays.asList(s);
        println("slist size: " + slist.size());
        println(JSON.toJSONString(slist));


        String[] sl = {"Java","Kotlin","Scala","JS"};
        sl = Arrays.copyOf(sl, sl.length * 2);
        println("sl = " + Arrays.toString(sl));

        Arrays.binarySearch(x, 0);


    }


    void testMultiArray(){
        String[][] s = new String[2][];
        s[0] = new String[2];
        s[1] = new String[3];
        s[0][0] = new String("Java");
        s[0][1] = new String("Scala");
        s[1][0] = new String("Kotlin");
        s[1][1] = new String("SpringBoot");
        s[1][2] = new String("JS");

        println(JSON.toJSONString(s));
    }

    public int search(int[] nums, int target) {
        // Look at every element
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == target) {
                return i; // return the index where the target is found
            }
        }

        // If we get here, the target was not in the array
        return -1;
    }

   void println(Object o) {
        System.out.println(o);
    }
}

class Person {
    String name;
    int age;

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
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
}
