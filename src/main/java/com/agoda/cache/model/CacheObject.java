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

    private String key;
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheObject)) return false;

        CacheObject object = (CacheObject) o;

        return getKey().equals(object.getKey());
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }
}
