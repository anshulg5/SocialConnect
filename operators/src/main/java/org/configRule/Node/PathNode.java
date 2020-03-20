package org.configRule.Node;

import org.example.Node;
import org.example.Operator;

import java.util.*;

public class PathNode<T> implements Node<T> {

    Node<Collection<String>> collectionNode;

    public PathNode(Map<Operator,Object> map, Map<String,Object> symbolTable){
        if(map.size()==1) {
            Iterator<Operator> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Operator key = iterator.next();
                collectionNode = key.getInstance(map.get(key),symbolTable);;
            }
        }
    }

    @Override
    public T apply(Map<String,?> input){
        Collection<String> collection = collectionNode.apply(input);
        Iterator<String> iterator = collection.iterator();
        Object object = input;
        while(iterator.hasNext()) {
            object = ((Map) object).get(iterator.next());
        }
        return (T)object;
    }

}
