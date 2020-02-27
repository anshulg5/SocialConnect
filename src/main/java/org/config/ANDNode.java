package org.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class ANDNode extends Operation<Node<Boolean>> implements Node<Boolean>{
    @Override
    public Boolean apply(JSONObject msg) {
        Boolean and = Boolean.TRUE;
        for(Node<Boolean> node: arg)
            and&=node.apply(msg);
        return and;
    }
}
