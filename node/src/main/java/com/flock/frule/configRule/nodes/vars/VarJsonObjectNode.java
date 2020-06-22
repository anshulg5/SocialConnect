//package com.flock.frule.configRule.nodes.vars;
//
//import com.flock.frule.model.JsonData;
//import com.flock.frule.model.Node;
//import com.flock.frule.model.jsondata.JsonObject;
//import com.flock.frule.model.jsondata.JsonType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Map;
//
//public class VarJsonObjectNode implements Node<JsonObject> {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final String TYPE = "VarJSONObj";
//    private final Map<String, Node<JsonType>> arg;
//
//    VarJsonObjectNode(JsonObject json) {
////        log.debug("replaced node spec by node object in JsonData: {}",this.arg);
//    }
//
//    @Override
//    public JsonObject apply(JsonType input) {
//        JsonObject jsonObject = new JsonObject();
//        arg.forEach((k,v) -> jsonObject.put(k,v.apply(input)));
//        log.debug(" applied input to node objects in JsonObject {}",jsonObject);
//        return jsonObject;
//    }
//}
