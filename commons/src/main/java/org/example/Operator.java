package org.example;
import java.util.Map;

public interface Operator {
    Node getInstance(Object value, Map<String,Object> symbolTable);
}
