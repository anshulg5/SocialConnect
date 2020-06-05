package com.flock.frule.configRule.nodes.vars;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VarJsonPrimitvieNode implements Node<JsonPrimitive> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Node arg;

    public VarJsonPrimitvieNode(Node arg) {
        this.arg = arg;
    }

    @Override
    public JsonPrimitive apply(JsonData input) {
        return new JsonPrimitive(arg.apply(input));
    }
}
