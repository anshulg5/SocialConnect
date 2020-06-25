package com.flock.frule.model;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.targetaction.TargetAction;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import com.flock.frule.util.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class Target {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String id;
    private final Map<String, Node<JsonType>> vars;
    private final TargetAction action;
    private String targetJSonString;

    private Target(String id, Map<String, Node<JsonType>> vars, TargetAction action) {
        this.id = id;
        this.vars = vars;
        this.action = action;
    }

    public CompletionStage<Void> execute(JsonType input) {
        JsonType moreJson = decorate(input);
        log.debug("executing action with: {}", moreJson);
        return action.apply(moreJson);
    }

    private JsonType decorate(JsonType input) {
        JsonObject copy = new JsonObject();
        copy.merge(input.asObject());
        vars.forEach((var, mapper) -> copy.put(var, mapper.apply(input)));
        return copy;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.targetJSonString;
    }

    public void setTargetJSonString(String jsonString) {
        this.targetJSonString = jsonString;
    }


    public static class Builder {
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

            Target target = new Target(id,vars,targetAction);
            target.setTargetJSonString(Serializer.toJson(json));
            return target;
        }
    }
}
