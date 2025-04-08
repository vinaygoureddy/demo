package com.example.demo.challenge.utils;

import com.example.demo.challenge.dto.CReqRequest;
import com.example.demo.challenge.model.CReq;

import java.util.HashMap;

public class Cache {
    private HashMap<String, CReqRequest> cache;

    public Cache() {
        cache = new HashMap<>();
    }

    public void addToCache(String key, CReqRequest value) {
        cache.put(key, value);
    }

    public CReqRequest getFromCache(String key) {
        return cache.get(key);
    }

    public void removeFromCache(String key) {
        cache.remove(key);
    }


}
