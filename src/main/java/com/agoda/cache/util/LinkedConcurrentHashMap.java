package com.agoda.cache.util;

import com.google.common.collect.Iterables;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by rajnikant on 01/12/18.
 */
public class LinkedConcurrentHashMap<K, V> {

    private LinkedHashMap<K, V> linkedHashMap;
    private final int cacheSize;
    private ReadWriteLock readWriteLock;

    public LinkedConcurrentHashMap(int size) {
        this.linkedHashMap = new LinkedHashMap<>();
        cacheSize = size;
        readWriteLock = new ReentrantReadWriteLock();
    }

    public boolean put(K key, V value) {
        if (get(key) != null) {
            remove(key);
        }
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            if (linkedHashMap.size() >= cacheSize && cacheSize > 0) {
                K oldAgedKey = linkedHashMap.keySet().iterator().next();
                remove(oldAgedKey);
            }
            V v = linkedHashMap.put(key, value);
            return v == null;
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return linkedHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public boolean containsKey(K key) {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return linkedHashMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    public V remove(K key) {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            return linkedHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public V peek() {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return linkedHashMap.size() > 0 ? linkedHashMap.get(Iterables.getLast(linkedHashMap.keySet())) : null;
        } finally {
            readLock.unlock();
        }
    }

    public V take() {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            return linkedHashMap.size() > 0 ? linkedHashMap.remove(Iterables.getLast(linkedHashMap.keySet())) : null;
        } finally {
            writeLock.unlock();
        }
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return linkedHashMap.entrySet();
    }

    public Set<K> keySet() {
        return linkedHashMap.keySet();
    }

    public int size(){
        return linkedHashMap.size();
    }
}
