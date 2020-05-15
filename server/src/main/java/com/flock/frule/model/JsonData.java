package com.flock.frule.model;

import com.flock.frule.util.Serializer;
import com.google.common.collect.Maps;

import java.util.Map;

public interface JsonData {

    void merge(JsonData other);

    void put(String key, Object val);

    <T> T get(String key, Class<T> type);

    static JsonData createEmpty() {
        return new MapJsonDataImpl("{}");
    }

    static JsonData fromJson(String json) {
        return new MapJsonDataImpl(json);
    }

    // TODO: remove this, this is temporary till we migrate Node.apply() to work on JsonData
    Map<String, ?> asMap();

    // TODO: implement this
    class MapJsonDataImpl implements JsonData {
        private final Map<String, Object> underlying = Maps.newHashMap();

        private MapJsonDataImpl(String json) {
            this.underlying.putAll(Serializer.fromJson(json));
        }

        @Override
        public void merge(JsonData other) {
            underlying.putAll(other.asMap());
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
        public Map<String, ?> asMap() {
            return underlying;
        }

        @Override
        public String toString() {
            return underlying.toString();
        }
    }
}
