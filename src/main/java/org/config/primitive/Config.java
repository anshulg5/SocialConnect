package org.config.primitive;

import org.config.Node;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

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
