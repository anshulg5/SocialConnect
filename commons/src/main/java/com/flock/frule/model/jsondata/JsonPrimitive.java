package com.flock.frule.model.jsondata;

public class JsonPrimitive implements JsonType {
    private String underlyingPrimitive;

    public JsonPrimitive(Object value) {
        this.underlyingPrimitive = String.valueOf(value);
    }

    public String getAsString() {
        return underlyingPrimitive;
    }

    public int getAsInt() {
        return Integer.parseInt(underlyingPrimitive);
    }

    public double getAsDouble() {
        return Double.parseDouble(underlyingPrimitive);
    }

    public long getAsLong() {
        return Long.parseLong(underlyingPrimitive);
    }
}
