package org.node.factory;

import org.json.JSONObject;
import org.node.Node;
import org.node.NodeManager;
import org.node.PathNode;

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

