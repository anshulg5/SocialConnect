package com.flock.frule.configRule.nodes;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

import java.util.Collection;

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
    public Collection<String> apply(JsonData input) {
        return stringCollection;
    }

}
