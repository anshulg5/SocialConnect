package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.JsonArrayNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

public class JsonArrayNodeFactory implements NodeFactory {
    static {
        JsonArrayNodeFactory jsonArrayOperator = new JsonArrayNodeFactory();
        NodeManager.registerNodeFactory("JSONArr",jsonArrayOperator);
    }

    @Override
    public Node getInstance(JsonType json) {
        return new JsonArrayNode(json);
    }
}
