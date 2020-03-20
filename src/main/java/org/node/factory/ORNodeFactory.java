package org.node.factory;

import org.json.JSONArray;
import org.node.Node;
import org.node.NodeManager;
import org.node.ORNode;

public class ORNodeFactory implements INodeFactory {

    static ORNodeFactory orNodeFactory = null;

    public static INodeFactory getInstance() {
        if(orNodeFactory == null)
            orNodeFactory = new ORNodeFactory();
        return orNodeFactory;
    }

    @Override
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ORNode orNode = new ORNode();
        JSONArray jsonArray = (JSONArray)json;
        for(int i=0;i<jsonArray.length();++i){
            orNode.put(NodeManager.createNode(jsonArray.getJSONObject(i)));
        }
        return orNode;
    }
}
