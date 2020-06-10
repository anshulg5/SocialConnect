package com.flock.frule.util;

import com.flock.frule.model.jsondata.JsonType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.CompletionException;

public class Serializer {
    private static final GsonBuilder gsonBuilder = new GsonBuilder();
    private static final Gson gson = gsonBuilder.registerTypeAdapter(JsonType.class, new GsonTypeAdapter()).create();

    public static <T> byte[] getJsonBytes(T data, String charset) {
        try {
            return gson.toJson(data, JsonType.class).getBytes(charset);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    public static JsonType fromJson(String json) {
        return gson.fromJson(json, JsonType.class);
    }

    public static <T> String toJson(T data ){
        return gson.toJson(data, JsonType.class);
    }
}
