package com.flock.frule.util;

import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.CompletionException;

public class Serializer {
    private static final Gson gson = new Gson();

    public static <T> byte[] getJsonBytes(T data, String charset) {
        try {
            return gson.toJson(data).getBytes(charset);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    public static Map fromJson(String json) {
        return gson.fromJson(json, Map.class);
    }
}
