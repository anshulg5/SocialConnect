package com.flock.frule.model.jsondata;

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


}
