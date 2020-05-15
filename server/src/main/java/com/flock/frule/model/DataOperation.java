package com.flock.frule.model;

public interface DataOperation<T> {

    T execute(JsonData input);
}
