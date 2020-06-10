package com.flock.frule.model;

import com.flock.frule.NodeManager;
import com.flock.frule.model.jsondata.JsonType;
import com.flock.frule.util.Serializer;

import java.io.InvalidObjectException;

public class Rule {
    private final String ID;
    private final String ruleString;
    private final Node rootNode;

    public Rule(String ID, JsonType json) throws InvalidObjectException, IllegalAccessException {
        this.ID = ID;
        this.ruleString = Serializer.toJson(json);
        rootNode = NodeManager.create(json.asObject());
    }

    public Boolean validate(JsonType input){
        return (Boolean) rootNode.apply(input);
    }

    public String getID() {
        return ID;
    }

    public String getRuleString() {
        return ruleString;
    }

}
