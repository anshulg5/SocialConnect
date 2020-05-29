package org.configRule.Node.vars;

import com.flock.frule.model.JsonData;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.example.Node;
import org.example.NodeManager;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletionException;

public class VarJsonData implements Node<JsonData> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final JsonData arg;

    VarJsonData(JsonData jsonData) {
        JsonData copy = JsonData.createEmpty();
        List<String> keyList = jsonData.Keys();
        keyList.forEach(k -> copy.put(k,recursivelyApply(jsonData.get(k,Object.class),null)));
        this.arg = copy;
        log.debug("replaced node spec by node object in JsonData: {}",this.arg);
    }

    @Override
    public JsonData apply(JsonData input) {
        JsonData copy = JsonData.createEmpty();
        List<String> keyList = arg.Keys();
        keyList.forEach(k -> copy.put(k,recursivelyApply(arg.get(k,Object.class),input)));
        log.debug(" applied input to node objects in JsonData {}",copy);
        return copy;
    }

    // input provided as null during construction of Node objects from node spec
    private <T> T recursivelyApply(T root, @Nullable JsonData input) {
        if(isNodeJson(root)) {
            try {
                return (T) NodeManager.create((Map) root);
            } catch (IllegalAccessException e) {
                throw new CompletionException(e);
            }
        }
        if(root instanceof Map){
            Map<String, Object> map = (Map) root;
            Map<String, Object> copy = new HashMap<>();

            map.forEach((k,v) -> copy.put(k,recursivelyApply(v,input)));
            return (T)copy;
        }
        if(root instanceof List){
            List<Object> list = (List) root;
            List<Object> copy = new ArrayList<>();

            list.forEach(elem -> copy.add(recursivelyApply(elem,input)));
            return (T)copy;
        }
        if(root instanceof Node && input!=null)
            return (T)((Node)root).apply(input);
        return root;
    }

    private Boolean isNodeJson(Object obj){
        if(obj instanceof Map){
            Map<String, Object> map = (Map) obj;
            Set<String> keySet = map.keySet();
            return keySet.size() == 1 && NodeManager.containsNode(keySet.iterator().next());
        }
        return false;
    }
}
