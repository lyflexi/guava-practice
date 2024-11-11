
# guava的不可变逻辑与jdk的final关键字正好相反！

final作用在属性上与作用在类上，有两大不同的作用
- 属性包括数组属性用 final 修饰，仅仅意味着这个变量一旦被初始化后，其引用就不能再指向另一个对象。一旦你想要重新设置final属性的引用将编译不通过。这样外部引用不会干预内部引用，以防止某一天外部引用的数据修改，对内部引用造成混乱
- 类用 final 修饰保证了该类中的方法不能被覆盖，防止子类无意间破坏不可变性

guava的不可变体现在：
- 不可变集合不支持元素修改操作
- 不可变集合支持改变引用

相比jdk的final逻辑，guava的不可变集合更贴近于业务需要，因此才诞生了guava
```java


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

```