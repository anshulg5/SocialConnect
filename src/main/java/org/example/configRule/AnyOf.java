package org.example.configRule;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class AnyOf<T> implements Node<Boolean> {

    Node<T> object;
    Collection<Node<T>> nodeCollection;

    @Override
    public Boolean apply(Map<String, ?> bindings) {
        return null;
    }
}
