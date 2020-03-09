package org.node.factory;

import org.node.Node;

public interface INodeFactory {
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException;
}
