package org.lyflexi.validutils.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 10:55
 */

/**
 * 创建不可变集合是我个人最喜欢 Guava 的一个原因，因为创建一个不能删除、不能修改、不能增加元素的集合实在是太实用了。这样的集合你完全不用担心发生什么问题
 */
public class ImmutableSamples {

    public static void main(String[] args) {
//        initByOf();
//        initByBuilder();
//        initByCopyOf();
//        modifyException();
//        referenceChange();
//        jdkImmutable();
        jdkVsGuava();
    }
    

    /**
     * 创建方式1：of
     */
    private static void initByOf(){
        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c");
        immutableSet.forEach(System.out::println);
    }

    /**
     * 创建方式2：builder
     */
    private static void initByBuilder(){
        ImmutableSet<String> immutableSet = ImmutableSet.<String>builder()
                .add("hello")
                .add(new String("未读代码"))
                .build();
        immutableSet.forEach(System.out::println);
    }
    /**
     * 创建方式3：从其他集合中拷贝创建
     */
    private static void initByCopyOf(){
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("www.wdbyte.com");
        arrayList.add("https");
        ImmutableSet<String> immutableSet = ImmutableSet.copyOf(arrayList);
        immutableSet.forEach(System.out::println);
    }


    /**
     * 不可变集合不支持元素修改操作：modifyException，也就是说，guava的不可变与jdk的final关键字逻辑是相反的！
     */
    private static void modifyException (){
        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c");
        immutableSet.forEach(System.out::println);
        immutableSet.add("add");
    }


    /**
     * 不可变集合支持改变引用：referenceChange，也就是说，guava的不可变与jdk的final关键字逻辑是相反的！
     */
    private static void referenceChange (){
        System.out.println("---begin:immutableSet1");
        ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "b", "c");
        immutableSet1.forEach(System.out::println);

        System.out.println("---begin:immutableSet2");
        ImmutableSet<String> immutableSet2 = ImmutableSet.<String>builder()
                .add("hello")
                .add(new String("未读代码"))
                .build();
        immutableSet2.forEach(System.out::println);

        System.out.println("---begin:immutableSet referenceChange");
        immutableSet1 = immutableSet2;
        immutableSet1.forEach(System.out::println);

    }


    /**
     * 其实 JDK 中也提供了一个不可变集合，可以像下面这样创建。
     */
    private static void jdkImmutable (){
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("www.wdbyte.com");
        arrayList.add("https");
        // JDK Collections 创建不可变 List
        List<String> list = Collections.unmodifiableList(arrayList);
        list.forEach(System.out::println);// www.wdbyte.com https
        list.add("未读代码"); // java.lang.UnsupportedOperationException
    }


    /**
     * 使用 JDK 提供的不可变集合创建成功后，原集合添加元素会体现在不可变集合中，而 Guava 的不可变集合不会有这个问题。
     */
    private static void jdkVsGuava (){
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        List<String> jdkList = Collections.unmodifiableList(arrayList);
        ImmutableList<String> immutableList = ImmutableList.copyOf(arrayList);
        arrayList.add("ccc");
        jdkList.forEach(System.out::println);// result: a b ccc
        System.out.println("-------");
        immutableList.forEach(System.out::println);// result: a b
    }


}
