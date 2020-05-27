package com.flock.frule.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.CompletionException;

public class Serializer {
    private static final Gson gson = new Gson();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> byte[] getJsonBytes(T data, String charset) {
        try {
            return gson.toJson(data).getBytes(charset);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

//    public static Map fromJson(String json) {
//        return gson.fromJson(json, Map.class);
//    }

    public static Map fromJson(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new CompletionException(e);
        }
    }

    public static <T> String toJson(T data ){
        return gson.toJson(data);
    }
}
