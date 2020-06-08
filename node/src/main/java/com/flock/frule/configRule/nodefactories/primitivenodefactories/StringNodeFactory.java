package com.flock.frule.configRule.nodefactories.primitivenodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.primitivenodes.StringNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.util.Map;

public class StringNodeFactory implements NodeFactory {
    static {
        StringNodeFactory stringNodeOperator = new StringNodeFactory();
        NodeManager.registerNodeFactory("STR",stringNodeOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new StringNode((String) value);
    }

    @Override
    public Node getInstance(Object value) {
        return new StringNode((String) value);
    }

    @Override
    public Node getInstance(JsonType json) {
        return new StringNode(json.asObject());
    }
}
