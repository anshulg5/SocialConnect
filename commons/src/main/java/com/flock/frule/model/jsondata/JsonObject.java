package com.flock.frule.model.jsondata;


import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonType {
    private final Map<String, JsonType> underlyingMap;

    public JsonObject() {
        this.underlyingMap = new HashMap<>();
    }

    public void put(String key, JsonType val) {
        underlyingMap.put(key,val);
    }

    public <T> T get(String key, Class<T> type) {
        return null;
    }

    public void merge(Map<String, JsonType> map) {
        underlyingMap.putAll(map);
    }

    public JsonType get(String key) {
        return underlyingMap.get(key);
    }
}
