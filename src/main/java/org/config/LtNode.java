package org.config;

import org.json.JSONObject;

import java.util.List;

public class LtNode implements Node<Boolean> {
    List<Node<?>> arg;

    @Override
    public Boolean apply(JSONObject msg) {
        return (Integer)arg.get(0).apply(msg) < (Integer)arg.get(1).apply(msg);
    }

    public void put(Node<?> node) {
        arg.add(node);
    }
}

