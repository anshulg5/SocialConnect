package com.flock.frule.model;

public class Identity implements DataOperation<JsonData> {
    @Override
    public JsonData execute(JsonData input) {
        return input;
    }
}
