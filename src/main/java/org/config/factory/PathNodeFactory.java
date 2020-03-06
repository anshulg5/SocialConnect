package org.config.factory;

import org.config.Node;
import org.config.NodeManager;
import org.config.PathNode;
import org.json.JSONObject;

public class PathNodeFactory implements INodeFactory{

    static PathNodeFactory pathNodeFactory = null;

    public static INodeFactory getInstance() {
        if(pathNodeFactory == null)
            pathNodeFactory =new PathNodeFactory();
        return pathNodeFactory;
    }

    @Override
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        PathNode pathNode;
        if(json instanceof JSONObject){
            pathNode = new PathNode(NodeManager.createNode((JSONObject)json));
        }
        else {
            pathNode = new PathNode(NodeManager.createStrlist(json));
        }
        return pathNode;
    }
}

