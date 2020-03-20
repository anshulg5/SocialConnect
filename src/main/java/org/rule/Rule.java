package org.rule;

import org.json.JSONObject;
import org.node.Node;
import org.node.NodeManager;

public class Rule {
    String ID;
    JSONObject jsonObject;
    Node rootNode;

    public Rule(String ID, JSONObject jsonObject) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ID = ID;
        this.jsonObject = jsonObject;
        rootNode = NodeManager.createNode(jsonObject);
    }

    public Rule(String ID, String jsonString) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ID =ID;
        this.jsonObject = new JSONObject(jsonString);
        rootNode = NodeManager.createNode(jsonObject);
    }

    public Boolean validate(JSONObject msg){
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
        rootNode = NodeManager.createNode(jsonObject);
    }
}
