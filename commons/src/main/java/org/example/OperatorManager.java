package org.example;

import java.util.HashMap;

public class OperatorManager {
    private static HashMap<String,Operator> hashMap = new HashMap();
    public static void registerOperator(String name,Operator operator){
        hashMap.put(name,operator);
    }
    public static Operator getOperator(String name){
        return hashMap.get(name);
    }
}
