package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.CollectionStringNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class CollectionStringNodeFactory implements NodeFactory<JsonArray> {
    static {
        CollectionStringNodeFactory collectionStringOperator = new CollectionStringNodeFactory();
        NodeManager.registerNodeFactory("STRLIST",collectionStringOperator);
    }

    @Override
    public Node<JsonArray> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new CollectionStringNode(json);
    }

}
