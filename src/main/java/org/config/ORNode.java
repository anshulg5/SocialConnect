package org.config;

import org.json.JSONObject;

public class ORNode extends Operation<Node<Boolean>> implements Node<Boolean>{
    @Override
    public Boolean apply(JSONObject msg) {
        Boolean or = Boolean.FALSE;
        for(Node<Boolean> node: arg)
            or|=node.apply(msg);
        return or;
    }
}
