package org.config.factory;

import org.config.EqualsNode;
import org.config.Node;
import org.config.NodeManager;
import org.json.JSONArray;

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
