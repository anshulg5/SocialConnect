package org.example;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.Map;

public class OperatorManager {
    private static HashMap<String,Operator> hashMap = new HashMap();
    public static void registerOperator(String name,Operator operator){
        hashMap.put(name,operator);
    }
    public static Operator getOperator(String name){
        return hashMap.get(name);
    }
    public static Map<Operator,Object> parse(JSONPObject jsonpObject){
        Map<Operator,Object> map = new HashMap<>();
        Operator operator = getOperator(jsonpObject.getFunction());
        if(jsonpObject.getClass().equals(jsonpObject)){
            map.put(operator,parse(jsonpObject));
        }
        else {
            map.put(operator,jsonpObject);
        }
        return map;
    }
}
