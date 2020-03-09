package org.node.factory;

import org.json.JSONArray;
import org.node.NOTNode;
import org.node.Node;
import org.node.NodeManager;

public class NOTNodeFactory implements INodeFactory {

    static NOTNodeFactory notNodeFactory = null;

    public static INodeFactory getInstance() {
        if(notNodeFactory == null)
            notNodeFactory = new NOTNodeFactory();
        return notNodeFactory;
    }

    @Override
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        NOTNode notNode = new NOTNode();
        JSONArray jsonArray = (JSONArray)json;
        for(int i=0;i<jsonArray.length();++i){
            notNode.put(NodeManager.createNode(jsonArray.getJSONObject(i)));
        }
        return notNode;
    }
}
