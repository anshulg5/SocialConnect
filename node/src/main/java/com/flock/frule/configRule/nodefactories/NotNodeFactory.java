package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.NotNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class NotNodeFactory implements NodeFactory<Boolean> {

    static {
        NotNodeFactory notOperator = new NotNodeFactory();
        NodeManager.registerNodeFactory("NOT",notOperator);
    }

    @Override
    public Node<Boolean> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new NotNode(json.asObject());
    }
}
