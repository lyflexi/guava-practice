package org.lyflexi.validutils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 10:55
 */


public class StringSamples {

    public static void main(String[] args) {
//        splitByJdk();
//        splitByGuava();

        testArrJoiner();
        testListJoiner();
        testMapJoiner();
    }


    /**
     * jdk方法有一个问题，就是如果最后一个元素为空，那么就会丢弃，奇怪的是第一个元素为空却不会丢弃，这就十分迷惑，下面通过一个例子演示这个问题。
     */
    private static void splitByJdk(){
        String str = ",a,,b,";
        String[] splitArr = str.split(",");
        Arrays.stream(splitArr).forEach(System.out::println);
        System.out.println("------");
        /**
         *
         * a
         *
         * b
         * ------
         */
    }


    /**
     * 如果使用 Guava 是怎样的操作方式呢？Guava 提供了 Splitter 类，并且有一系列的操作方式可以直观的控制分割逻辑。
     */
    private static void splitByGuava() {
        String str = ",a ,,b ,";
        Iterable<String> split = Splitter.on(",")
                .omitEmptyStrings() // 忽略空值
                .trimResults() // 过滤结果中的空白
                .split(str);
        split.forEach(System.out::println);
        /**
         * a
         * b
         */
    }


    /**
     * \Guava Joiner 可以轻松地将数组或列表中的元素拼接成一个字符串。
     *
     */
    public static void testArrJoiner() {
        String[] arr = {"3", "2", null, "", "5"};
        String result = Joiner.on("-").skipNulls().join(arr);
        System.out.println("arr " + result);
        result = Joiner.on("-").useForNull("空").join(arr);
        System.out.println("arr " + result);
    }

    public static void testListJoiner() {
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("2");
        list.add(null);
        list.add("5");
        String result = Joiner.on("-").skipNulls().join(list);
        System.out.println("list " + result);
        result = Joiner.on("-").useForNull("空").join(list);
        System.out.println("list " + result);
    }
    public static void testMapJoiner() {
        Map<String, String> tmpMap = Maps.newLinkedHashMap();
        tmpMap.put("1", "q1");
        tmpMap.put("5", "q5");
        tmpMap.put("2", "q3");

        String result = Joiner.on("#").withKeyValueSeparator("=").join(tmpMap);
        System.out.println(result); // 输出: 1=q1#5=q5#2=q3
    }

}
