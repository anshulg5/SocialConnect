package org.configRule.Node;

import org.example.Node;
import org.example.NodeManager;

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
    public T apply(Map<String,?> input){
        Collection<String> collection = collectionNode.apply(input);
        Iterator<String> iterator = collection.iterator();
        Object object = input;
        while(iterator.hasNext()) {
            if(object instanceof Map)
                object = ((Map) object).get(iterator.next());
            else
                object = ((List) object).get(Integer.valueOf(iterator.next()));
        }
        return (T)object;
    }

}
