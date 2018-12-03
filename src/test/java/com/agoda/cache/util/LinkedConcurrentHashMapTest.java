package com.agoda.cache.util;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by rajnikant on 03/12/18.
 */
@RunWith(JUnitParamsRunner.class)
public class LinkedConcurrentHashMapTest {

    private LinkedConcurrentHashMap<String, String> linkedConcurrentHashMap;
    private static final int CACHE_LIMIT = 5;

    @Before
    public void setup(){
        linkedConcurrentHashMap = new LinkedConcurrentHashMap<>(CACHE_LIMIT);
    }

    @Test
    @Parameters({
            "China, Beijing",
            "India, New Delhi",
            "Thailand, Bangkok",
            "Indonesia, Jakarta",
            "Philippines, Manila",
            "Malaysia, Kuala Lumpur"
    })
    public void testPut(String key, String value) throws Exception {
        assertTrue(linkedConcurrentHashMap.put(key, value));
    }

    @Test
    @Parameters({
            "India, New Delhi",
            "Thailand, Bangkok"
    })
    public void testGet(String key, String value) throws Exception {
        linkedConcurrentHashMap.put(key, value);
        assertEquals(value, linkedConcurrentHashMap.get(key));
    }

    @Test
    @Parameters({
            "India, New Delhi",
            "Thailand, Bangkok",
            "India, New Delhi"
    })
    public void testPeek(String key, String value) throws Exception {
        assertNotEquals(value, linkedConcurrentHashMap.peek());
        linkedConcurrentHashMap.put(key, value);
        assertEquals(value, linkedConcurrentHashMap.peek());
    }

    @Test
    @Parameters({
            "India, New Delhi",
            "Thailand, Bangkok"
    })
    public void testRemove(String key, String value) throws Exception {
        linkedConcurrentHashMap.put(key, value);
        assertEquals(value, linkedConcurrentHashMap.remove(key));
        assertEquals(null, linkedConcurrentHashMap.remove(key));
    }

    @Test
    @Parameters({
            "India, New Delhi"
    })
    public void testTake(String key, String value) throws Exception {
        linkedConcurrentHashMap.put(key, value);
        assertEquals(value, linkedConcurrentHashMap.take());
        assertEquals(null, linkedConcurrentHashMap.take());
    }

}