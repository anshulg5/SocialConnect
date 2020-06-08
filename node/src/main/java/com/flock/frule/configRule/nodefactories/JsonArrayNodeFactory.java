package com.flock.frule.configRule.nodefactories;

import com.flock.frule.configRule.nodes.JsonArrayNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.Map;

public class JsonArrayNodeFactory implements NodeFactory {
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return null;
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return null;
    }

    @Override
    public Node getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new JsonArrayNode(json.asArray());
    }
}
