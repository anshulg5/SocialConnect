package org.config;

import org.json.JSONObject;

import java.util.List;

public class PathNode<T> implements Node<T> {
    Node<List<String>> arg;

    public PathNode(Node<List<String>> value){
        this.arg = value;
    }

    @Override
    public T apply(JSONObject msg) {
//        ConfigStrlist temp = new ConfigStrlist(arg.apply(msg));
//        List<String> path = (temp).apply(msg);
        List<String> path = arg.apply(msg);
        return Eval.evalPath(msg, path);
    }
}
