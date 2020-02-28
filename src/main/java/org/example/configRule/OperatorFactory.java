package org.example.configRule;
import java.util.Map;

public interface OperatorFactory {
    Node getInstance(Object value, Map<String,Object> symbolTable);
}
