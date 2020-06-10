package com.flock.frule.model.jsondata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonArray extends JsonType implements Iterable<JsonType>{
    private final List<JsonType> underlyingList;

    public JsonArray() {
        this.underlyingList = new ArrayList<>();
    }

    public void add(JsonType elem) {
        underlyingList.add(elem);
    }

    public void merge(JsonArray other) {
        this.underlyingList.addAll(other.underlyingList);
    }

    public JsonType get(int index) {
        return underlyingList.get(index);
    }

    public int size() {
        return underlyingList.size();
    }

    @Override
    public Iterator<JsonType> iterator() {
        return underlyingList.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(getClass()!=obj.getClass())
            return false;
        return ((JsonArray)obj).underlyingList.equals(underlyingList);
    }

    @Override
    public int hashCode() {
        return underlyingList.hashCode();
    }
}