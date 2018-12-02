package com.agoda.cache.serviceImpl;

import com.agoda.cache.service.CacheService;
import com.agoda.cache.util.LinkedConcurrentHashMap;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by rajnikant on 01/12/18.
 */
@Service
public class CustomCacheServiceImpl<K, V> implements CacheService<K, V> {

    private LinkedConcurrentHashMap<K, V> customCache;

    public CustomCacheServiceImpl(Environment environment) {
        customCache = new LinkedConcurrentHashMap<>(Integer.parseInt(
                environment.getProperty("cache.max.entry.limit")));
    }

    @Override
    public Boolean add(K key, V value) {
        return customCache.put(key, value);
    }

    @Override
    public V get(K key) {
        return customCache.get(key);
    }

    @Override
    public V peek() {
        return customCache.peek();
    }

    @Override
    public Boolean remove(K key) {
        V value = customCache.get(key);
        V removedValue = customCache.remove(key);
        return removedValue != null && value == removedValue;
    }

    @Override
    public V take() {
        return customCache.take();
    }
}
