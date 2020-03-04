package org.config;

import org.json.JSONObject;

public class Rule {
    String ID;
    JSONObject jsonString;
    Node rootNode;

    Rule(String ID, JSONObject jsonString) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ID = ID;
        this.jsonString = jsonString;
        rootNode = NodeFactory.createNode(jsonString);
    }

    Boolean validate(JSONObject msg){
        return (Boolean) rootNode.apply(msg);
    }

    String getID(){
        return ID;
    }

    JSONObject getJsonString(){
        return jsonString;
    }

    void setID(String newID){
        ID = newID;
    }
}
