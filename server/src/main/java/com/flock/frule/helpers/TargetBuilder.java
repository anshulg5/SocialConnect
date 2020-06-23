package com.flock.frule.helpers;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.targetaction.TargetAction;
import com.flock.frule.model.Node;
import com.flock.frule.model.Target;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class TargetBuilder {
    public static Target fromJson(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        String id = json.get("id").asPrimitive().getAsString();

        JsonObject varsJson = json.get("prepare").asObject();
        Map<String, Node<JsonType>> vars = new HashMap<>();
        for(Map.Entry<String,JsonType> e: varsJson.entrySet()) {
            Node<JsonType> node = NodeManager.create(e.getValue());
            vars.put(e.getKey(), node);
        }

        JsonObject targetActionJson = json.get("action").asObject();
        TargetAction targetAction = (TargetAction) NodeManager.<CompletionStage<Void>>create(targetActionJson);

        return new Target(id,vars,targetAction);
    }
}
