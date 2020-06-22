//package com.flock.frule.configRule.nodes.vars;
//
//import com.flock.frule.NodeManager;
//import com.flock.frule.model.JsonData;
//import com.flock.frule.model.Node;
//import com.flock.frule.model.jsondata.JsonArray;
//import com.flock.frule.model.jsondata.JsonObject;
//import com.flock.frule.model.jsondata.JsonType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.InvalidObjectException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class VarJsonArrayNode implements Node<JsonArray> {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final String TYPE = "VarJSONArr";
//    private final List<Node<JsonType>> arg;
//
//    public VarJsonArrayNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
//        JsonType arg = json.get(TYPE);
//        if(arg.isArray()){
//            JsonArray arr = arg.asArray();
//            this.arg = new ArrayList<>();
//            for(JsonType elem : arr){
//                this.arg.add(NodeManager.create(elem));
//            }
//        } else
//            throw new RuntimeException();
//    }
//
//    @Override
//    public JsonArray apply(JsonType input) {
//        JsonArray jsonArray = new JsonArray();
//        arg.forEach(elem -> jsonArray.add(elem.apply(input)));
//        log.debug(" applied input to node objects in JsonArray {}",jsonArray);
//        return jsonArray;
//    }
//}
