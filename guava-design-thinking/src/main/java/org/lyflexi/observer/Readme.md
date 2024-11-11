
用户自定义重写回调函数：
```java
/**
 * 缓存移除监听器：泛型不确定的正确写法，将泛型的确定权利交给上层用户
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
```
用户调用手动失效函数：LocalCache#invalidate
```java
@Override
public void invalidate(Object key) {
  checkNotNull(key);
  localCache.remove(key);
}
```
打断点， LocalCache底层触发回调
```java
  void processPendingNotifications() {
    RemovalNotification<K, V> notification;
    while ((notification = removalNotificationQueue.poll()) != null) {
      try {
        removalListener.onRemoval(notification);
      } catch (Throwable e) {
        logger.log(Level.WARNING, "Exception thrown by removal listener", e);
      }
    }
  }
```