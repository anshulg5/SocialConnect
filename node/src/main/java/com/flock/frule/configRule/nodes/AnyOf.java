package com.flock.frule.configRule.nodes;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

import java.util.Collection;

// Does Nothing for now.
public class AnyOf<T> implements Node<Boolean> {

    Node<T> object;
    Collection<Node<T>> nodeCollection;

    @Override
    public Boolean apply(JsonData bindings) {
        return null;
    }
}
