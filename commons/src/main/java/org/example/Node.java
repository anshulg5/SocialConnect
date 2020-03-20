package org.example;
import java.util.Map;

public interface Node<T>{
    T apply(Map<String,?> input);
}
