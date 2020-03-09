package org.node;

import org.json.JSONObject;

public class NOTNode extends Operation<Node<Boolean>> implements Node<Boolean> {
    @Override
    public Boolean apply(JSONObject msg) {
        return !arg.get(0).apply(msg);
    }
}
