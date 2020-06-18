package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.IdentityNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

public class IdentityNodeFactory implements NodeFactory<JsonType> {

    static {
        IdentityNodeFactory identityOperator = new IdentityNodeFactory();
        NodeManager.registerNodeFactory("IDTY",identityOperator);
    }

    @Override
    public Node<JsonType> getInstance(JsonType json) {
        return new IdentityNode();
    }
}
