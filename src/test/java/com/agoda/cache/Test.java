package com.agoda.cache;

import com.agoda.cache.util.LinkedConcurrentHashMap;

/**
 * Created by rajnikant on 01/12/18.
 */
public class Test {

    public static void main(String... args){
        LinkedConcurrentHashMap<Integer, String> map = new LinkedConcurrentHashMap<>(5);
        map.put(1,"1313");
        map.put(2,"1213");
        map.put(3,"54365");
        map.put(1,"2313");
        map.put(4,"87668");
        map.put(5,"dkghf");
        map.put(6,"agd");
        map.put(7,"6436234");
        //System.out.println(map.take());
        System.out.println(map.peek());
        System.out.println(map.size());
    }
}
