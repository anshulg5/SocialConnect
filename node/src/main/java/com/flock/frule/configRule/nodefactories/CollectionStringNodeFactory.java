package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.CollectionStringNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;

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
