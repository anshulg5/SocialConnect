package org.config;

import org.json.JSONObject;

public class EqualsNode extends Operation<Node<?>> implements Node<Boolean> {
    @Override
    public Boolean apply(JSONObject msg) {
        return arg.get(0).apply(msg).equals(arg.get(1).apply(msg));
    }
}
