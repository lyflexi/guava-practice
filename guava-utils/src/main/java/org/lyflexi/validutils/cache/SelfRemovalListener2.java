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
 * 确定泛型的正常写法：SelfRemovalListener2
 *  <String, Animal>写在implements后面
 *  这样SelfRemovalListener2的定义会显得很丝滑
 */
public class SelfRemovalListener2 implements RemovalListener<String, Animal> {
    
    @Override
    public void onRemoval(RemovalNotification<String, Animal> notification) {
        String reason = String.format("key=%s,value=%s,reason=%s", notification.getKey(), notification.getValue(), notification.getCause());
        System.out.println(reason);
    }
}