package org.configRule.NodeFactory;

import org.configRule.Node.CollectionStringNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.Collection;
import java.util.Map;

public class CollectionStringNodeFactory implements NodeFactory {
    static {
        CollectionStringNodeFactory collectionStringOperator = new CollectionStringNodeFactory();
        NodeManager.registerNodeFactory("STRLIST",collectionStringOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new CollectionStringNode((Collection<String>) value);
    }

    @Override
    public Node getInstance(Object value) {
        return new CollectionStringNode((Collection<String>) value);
    }

}
