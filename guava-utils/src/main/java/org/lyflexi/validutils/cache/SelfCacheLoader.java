package org.lyflexi.validutils.cache;

import com.google.common.cache.CacheLoader;
import org.checkerframework.checker.units.qual.C;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 14:02
 */

public class SelfCacheLoader<K, V> extends CacheLoader<K, V> {

    /**
     * CacheLoader 中重写了 load 方法，这个方法会在查询缓存没有命中时被调用，
     * 我这里直接返回了 null，其实这样会在没有命中时抛出 CacheLoader returned null for key 异常信息。
     * 
     * @param key the non-null key whose value should be loaded
     * @return
     * @throws Exception
     */
    @Override
    public Object load(Object key) throws Exception {
        return null;
    }
}
