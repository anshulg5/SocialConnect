package com.flock.frule.model;

import com.flock.frule.NodeManager;
import com.flock.frule.model.jsondata.JsonType;
import com.flock.frule.util.Serializer;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;

public class Rule {
    private final String ID;
    private final String ruleString;
    private final Node<Boolean> rootNode;
    private final Map<String,Target> targets = new HashMap<>();

    public Rule(String ID, JsonType json) throws InvalidObjectException, IllegalAccessException {
        this.ID = ID;
        this.ruleString = Serializer.toJson(json);
        rootNode = NodeManager.create(json.asObject());
    }

    public void apply(JsonType input){
        if(validate(input).getStatus())
            targets.forEach((id, target) -> target.execute(input));
    }

    public Response addTarget(Target target) {
        Response response = new Response();
        String targetId = target.getId();
        if(targets.containsKey(targetId)){
            response.setStatus(false);
            response.setMessage("Target with id: " + targetId + " already exist in Rule id: " + ID);
            return response;
        }
        targets.put(targetId,target);
        response.setStatus(true);
        response.setMessage("Target with id: "+ targetId + " added in Rule id: " + ID);
        return response;
    }

    public Response removeTarget(String targetId) {
        Response response = new Response();
        if(!targets.containsKey(targetId)){
            response.setStatus(false);
            response.setMessage("No target with id: " + targetId + " exists in Rule id: " + ID);
            return response;
        }
        targets.remove(targetId);
        response.setStatus(true);
        response.setMessage("Target with id: "+ targetId + " deleted from Rule id: " + ID);
        return response;
    }

    public Response validate(JsonType input){
        Response response = new Response();
        boolean success;
        try{
            success = rootNode.apply(input);
        } catch (Exception e){
            success = false;
        }
        response.setStatus(success);
        if(success){
            response.setMessage("Input validation passed on Rule id: "+ ID);
        } else {
            response.setMessage("Input validation failed on Rule id: " + ID);
        }
        return response;
    }

    public String getID() {
        return ID;
    }

    public String getRuleString() {
        return ruleString;
    }

}
