package org.node.factory;

import org.json.JSONArray;
import org.node.EqualsNode;
import org.node.Node;
import org.node.NodeManager;

public class EqualsNodeFactory implements INodeFactory {

    static EqualsNodeFactory equalsNodeFactory = null;

    public static INodeFactory getInstance() {
        if(equalsNodeFactory == null)
            equalsNodeFactory = new EqualsNodeFactory();
        return equalsNodeFactory;
    }

    @Override
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        EqualsNode equalsNode = new EqualsNode();
        JSONArray jsonArray = (JSONArray)json;
        for(int i=0;i<jsonArray.length();++i){
            equalsNode.put(NodeManager.createNode(jsonArray.getJSONObject(i)));
        }
        return equalsNode;
    }
}
