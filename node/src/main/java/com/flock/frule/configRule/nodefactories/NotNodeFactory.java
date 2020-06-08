package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.NotNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Map;

public class NotNodeFactory implements NodeFactory {

    static {
        NotNodeFactory notOperator = new NotNodeFactory();
        NodeManager.registerNodeFactory("NOT",notOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new NotNode((List<Map<String, Object>>) value,symbolTable);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new NotNode((List<Map<String, Object>>) value);
    }

    @Override
    public Node getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new NotNode(json.asObject());
    }
}
