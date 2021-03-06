package com.flock.frule.model.jsondata;

import com.flock.frule.util.Serializer;

public class JsonType {

    public boolean isObject() {
        return this instanceof JsonObject;
    }

    public boolean isArray() {
        return this instanceof JsonArray;
    }

    public boolean isPrimitive() {
        return this instanceof JsonPrimitive;
    }

    public boolean isNull() { return this instanceof JsonNull;}

    public JsonObject asObject(){
        if(this instanceof JsonObject)
            return (JsonObject)this;
        throw new ClassCastException();
    }

    public JsonArray asArray(){
        if(this instanceof JsonArray)
            return (JsonArray)this;
        throw new ClassCastException();
    }

    public JsonPrimitive asPrimitive(){
        if(this instanceof JsonPrimitive)
            return (JsonPrimitive)this;
        throw new ClassCastException();
    }

    public JsonNull asNull(){
        if(this instanceof JsonNull)
            return (JsonNull)this;
        throw new ClassCastException();
    }

    @Override
    public String toString() {
        return Serializer.toJson(this);
    }
}
