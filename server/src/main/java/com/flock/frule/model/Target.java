package com.flock.frule.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CompletionStage;

public class Target {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, DataOperation<JsonData>> vars;
    private final TargetAction action;

    public Target(Map<String, DataOperation<JsonData>> vars, TargetAction action) {
        this.vars = vars;
        this.action = action;
    }

    public CompletionStage<Void> execute(JsonData input) {
        JsonData moreJsonData = decorate(input);
        log.debug("executing action with: {}", moreJsonData);
        return action.execute(moreJsonData);
    }

    private JsonData decorate(JsonData jsonData) {
        JsonData copy = JsonData.createEmpty();
        copy.merge(jsonData);
        vars.forEach((var, mapper) -> copy.put(var, mapper.execute(jsonData)));
        return copy;
    }
}
