package org.example;
import com.flock.frule.model.JsonData;

public interface Node<T>{
    T apply(JsonData input);
}
