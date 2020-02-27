package org.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONObject;

import java.util.List;

public class NOTNode extends Operation<Node<Boolean>> implements Node<Boolean> {
    @Override
    public Boolean apply(JSONObject msg) {
        return !arg.get(0).apply(msg);
    }
}
