package com.flock.frule.model.jsondata;


// TODO: handle null values
public class JsonPrimitive extends JsonType {
    private Object underlyingValue;

    public JsonPrimitive(Object value){
        this.underlyingValue = value;
    }

    public Boolean isString() {
        return underlyingValue instanceof String;
    }

    public Boolean isNumber() {
        return underlyingValue instanceof Number;
    }

    public Boolean isIntegral() {
        if(isNumber()){
            return underlyingValue instanceof Integer || underlyingValue instanceof Long;
        }
        return false;
    }

    public Boolean isBoolean() {
        return underlyingValue instanceof Boolean;
    }

    public String getAsString() {
        return underlyingValue.toString();
    }

    //Converting string to number or not can be based on strictness
    public Number getAsNumber() {
        if(isString())
            return (getAsString().contains(".") ? Double.parseDouble(getAsString()) : Long.parseLong(getAsString()));
        return (Number)underlyingValue;
    }

    public int getAsInteger() {
        return getAsNumber().intValue();
    }

    public double getAsDouble() {
        return getAsNumber().doubleValue();
    }

    public long getAsLong() {
        return getAsNumber().longValue();
    }

    public Boolean getAsBoolean() {
        if(isString())
            return Boolean.parseBoolean(getAsString());
        return (Boolean)underlyingValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(getClass()!=obj.getClass())
            return false;

        JsonPrimitive other = (JsonPrimitive)obj;
        if(isIntegral() && other.isIntegral()) {
            return getAsLong() == other.getAsLong();
        }
        if(isNumber() && other.isNumber())
            return getAsDouble() == other.getAsDouble();
        return underlyingValue.equals(other.underlyingValue);
    }

    @Override
    public int hashCode() {
        if (isIntegral()) {
            long value = getAsNumber().longValue();
            return (int) (value ^ (value >>> 32));
        }
        if (underlyingValue instanceof Number) {
            long value = Double.doubleToLongBits(getAsNumber().doubleValue());
            return (int) (value ^ (value >>> 32));
        }
        return underlyingValue.hashCode();
    }
}