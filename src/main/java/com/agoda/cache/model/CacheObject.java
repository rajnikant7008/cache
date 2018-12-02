package com.agoda.cache.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by rajnikant on 01/12/18.
 */
@Getter
@Setter
@ToString
public class CacheObject {

    private long key;
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheObject)) return false;

        CacheObject cacheObject = (CacheObject) o;

        return getKey() == cacheObject.getKey();
    }

    @Override
    public int hashCode() {
        return (int) (getKey() ^ (getKey() >>> 32));
    }
}
