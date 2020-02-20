package org.example.configRule;

import com.sun.tools.internal.xjc.reader.internalizer.SCDBasedBindingSet;
import org.apache.commons.codec.language.bm.Rule;
import org.example.configRule.primitiveType.StringNode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LetNode<T> implements Node<T> {

    Node<List<String> > def;
    Node<T>body;
    Map<String,Node> symbolTable;

    public LetNode(Map<Operator,Object> map, Map<String,Node> symbolTable) {
        this.symbolTable = symbolTable;
        if(map.size()==2 && map.containsKey(Operator.DEF) && map.containsKey(Operator.BODY)) {
            def = RuleApp.createNode(Operator.DEF,map.get(Operator.DEF),symbolTable);
            Map<Operator,Object> map1 = (Map<Operator, Object>) map.get(Operator.BODY);
            if(map1.size()==1) {
                Operator key = map1.keySet().iterator().next();
                body = RuleApp.createNode(key,map1.get(key),symbolTable);
            }
        }
        else {
            System.out.println("Invalid");
        }
    }

    @Override
    public T apply(Map<String, ?> input) {
        T result = (T)body.apply(input);
        List<String> list = def.apply(input);
        for(String string : list)symbolTable.remove(string);
        return result;
    }
}
