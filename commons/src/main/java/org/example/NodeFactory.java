package org.example;
import java.util.Map;

public interface NodeFactory {
    Node getInstance(Object value, Map<String,Object> symbolTable);
}
