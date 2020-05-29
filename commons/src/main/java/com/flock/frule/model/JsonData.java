package com.flock.frule.model;

import com.flock.frule.util.Serializer;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JsonData {

    void merge(JsonData other);

    void put(String key, Object val);

    <T> T get(String key, Class<T> type);

    List<String> keys();

    static JsonData createEmpty() {
        return new MapJsonDataImpl("{}");
    }

    static JsonData fromJson(String json) {
        return new MapJsonDataImpl(json);
    }

    // TODO: implement this
    class MapJsonDataImpl implements JsonData {
        private final Map<String, Object> underlying = Maps.newHashMap();

        private MapJsonDataImpl(String json) {
            this.underlying.putAll(Serializer.fromJson(json));
        }

        @Override
        public void merge(JsonData other) {
            Map<String, Object> otherMap = new HashMap<>();
            List<String> keyList = other.keys();
            keyList.forEach(key -> otherMap.put(key,other.get(key,Object.class)));
            underlying.putAll(otherMap);
        }

        @Override
        public void put(String key, Object val) {
            underlying.put(key, val);
        }

        @Override
        public <T> T get(String key, Class<T> type) {
            Object entry = underlying.get(key);
            if (null == entry) return null;
            if (type.isAssignableFrom(entry.getClass())) {
                return (T) entry;
            } else {
                throw new IllegalArgumentException("type mismatch!");
            }
        }

        @Override
        public List<String> keys() {
            return new ArrayList<>(underlying.keySet());
        }

        @Override
        public String toString() {
            return Serializer.toJson(underlying);
        }

    }
}
