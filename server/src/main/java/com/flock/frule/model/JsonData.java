package com.flock.frule.model;

import com.flock.frule.util.Serializer;
import com.google.common.collect.Maps;
import org.example.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JsonData {

    void merge(JsonData other);

    void put(String key, Object val);

    <T> T get(String key, Class<T> type);

    JsonData applyOn(JsonData input);

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
        public JsonData applyOn(JsonData input) {
            JsonData copy = JsonData.createEmpty();
            recursivelyApply(underlying,input)
                    .forEach((k,v) -> copy.put(k,v));
            return copy;
        }

        @Override
        public Map<String, ?> asMap() {
            return underlying;
        }

        @Override
        public String toString() {
            return underlying.toString();
        }

        private <T> T recursivelyApply(T root, JsonData input) {
            if(root instanceof Map){
                Map<String, Object> map = (Map) root;
                Map<String, Object> copy = new HashMap<>();
                for(Map.Entry<String, Object> entry: map.entrySet()) {
                    String k = entry.getKey();
                    Object v = entry.getValue();
                    if(v instanceof Node)
                        copy.put(k,((Node)v).apply(input.asMap()));
                    else
                        copy.put(k,recursivelyApply(v,input));
                }
                return (T)copy;
            }
            if(root instanceof List){
                List list = (List) root;
                List<Object> copy = new ArrayList<>();
                for(Object elem: list){
                    if(elem instanceof Node)
                        copy.add(((Node)elem).apply(input.asMap()));
                    else
                        copy.add(recursivelyApply(elem,input));
                }
                return (T)copy;
            }
            return root;
        }
    }
}
