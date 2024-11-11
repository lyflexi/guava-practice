package org.lyflexi.validutils.cache;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 14:01
 */
public class Animal {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}