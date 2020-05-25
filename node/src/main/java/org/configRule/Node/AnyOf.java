package org.configRule.Node;

import com.flock.frule.model.JsonData;
import org.example.Node;

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
