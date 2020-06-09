//package com.flock.frule;
//
//import com.flock.frule.configRule.nodes.primitivenodes.StringNode;
//import com.flock.frule.model.*;
//import com.google.common.collect.ImmutableMap;
//
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//public class TargetExample {
//
//    public static void main(String[] args) {
//        Map<String, DataOperation<JsonData>> vars = ImmutableMap.of(
//                "var_filedata", new ReadJsonFile(new StringNode("server/src/test/resources/example.json"))
//        );
//        TargetAction action = input -> {
//            System.out.println("got input data for action: " + input);
//            return CompletableFuture.completedFuture(null);
//        };
//        Target target = new Target(vars, action);
//        JsonData input = JsonData.createEmpty();
//        input.put("k1", "v1");
//        input.put("k2", "v2");
//        target.execute(input);
//    }
//}
