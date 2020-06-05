package com.flock.frule.model.jsondata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonArray implements JsonType {
    private final List<JsonType> underlyingList;

    public JsonArray() {
        this.underlyingList = new ArrayList<>();
    }

    public void put(JsonType elem) {
        underlyingList.add(elem);
    }

    public void addAll(Collection<JsonType> collection) {
        underlyingList.addAll(collection);
    }

    public JsonType get(int index) {
        return underlyingList.get(index);
    }






}
