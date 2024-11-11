package org.lyflexi.validutils.cache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.checkerframework.checker.units.qual.K;

/**
 * @Description:
 * @Author: lyflexi
 * @project: guava-practice
 * @Date: 2024/11/11 14:01
 */

/**
 * 缓存移除监听器
 */
public class SelfRemovalListener<K, V> implements RemovalListener<K, V> {

    /**
     * MyRemovalListener 作为缓存元素失效时的监听类，在有元素缓存失效时会自动调用 onRemoval 方法，
     * 这里需要注意的是这个方法是同步方法，如果这里耗时较长，会阻塞直到处理完成。
     * @param notification
     */
    @Override
    public void onRemoval(RemovalNotification<K, V> notification) {
        String reason = String.format("key=%s,value=%s,reason=%s", notification.getKey(), notification.getValue(), notification.getCause());
        System.out.println(reason);
    }
}