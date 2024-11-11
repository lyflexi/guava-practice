package org.lyflexi.validutils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 10:48
 */
public class PreconditionsSamples {
    /**
     * 数据校验说来十分简单，一是非空判断，二是预期值判断。非空判断我想每一个 Java 开发者都很熟悉，一开始都经常和 NullPointException 打交道。处理的方式我们自然是一个 if( xx == null) 就能轻松解决。预期值判断也是类似，检查数据值是不是自己想要的结果即可。
     *
     * 即使这么简单的操作，我们是不是还经常出错呢？而且写起来的代码总是一行判断一行异常抛出，怎么看都觉得那么优雅。还好，现在就来尝试第一次使用 Guava 吧。
     * @param args
     */
    public static void main(String[] args) {
//        checkNotNull();
//        checkArgument();
        checkElementIndex();
    }
    

    /**
     * 非空判断
     */
    private static void checkNotNull(){
        String param = "未读代码";
        String name = Preconditions.checkNotNull(param);
        System.out.println(name); // 未读代码
        String param2 = null;
        String name2 = Preconditions.checkNotNull(param2,"param2 is null"); // NullPointerException
        System.out.println(name2);
    }

    /**
     * 预期值判断
     */
    private static void checkArgument() {
        String param = "www.wdbyte.com2";
        String wdbyte = "www.wdbyte.com";
        Preconditions.checkArgument(wdbyte.equals(param), "[%s] 404 NOT FOUND", param);
    }

    /**
     * 是否越界
     */
    private static void checkElementIndex() {
        // Guava 中快速创建ArrayList
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        // 开始校验
        int index = Preconditions.checkElementIndex(5, list.size());
    }

}
