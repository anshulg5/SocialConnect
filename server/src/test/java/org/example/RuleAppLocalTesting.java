package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonParser;
import com.google.inject.Injector;
import org.example.app.RuleApp;
import org.example.helpers.GuavaListBuilder;
import org.example.helpers.GuavaMapBuilder;
import org.example.main.Main;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RuleAppLocalTesting {
    RuleApp ruleApp;
    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws ClassNotFoundException, JsonProcessingException, InstantiationException, IllegalAccessException {
        System.setProperty("ENV","local");
        new RuleAppLocalTesting();

    }

    RuleAppLocalTesting() throws ClassNotFoundException, InstantiationException, IllegalAccessException, JsonProcessingException {
        Injector injector = Main.createInjector();
        ruleApp = injector.getInstance(RuleApp.class);


        try {
//            checkIndexOnPath();
        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getStackTrace());
            System.out.println(e);
            System.out.println("hi");
            throw new RuntimeException(e);
        }

        ImmutableMapTesting();
//        classTesting();
//        checkIndexOnPath();
//        single2doublequotes();
//        testGson();
    }

    private void ImmutableMapTesting() {
        GuavaMapBuilder<String, Object> mapBuilder = new GuavaMapBuilder<>();
        GuavaListBuilder<Object> listBuilder = new GuavaListBuilder<>();

        ImmutableMap<String, Object> map = mapBuilder.get()
                                        .put("AND",mapBuilder.get()
                                                .put("AND",mapBuilder.get()
                                                        .put("AND","sd")
                                                        .put("OR","sd")
                                                        .build())
                                                .build())
                                        .put("EQ","Dsf")
                                        .build();

        ImmutableMap<String, Object> map2 = mapBuilder.get()
                .put("AND",listBuilder.get()
                    .add(mapBuilder.get()
                        .put("BOOL","true")
                        .build())
                    .add(mapBuilder.get()
                        .put("PTH",mapBuilder.get()
                            .put("STRLIST",listBuilder.get()
                                .add("path_to")
                                .build())
                            .build())
                        .build())
                    .build())
                .build();

        ImmutableMap<String, Object> map3 = mapBuilder.get()
                .put("AND",listBuilder.get()
                    .add(mapBuilder.get()
                        .put("EQ",listBuilder.get()
                            .add(mapBuilder.get()
                                .put("PTH",mapBuilder.get()
                                    .put("STRLIST",listBuilder.get()
                                        .add("from","firstname")
                                        .build())
                                    .build())
                                .build())
                            .add(mapBuilder.get()
                                .put("STR","Anshul")
                                .build())
                            .build())
                        .build())
                    .add(mapBuilder.get()
                        .put("NOT",listBuilder.get()
                            .add(mapBuilder.get()
                                .put("EQ",listBuilder.get()
                                    .add(mapBuilder.get()
                                        .put("PTH",mapBuilder.get().put("STRLIST",listBuilder.get().add("text").build()).build())
                                        .build())
                                    .add(mapBuilder.get()
                                        .put("STR","Hi")
                                        .build())
                                    .build())
                                .build())
                            .build())
                        .build())
                    .build())
                .build();

        System.out.println(map3);
        ruleApp.deleteRule("guavaMapBuilder");

        try {
            ruleApp.addRule("guavaMapBuilder",map3);
        } catch (IllegalAccessException | JsonProcessingException e) {
            e.printStackTrace();
        }
        ruleApp.deleteRule("guavaMapBuilder");

        System.out.println(map);
//        Map<String,Object> map2 = (Map<String, Object>) map.get("AND");
//        System.out.println(map2);


    }

//    private void JSONTesting(){
//        String json = Json.createObjectBuilder()
//                .add("AND", Json.createArrayBuilder()
//                    .add(Json.createObjectBuilder()
//                        .add("EQ", Json.createArrayBuilder()
//                            .add(Json.createObjectBuilder()
//                                .add))))
//    }

    void classTesting() {
        NodeStringFactory builder = new NodeStringFactory();
        NodeString node = (new NodeString("AND"))
                .add((new NodeString("EQ"))
                        .add((new NodeString("PTH"))
                                .add((new NodeString("STRLIST",Arrays.asList("from","firstname").toString()))))
                        .add((new NodeString("STR","Anshul"))))
                .add((new NodeString("NOT"))
                        .add((new NodeString("EQ"))
                                .add((new NodeString("PTH"))
                                        .add((new NodeString("STRLIST",Arrays.asList("text").toString()))))
                                .add((new NodeString("STR","hi")))));

        System.out.println(node);

        System.out.println(Arrays.<String>asList("from","firstname").toString());



        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = null;
        try {
            rule = mapper.readValue(node.toString(),new TypeReference<Map<String,Object>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(rule);

        try {
            ruleApp.addRule("qwert",rule);
        } catch (IllegalAccessException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    void single2doublequotes(){
        String str = "{'EQ':[{'PTH':{'STRLIST':['arr','as']}},{'BOOL':true}]}";



        String str2 = str.replace("'","\"");
        System.out.println(str2);
    }


    void testGson() throws ClassNotFoundException, JsonProcessingException, InstantiationException, IllegalAccessException {
        String ruleString = JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { STRLIST: [arr,1] }" +
                                "}," +
                                "{" +
                                    "INT: 52" +
                                "}" +
                            "]" +
                      "}").toString();
        System.out.println(ruleString);
        String ruleID = "fi";

        Map<String, Object> ruleMap = null;
        try {
            ruleMap = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ruleMap);
        ruleApp.addRule(ruleID,ruleMap);

        Map<String,Object> msgMap = new HashMap<>();
        msgMap.put("arr", Arrays.asList(1,52,3));
//        msgMap.put("arr","dsfdf");
        msgMap.put("random","sadsad");

        boolean status = ruleApp.validateByID("fi",msgMap);
        System.out.println(status);

        ruleApp.deleteRule("fi");
        System.out.println(ruleApp.fetchRules().get("fi"));


    }

    void checkNullPoinerException(){
        System.out.println(ruleApp.fetchRules());
        Map<String,String> map = new HashMap<>();
        map.put("dfas","Asdsa");
        boolean success = ruleApp.validateByID("fi",map);
        System.out.println(success);
    }

    void checkIndexOnPath() throws ClassNotFoundException, JsonProcessingException, InstantiationException, IllegalAccessException {
        ruleApp.deleteRule("fi");
        String ruleString = "{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"arr\",\"1\"]}},{\"INT\":52}]}";
        String ruleID = "fi";
        Map<String, Object> ruleMap = null;
        try {
           ruleMap = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ruleMap);
        ruleApp.addRule(ruleID,ruleMap);

        Map<String,Object> msgMap = new HashMap<>();
        msgMap.put("arr", Arrays.asList(1,52,3));
//        msgMap.put("arr","dsfdf");
        msgMap.put("random","sadsad");

        boolean status = ruleApp.validateByID("fi",msgMap);
        System.out.println(status);

        ruleApp.deleteRule("fi");
        System.out.println(ruleApp.fetchRules().get("fi"));




    }

//    class JsonBuilder{
//        public String get(String s){
//            switch (s){
//                case '{':
//                    return Json.createObjectBuilder();
//                case '[':
//                    return Json.createArrayBuilder();
//            }
//        }
//    }
}
