package org.config;

import org.json.JSONObject;

public class Rule {
    String ID;
    JSONObject jsonObject;
    Node rootNode;

    Rule(String ID, JSONObject jsonObject) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ID = ID;
        this.jsonObject = jsonObject;
        rootNode = NodeFactory.createNode(jsonObject);
    }

    public Rule(String ID, String jsonString) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ID =ID;
        this.jsonObject = new JSONObject(jsonString);
        rootNode = NodeFactory.createNode(jsonObject);
    }

    Boolean validate(JSONObject msg){
        return (Boolean) rootNode.apply(msg);
    }

    public String getID(){
        return ID;
    }

    JSONObject getJsonObject(){
        return jsonObject;
    }

    public String getJsonString(){
        return jsonObject.toString();
    }

    void setID(String newID){
        ID = newID;
    }

    void setJsonObject(JSONObject jsonObject) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.jsonObject = jsonObject;
        rootNode = NodeFactory.createNode(jsonObject);
    }
}
