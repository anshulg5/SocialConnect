//package com.flock.frule.configRule.nodes.vars;
//
//import com.flock.frule.model.JsonData;
//import com.flock.frule.model.Node;
//import com.flock.frule.model.jsondata.JsonArray;
//import com.flock.frule.model.jsondata.JsonType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class VarJsonArrayNode implements Node<JsonArray> {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    private final List<Node<JsonType>> arg;
//
//    public VarJsonArrayNode(List<Node<JsonType>> arg) {
//        this.arg = arg;
//    }
//
//    @Override
//    public JsonArray apply(JsonData input) {
//        JsonArray jsonArray = new JsonArray();
//        arg.forEach(elem -> jsonArray.add(elem.apply(input)));
//        log.debug(" applied input to node objects in JsonArray {}",jsonArray);
//        return jsonArray;
//    }
//}
