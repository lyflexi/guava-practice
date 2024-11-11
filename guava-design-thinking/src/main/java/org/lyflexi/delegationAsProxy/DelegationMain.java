package org.lyflexi.delegationAsProxy;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 14:58
 */
public class DelegationMain {
    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("a", "b", "c");
        LoggingCollection<String> stringLoggingCollection = new LoggingCollection<>(strings);

        boolean a = stringLoggingCollection.remove("a");
        System.out.println("移除成功："+a);

        stringLoggingCollection.forEach(System.out::println);

    }
}
