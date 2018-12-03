package com.agoda.cache.controller;

import com.agoda.cache.model.CacheObject;
import com.agoda.cache.service.CacheService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rajnikant on 03/12/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CacheController.class)
@AutoConfigureMockMvc
public class CacheControllerIntegrationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Mock
    private CacheService<String, String> cacheService;

    @InjectMocks
    private CacheController cacheController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cacheController)
                .build();
    }

    @Test
    public void testAdd() throws Exception {
        CacheObject object = new CacheObject();
        object.setKey("India");
        object.setValue("New Delhi");
        when(cacheService.add(object.getKey(), object.getValue())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/cache/add")
                .content(asJsonString(object))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("true")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testRemove() throws Exception {
        String key = "India";
        when(cacheService.remove(key)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/cache/remove/{key}", key)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void get() throws Exception {
        String key = "India";
        String value = "New Delhi";
        when(cacheService.get(key)).thenReturn(value);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cache/get/{key}", key)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(value)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testPeek() throws Exception {
        String value = "New Delhi";
        when(cacheService.peek()).thenReturn(value);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cache/peek")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(value)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testTake() throws Exception {
        String value = "New Delhi";
        when(cacheService.take()).thenReturn(value);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cache/take")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(value)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    private static String asJsonString(final Object obj) {
        try {
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}