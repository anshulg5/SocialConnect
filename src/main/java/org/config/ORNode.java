package org.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONObject;

import java.util.List;

public class ORNode extends Operation<Node<Boolean>> implements Node<Boolean>{
    @Override
    public Boolean apply(JSONObject msg) {
        Boolean or = Boolean.FALSE;
        for(Node<Boolean> node: arg)
            or|=node.apply(msg);
        return or;
    }
}
