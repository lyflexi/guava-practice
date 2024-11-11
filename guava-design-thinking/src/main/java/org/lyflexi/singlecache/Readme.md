对于下述代码，build内部实际上创建出了LocalManualCache
```java
        //主要操作对象LoadingCache
        LoadingCache<String, Animal> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000) // 容量
                .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
                .removalListener(selfRemovalListener) // 失效监听器
                .build(selfCacheLoader); //
```
LocalManualCache就是LoadingCache的一个实现类，其内部的变量LocalCache是final的，也就意味着一旦初始化了LocalCache则无法再次改变LocalCache的引用，保证了缓存的安全
```java

  static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
    final LocalCache<K, V> localCache;

    LocalManualCache(CacheBuilder<? super K, ? super V> builder) {
      this(new LocalCache<K, V>(builder, null));
    }

    private LocalManualCache(LocalCache<K, V> localCache) {
      this.localCache = localCache;
    }

    // Cache methods

    @Override
    public @Nullable V getIfPresent(Object key) {
      return localCache.getIfPresent(key);
    }

    @Override
    public V get(K key, final Callable<? extends V> valueLoader) throws ExecutionException {
      checkNotNull(valueLoader);
      return localCache.get(
          key,
          new CacheLoader<Object, V>() {
            @Override
            public V load(Object key) throws Exception {
              return valueLoader.call();
            }
          });
    }

    @Override
    public ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
      return localCache.getAllPresent(keys);
    }

    @Override
    public void put(K key, V value) {
      localCache.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
      localCache.putAll(m);
    }

    @Override
    public void invalidate(Object key) {
      checkNotNull(key);
      localCache.remove(key);
    }

    @Override
    public void invalidateAll(Iterable<?> keys) {
      localCache.invalidateAll(keys);
    }

    @Override
    public void invalidateAll() {
      localCache.clear();
    }

    @Override
    public long size() {
      return localCache.longSize();
    }

    @Override
    public ConcurrentMap<K, V> asMap() {
      return localCache;
    }

    @Override
    public CacheStats stats() {
      SimpleStatsCounter aggregator = new SimpleStatsCounter();
      aggregator.incrementBy(localCache.globalStatsCounter);
      for (Segment<K, V> segment : localCache.segments) {
        aggregator.incrementBy(segment.statsCounter);
      }
      return aggregator.snapshot();
    }

    @Override
    public void cleanUp() {
      localCache.cleanUp();
    }

    // Serialization Support

    private static final long serialVersionUID = 1;

    Object writeReplace() {
      return new ManualSerializationProxy<>(localCache);
    }
  }
```

