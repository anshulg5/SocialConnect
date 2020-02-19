package org.example.configRule;
import java.util.Map;

public interface Node<T>{
    T apply(Map<String,?> input);
}
