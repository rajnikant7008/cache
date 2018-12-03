package com.agoda.cache.controller;

import com.agoda.cache.model.CacheObject;
import com.agoda.cache.service.CacheService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by rajnikant on 29/11/18.
 */
@RestController
@RequestMapping("v1/cache")
@Api(value = "Caches API controller", description = "APIs for caching")
public class CacheController {

    private static final Logger LOG = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    @Qualifier("customCacheServiceImpl")
    private CacheService<String, String> cacheService;


    @ApiOperation(value = "Add element to cache")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity add(@Valid @RequestBody CacheObject object) {
        LOG.info("Adding new element to the cache with key: {}", object.getKey());
        return new ResponseEntity<>(JsonNodeFactory.instance.booleanNode(
                cacheService.add(object.getKey(), object.getValue())), HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete element from cache")
    @RequestMapping(value = "/remove/{key}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity remove(@PathVariable(value = "key") String key) {
        LOG.info("Removing element from cache, key: {}", key);
        return new ResponseEntity<>(JsonNodeFactory.instance.booleanNode(cacheService.remove(key)), HttpStatus.OK);
    }

    @ApiOperation(value = "get element from cache")
    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity get(@PathVariable(value = "key") String key) {
        LOG.info("Retrieving element from cache, key: {}    ", key);
        String response = cacheService.get(key);
        ResponseEntity responseEntity;
        if (response == null) {
            responseEntity = new ResponseEntity<>(JsonNodeFactory.instance.nullNode(), HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(JsonNodeFactory.instance.textNode(response), HttpStatus.OK);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get most recently added element")
    @RequestMapping(value = "/peek", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity peek() {
        LOG.info("Retrieving most recently added element from cache");
        String response = cacheService.peek();
        ResponseEntity responseEntity;
        if (response == null) {
            responseEntity = new ResponseEntity<>(JsonNodeFactory.instance.nullNode(), HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(JsonNodeFactory.instance.textNode(response), HttpStatus.OK);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get most recently added element and remove it from cache   ")
    @RequestMapping(value = "/take", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity take() {
        LOG.info("Retrieving most recently added element from cache and removing it");
        String response = cacheService.take();
        ResponseEntity responseEntity;
        if (response == null) {
            responseEntity = new ResponseEntity<>(JsonNodeFactory.instance.nullNode(), HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(JsonNodeFactory.instance.textNode(response), HttpStatus.OK);
        }
        return responseEntity;
    }
}
