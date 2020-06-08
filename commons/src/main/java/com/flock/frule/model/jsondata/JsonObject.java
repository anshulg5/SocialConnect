package com.flock.frule.model.jsondata;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject extends JsonType {
    private final Map<String, JsonType> underlyingMap;

    public JsonObject() {
        this.underlyingMap = new HashMap<>();
    }

    public void put(String key, JsonType val) {
        underlyingMap.put(key,val);
    }

    //TODO: complete or remove this method, if not needed
    public <T> T get(String key, Class<T> type) {
        return null;
    }

    public JsonType get(String key) {
        if(!underlyingMap.containsKey(key))
            return JsonNull.get();
        return underlyingMap.get(key);
    }

    public Set<String> getKeys(){
        return underlyingMap.keySet();
    }

    public void merge(JsonObject other) {
        underlyingMap.putAll(other.underlyingMap);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(getClass()!=obj.getClass())
            return false;
        return ((JsonObject)obj).underlyingMap.equals(underlyingMap);
    }

    @Override
    public int hashCode() {
        return underlyingMap.hashCode();
    }
}