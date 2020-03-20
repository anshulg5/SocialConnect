package org.example;

import java.util.*;

public class OperatorManager {
    private static HashMap<String,Operator> hashMap = new HashMap();
    public static void registerOperator(String name,Operator operator){
        hashMap.put(name,operator);
    }
    public static Operator getOperator(String name){
        return hashMap.get(name);
    }
    public static Map<Operator,Object> parse(Map<String,Object> objectMap){
            Map<Operator,Object> map = new HashMap<>();
            String key = objectMap.keySet().iterator().next();
            Operator operator = getOperator(key);
            Object ofinal = objectMap.get(key);
            if(objectMap.get(key).getClass().equals(ArrayList.class)){
                ArrayList list = (ArrayList) objectMap.get(key);
                ofinal = new ArrayList();
                Iterator iterator = list.iterator();
                while (iterator.hasNext()){
                    Object o = iterator.next();
                    if(o.getClass().equals(LinkedHashMap.class)){
                        o = parse((Map<String, Object>) o);
                    }
                    ((ArrayList) ofinal).add(o);
                }
            }
            else if(objectMap.get(key).getClass().equals(LinkedHashMap.class)) {
                ofinal = parse((Map<String, Object>) ofinal);
            }
            map.put(operator,ofinal);
            return map;

//        System.out.println(operator + "  " + jsonpObject.getValue().getClass());
//        if(jsonpObject.getValue().getClass().equals(JSONPObject.class)){
//            map.put(operator,parse((JSONPObject) jsonpObject.getValue()));
//        }
//        else if(jsonpObject.getValue().getClass().equals(ArrayList.class)){
//             Collection list = (Collection) jsonpObject.getValue();
//            Iterator iterator = list.iterator();
//            Collection list2 = new ArrayList();
//            while(iterator.hasNext()){
//                list2.add(parse((JSONPObject) iterator.next()));
//            }
//            map.put(operator,list2);
//        }
//        else{
//            map.put(operator,jsonpObject.getValue());
//        }
//        return map;
    }
}
