package org.config;

import org.config.primitive.ConfigStrlist;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collection;
import java.util.List;

public class PathNode<T> implements Node<T> {
    Node<List<String>> arg;

    PathNode(Node<List<String>> value){
        this.arg = value;
    }

    @Override
    public T apply(JSONObject msg) {
//        ConfigStrlist temp = new ConfigStrlist(arg.apply(msg));
//        List<String> path = (temp).apply(msg);
        List<String> path = arg.apply(msg);
        return Eval.evalPath(msg, path);
    }

//    T evalPath(JSONObject msg, List<String> path){
//
//        return null;
//    }

//    public void addArg(Node node) {
//        arg.add(node);
//    }
}
