package com.flock.frule.model;

import com.flock.frule.configRule.nodes.targetaction.TargetAction;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CompletionStage;

public class Target {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String id;
    private final Map<String, Node<JsonType>> vars;
    private final TargetAction action;

    public Target(String id, Map<String, Node<JsonType>> vars, TargetAction action) {
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
}
