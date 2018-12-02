package com.agoda.cache.service;

/**
 * Created by rajnikant on 01/12/18.
 */
public interface CacheService<K, V> {

    Boolean add(K key, V value);

    V get(K key);

    V peek();

    Boolean remove(K key);

    V take();
}
