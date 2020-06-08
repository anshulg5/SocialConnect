package com.flock.frule.model.jsondata;

import java.util.Objects;

public class JsonNull extends JsonType {
    private final static JsonNull nullc = new JsonNull();

    private JsonNull(){

    }

    public static JsonNull get() {
        return nullc;
    }

    @Override
    public boolean equals(Object obj) {
        return obj==null || obj instanceof JsonNull;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(null);
    }
}
