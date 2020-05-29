package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PathNode<T> implements Node<T> {

    Node<Collection<String>> collectionNode;

    public PathNode(Map<String,Object> map, Map<String,Object> symbolTable) throws IllegalAccessException {
        if(map.size()==1) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                collectionNode = NodeManager.create(key,map.get(key),symbolTable);;
            }
        }
    }

    public PathNode(Map<String,Object> map) throws IllegalAccessException {
        if(map.size()==1) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                collectionNode = NodeManager.create(map);;
            }
        }
    }

    @Override
    public T apply(JsonData input){
        Collection<String> collection = collectionNode.apply(input);
        Iterator<String> iterator = collection.iterator();
        Object object = input;

        if(iterator.hasNext())
            object = input.get(iterator.next(),Object.class);
        if(object==null)
            throw new NullPointerException();

        while(iterator.hasNext()) {
            if(object instanceof Map)
                object = ((Map) object).get(iterator.next());
            else {
                Object index = iterator.next();
                if(index instanceof String)
                    index = Integer.valueOf((String) index);
                object = ((List) object).get((Integer) index);
            }
            if(object==null)
                throw new NullPointerException();
        }
        return (T)object;
    }

}
