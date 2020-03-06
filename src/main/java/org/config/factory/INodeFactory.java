package org.config.factory;

import org.config.Node;

public interface INodeFactory {
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException;
}
