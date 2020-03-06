package org.config.factory;

import org.config.Node;
import org.config.NodeManager;
import org.config.ORNode;
import org.json.JSONArray;

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
