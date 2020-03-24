package org.configRule.Node;

import org.example.Node;

import java.util.Collection;
import java.util.Map;

// Does Nothing for now.
public class AnyOf<T> implements Node<Boolean> {

    Node<T> object;
    Collection<Node<T>> nodeCollection;

    @Override
    public Boolean apply(Map<String, ?> bindings) {
        return null;
    }
}
