package com.flock.frule.configRule.nodefactories.primitivenodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.primitivenodes.StringNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class StringNodeFactory implements NodeFactory<String> {
    static {
        StringNodeFactory stringNodeOperator = new StringNodeFactory();
        NodeManager.registerNodeFactory("STR", stringNodeOperator);
    }
    @Override
    public Node<String> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new StringNode(json.asObject());
    }
}