package com.example.demo.challenge.utils;

import com.example.demo.challenge.model.CReq;

import java.util.HashMap;

public class Cache {
    private HashMap<String, CReq> cache;

    public Cache() {
        cache = new HashMap<>();
    }

    public void addToCache(String key, CReq value) {
        cache.put(key, value);
    }

    public CReq getFromCache(String key) {
        return cache.get(key);
    }

    public void removeFromCache(String key) {
        cache.remove(key);
    }


}
