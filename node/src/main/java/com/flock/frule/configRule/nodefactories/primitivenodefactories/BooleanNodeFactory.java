package com.flock.frule.configRule.nodefactories.primitivenodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.primitivenodes.BooleanNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class BooleanNodeFactory implements NodeFactory<Boolean> {
    static {
        BooleanNodeFactory booleanNodeOperator = new BooleanNodeFactory();
        NodeManager.registerNodeFactory("BOOL", booleanNodeOperator);
    }
    @Override
    public Node<Boolean> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new BooleanNode(json.asObject());
    }
}
