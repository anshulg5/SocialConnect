package com.flock.frule.model;

public interface Node<T>{
    T apply(JsonData input);
}
