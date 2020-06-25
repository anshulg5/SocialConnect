package com.flock.frule.helpers;

import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

public class JsonBuilder {
    public static JsonObject singletonListObject(String key, Object ... listItems) {
        return obj(key, arr(listItems));
    }

    public static JsonArray arr(Object... entries) {
        JsonArray result = new JsonArray();
        for (Object entry : entries) {
            result.add(of(entry));
        }
        return result;
    }

    public static JsonType of(Object entry) {
        if (entry instanceof JsonType) return (JsonType) entry;
        if (entry instanceof String || entry instanceof Boolean || entry instanceof Number) {
//            return JsonPrimitive.of(entry);
            return new JsonPrimitive(entry);
        }
        else
            throw new RuntimeException();
//        else if (entry instanceof Collection || entry.getClass().isArray()) {
////            return JsonArray.of(entry);
//            JsonArray jsonArray = new JsonArray();
//
//
//        }
    }

    public static JsonObject obj(String k1, JsonType v1) {
        JsonObject input = new JsonObject();
        input.put(k1,v1);
        return input;
    }

    public static JsonObject obj(String k1, JsonType v1, String k2, JsonType v2) {
        JsonObject input = new JsonObject();
        input.put(k1,v1);
        input.put(k2,v2);
        return input;
    }

    public static JsonObject obj(String k1, JsonType v1, String k2, JsonType v2, String k3, JsonType v3) {
        JsonObject input = new JsonObject();
        input.put(k1,v1);
        input.put(k2,v2);
        input.put(k3,v3);
        return input;
    }
}
