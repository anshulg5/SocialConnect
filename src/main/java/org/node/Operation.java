package org.node;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation<T> {
    List<T> arg = new ArrayList<>();

    public void put(T node){
        arg.add(node);
    }
}
