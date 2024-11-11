package org.lyflexi.observer;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 10:55
 */


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 在开发中我们可能需要使用小规模的缓存，来提高访问速度。这时引入专业的缓存中间件可能又觉得浪费。
 * 现在, Guava 中提供了简单的缓存类，且可以根据预计容量、过期时间等自动过期已经添加的元素
 * 
 * 这个例子中主要分为 CacheLoader、MyRemovalListener、LoadingCache。
 * CacheLoader 中重写了 load 方法，这个方法会在查询缓存没有命中时被调用，我这里直接返回了 null，其实这样会在没有命中时抛出 CacheLoader returned null for key 异常信息。
 * MyRemovalListener 作为缓存元素失效时的监听类，在有元素缓存失效时会自动调用 onRemoval 方法，这里需要注意的是这个方法是同步方法，如果这里耗时较长，会阻塞直到处理完成。
 * LoadingCache 就是缓存的主要操作对象了，常用的就是其中的 put 和 get 方法了。
 */
public class LoadingCacheMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建自定义的CacheLoader
        CacheLoader<String, Animal> selfCacheLoader = new SelfCacheLoader<String, Animal>();
        //创建自定义的RemovalListener
        RemovalListener<String, Animal> selfRemovalListener = new SelfRemovalListener<String, Animal>();
        
        //主要操作对象LoadingCache
        LoadingCache<String, Animal> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000) // 容量
                .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
                .removalListener(selfRemovalListener) // 失效监听器
                .build(selfCacheLoader); //
        
        //3个测试数据
        loadingCache.put("狗", new Animal("旺财", 1));
        loadingCache.put("猫", new Animal("汤姆", 3));
        loadingCache.put("狼", new Animal("灰太狼", 4));

        // 手动失效，打印出reason=EXPLICIT
        loadingCache.invalidate("猫"); 

        /*手动失效后自动打印过期的数据
        * key=猫,value=Animal{name='汤姆', age=3},reason=EXPLICIT
        * */
        
        
        //不过期，正常数据
        Animal wolf = loadingCache.get("狼");
        System.out.println(wolf);

        /*
        * Animal{name='灰太狼', age=4}
        * */
        
        
        //自然过期，自动打印
        Thread.sleep(4 * 1000);
        /*
        * key=狗,value=Animal{name='旺财', age=1},reason=EXPIRED
          key=狼,value=Animal{name='灰太狼', age=4},reason=EXPIRED
        * */
        
        //获取过期的 null 值报错
        System.out.println(loadingCache.get("狼"));
        /*
        * Exception in thread "main" com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key 狼.
         * */
    }

}
