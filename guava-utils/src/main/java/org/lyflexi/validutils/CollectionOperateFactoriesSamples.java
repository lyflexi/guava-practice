package org.lyflexi.validutils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 11:11
 */
public class CollectionOperateFactoriesSamples {
    public static void main(String[] args) {
//        calculateIUD();
//        calculateCountsByJdk();
//        sampleHashMultiset();
//        valueOfListByJdk();
        sampleHashMultimap();
    }

    /**
     * 计算交集并集差集
     * I:Intersection 
     * U:Union
     * D:Difference
     */
    private static void calculateIUD() {
        Set<String> newHashSet1 = Sets.newHashSet("a", "a", "b", "c");
        Set<String> newHashSet2 = Sets.newHashSet("b", "b", "c", "d");

        // 交集
        Sets.SetView<String> intersectionSet = Sets.intersection(newHashSet1, newHashSet2);
        System.out.println(intersectionSet); // [b, c]

        // 并集
        Sets.SetView<String> unionSet = Sets.union(newHashSet1, newHashSet2);
        System.out.println(unionSet); // [a, b, c, d]

        // newHashSet1 中存在，newHashSet2 中不存在
        Sets.SetView<String> setViewLeft = Sets.difference(newHashSet1, newHashSet2);
        System.out.println(setViewLeft); // [a]

        // newHashSet2 中存在，newHashSet1 中不存在
        setViewLeft = Sets.difference(newHashSet2, newHashSet1);
        System.out.println(setViewLeft); // [d]
    }

    /**
     * Java 统计相同元素出现的次数。
     */
    private static void calculateCountsByJdk() {

        List<String> words = Lists.newArrayList("a", "b", "c", "d", "a", "c");
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = countMap.get(word);
            count = (count == null) ? 1 : ++count;
            countMap.put(word, count);
        }
        countMap.forEach((k, v) -> System.out.println(k + ":" + v));
        /**
         * result:
         * a:2
         * b:1
         * c:2
         * d:1
         */
    }

    /**
     * 统计相同元素出现的次数。在 Guava 中有什么不一样呢？在 Guava. 中主要是使用 HashMultiset 类，看下面。
     * 
     * 是的，只要把元素添加进去就行了，不用在乎是否重复，最后都可以使用 count 方法统计重复元素数量。看着舒服，写着优雅，
     */
    private static void sampleHashMultiset() {
        ArrayList<String> arrayList = Lists.newArrayList("a", "b", "c", "d", "a", "c");
        HashMultiset<String> multiset = HashMultiset.create(arrayList);
        multiset.elementSet().forEach(s -> System.out.println(s + ":" + multiset.count(s)));
        /**
         * result:
         * a:2
         * b:1
         * c:2
         * d:1
         */
    }


    /**
     * 假设一个场景，需要把很多动物按照种类进行分类，我相信最后你会写出类似的代码。
     */
    private static void valueOfListByJdk() {
        HashMap<String, Set<String>> animalMap = new HashMap<>();
        HashSet<String> dogSet = new HashSet<>();
        dogSet.add("旺财");
        dogSet.add("大黄");
        animalMap.put("狗", dogSet);
        HashSet<String> catSet = new HashSet<>();
        catSet.add("加菲");
        catSet.add("汤姆");
        animalMap.put("猫", catSet);
        System.out.println(animalMap.get("猫")); // [加菲, 汤姆]
    }

    /**
     * 如果使用guava来计算value为list的map
     * HashMultimap 可以扔进去重复的 key 值，最后获取时可以得到所有的 value 值，可以看到输出结果和 JDK 写法上是一样的，但是代码已经无比清爽。
     */
    private static void sampleHashMultimap() {
        // use guava
        HashMultimap<String, String> multimap = HashMultimap.create();
        multimap.put("狗", "大黄");
        multimap.put("狗", "旺财");
        multimap.put("猫", "加菲");
        multimap.put("猫", "汤姆");
        System.out.println(multimap.get("猫")); // [加菲, 汤姆]
    }


}
