package org.node;

import org.json.JSONObject;

public class ANDNode extends Operation<Node<Boolean>> implements Node<Boolean>{
    @Override
    public Boolean apply(JSONObject msg) {
        Boolean and = Boolean.TRUE;
        for(Node<Boolean> node: arg)
            and&=node.apply(msg);
        return and;
    }
}
