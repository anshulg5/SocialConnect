package org.node.factory;

import org.json.JSONArray;
import org.node.ANDNode;
import org.node.Node;
import org.node.NodeManager;

public class ANDNodeFactory implements INodeFactory {

    static ANDNodeFactory andNodeFactory = null;

    public static INodeFactory getInstance() {
        if(andNodeFactory == null)
            andNodeFactory = new ANDNodeFactory();
        return andNodeFactory;
    }

    @Override
    public Node create(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ANDNode andNode = new ANDNode();
        JSONArray jsonArray = (JSONArray)json;
        for(int i=0;i<jsonArray.length();++i){
            andNode.put(NodeManager.createNode(jsonArray.getJSONObject(i)));
        }
        return andNode;
    }

}
