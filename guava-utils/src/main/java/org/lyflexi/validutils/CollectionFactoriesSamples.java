package org.lyflexi.validutils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 11:11
 */
public class CollectionFactoriesSamples {
    public static void main(String[] args) {
        initByFactory();
    }

    /**
     * Guava 为每一个集合都添加了工厂方法创建方式。
     * 而且可以在创建时直接扔进去几个元素，这个简直太赞了，再也不用一个个 add 了。
     */
    private static void initByFactory() {
        // 创建一个 ArrayList 集合
        List<String> list1 = Lists.newArrayList();
        // 创建一个 ArrayList 集合，同时塞入3个数据
        List<String> list2 = Lists.newArrayList("a", "b", "c");
        // 创建一个 ArrayList 集合，容量初始化为10
        List<String> list3 = Lists.newArrayListWithCapacity(10);

        LinkedList<String> linkedList1 = Lists.newLinkedList();
        CopyOnWriteArrayList<String> cowArrayList = Lists.newCopyOnWriteArrayList();

        HashMap<Object, Object> hashMap = Maps.newHashMap();
        ConcurrentMap<Object, Object> concurrentMap = Maps.newConcurrentMap();
        TreeMap<Comparable, Object> treeMap = Maps.newTreeMap();

        HashSet<Object> hashSet = Sets.newHashSet();
        HashSet<String> newHashSet = Sets.newHashSet("a", "a", "b", "c");
    }

}
