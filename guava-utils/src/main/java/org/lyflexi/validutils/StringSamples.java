package org.lyflexi.validutils;

import com.google.common.base.Splitter;

import java.util.Arrays;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 10:55
 */


public class StringSamples {

    public static void main(String[] args) {
//        splitByJdk();
        splitByGuava();
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


}
