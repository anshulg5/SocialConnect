package org.example.helpers;

import org.example.Node;
import org.example.NodeManager;

import java.util.*;

public class JsonHelper {

    public static Object createCopy(Object jsonElement, Map<String,?> message) throws IllegalAccessException {
        if (jsonElement instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) jsonElement;
            Set<String> keySet = map.keySet();
            if (keySet.size() == 1 && NodeManager.containsNode(keySet.iterator().next())) {
                return NodeManager.create(map);
            }
            Map<String, Object> newMap = new HashMap<>();
            for (String key : keySet) {
                newMap.put(key, createCopy(map.get(key), message));
            }
            return newMap;
        } else if (jsonElement instanceof List) {
            List list = (List) jsonElement;
            List<Object> newList = new ArrayList<>();
            for (Object elem : list)
                newList.add(createCopy(elem, message));
        } else if (jsonElement instanceof Node) {
            return ((Node) jsonElement).apply(message);
        }
        return jsonElement;
    }
}
