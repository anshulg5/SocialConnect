package org.configRule.Node;

import org.example.Node;

import java.util.Collection;
import java.util.Map;

public class CollectionStringNode implements Node<Collection<String>> {

    Collection<String>stringCollection;

    public Collection<String> getStringCollection() {
        return stringCollection;
    }
    public void setStringCollection(Collection<String> stringCollection) {
        this.stringCollection = stringCollection;
    }

    public CollectionStringNode(Collection<String> stringCollection) {
        this.stringCollection = stringCollection;
    }

    @Override
    public Collection<String> apply(Map<String, ?> input) {
        return stringCollection;
    }

}
