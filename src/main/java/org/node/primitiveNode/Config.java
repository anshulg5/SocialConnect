package org.node.primitiveNode;

import org.json.JSONObject;
import org.node.Node;

import java.util.List;

public class Config<T> implements Node<T> {
    List<Node> arg;

    @Override
    public T apply(JSONObject msg) {
        return (T)arg.get(0).apply(msg);
    }

    public void addArg(Node node) {
        arg.add(node);
    }
}
