package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.app.MediatorApp;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.*;

public class RuleApp {


    private static MediatorApp app = null;

    static List<Node> sampleRuleList = new ArrayList<>();

    public static MediatorApp getApp() {
        return app;
    }

    public static void setApp(MediatorApp app) {
        RuleApp.app = app;
    }

    public static void loadSampleRules(){
        System.out.println("\nLoading Sample Rule configurations");

        try {

            ObjectMapper mapper = new ObjectMapper();

            String rule = "{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshu\"}]}";
            Map<String, Object> ruleMap = (Map<String, Object>) mapper.readValue(rule,new TypeReference<Map<String,Object>>(){});
            System.out.println(rule);

            String rule2 = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";
            Map<String, Object> ruleMap2 = (Map<String, Object>) mapper.readValue(rule2,new TypeReference<Map<String,Object>>(){});
            System.out.println(rule2);

            String rule3 = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshu\"}]},{\"BOOL\":true}]}";
            Map<String, Object> ruleMap3 = (Map<String, Object>) mapper.readValue(rule3,new TypeReference<Map<String,Object>>(){});
            System.out.println(rule3);


            sampleRuleList.add(NodeManager.create(ruleMap));
            sampleRuleList.add(NodeManager.create(ruleMap2));
            sampleRuleList.add(NodeManager.create(ruleMap3));
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void demo(){
        ObjectMapper mapper = new ObjectMapper();
        String msg = "{\"name\":54,\"arr\":[\"random_str\",[\"path_to\"]],\"path_to\":[\"name\"]}";
        System.out.println(msg);
        Map<String, Object> msgMap = null;

        try {
            msgMap = (Map<String, Object>) mapper.readValue(msg,new TypeReference<Map<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rule = "{\"EQ\":[{\"PTH\":{\"PTH\":{\"PTH\":{\"STRLIST\":[\"arr\",\"1\"]}}}},{\"INT\":54}]}";
        System.out.println(rule);
        Node config = null;
        try {
            Map<String, Object> ruleMap = (Map<String, Object>) mapper.readValue(rule,new TypeReference<Map<String,Object>>(){});
            config = NodeManager.create(ruleMap);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(config.apply(msgMap));

        String rule2 = "{\"EQ\":[{\"PTH\":{\"PTH\":{\"STRLIST\":[\"path_to\"]}}},{\"STR\":\"Anshul\"}]}";
        System.out.println(rule2);
        Node config2 = null;
        try {
            Map<String, Object> ruleMap2 = (Map<String, Object>) mapper.readValue(rule2,new TypeReference<Map<String,Object>>(){});
            config2 = NodeManager.create(ruleMap2);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(config2.apply(msgMap));
    }

    public Boolean validateSampleRules(int index, Map<String,?> inp){
        System.out.println("Validating the message on Config "+index);
        return (Boolean) sampleRuleList.get(index).apply(inp);
    }


    public static void main(String[] args) throws ClassNotFoundException, IOException {

        /*
            input rule 1 : {
                             "AND" : [
                                       { 'EQ' : [   {'PATH' : { 'STRLST' : [ "Address" , "City" ] } } ,
                                                    { 'STR' : "Ahmedabad" }  ]
                                       },
                                       { 'EQ' : [   {'PATH' : { 'STRLST' : [ "Address" , "State" ] } } ,
                                                    { 'STR' : "Gujarat" }  ]
                                       }
                                     ]
                           }

            input 2 : { 'EQ' : [ { 'PATH' : { 'PATH' : 'Name' } }  ,
                                 {'STR' : 'GUJRAT'}
                               ]
                      }

            input 3 : { 'LET' : { 'DEF' : [ { 'Name' : "x", 'Value' : {'PATH' : { 'STRLST' : [ "Mentor"] } } } ],
                                   'BODY' : {
                                                'EQ' : [ { 'STR' : 'Hemanshu'} ,
                                                         { 'VAR' : "x" } ]
                                            }
                                }
                      }

            Doc : {
                     'Mentor' : "Hemanshu"
                     'Town' : ["Address" , "City" ],
                     'Address' : {
                                    'City' : "Ahmedabad",
                                    'State' : "Gujarat"
                                 }
                  }
        */

//        Map<Operator, Object> input = ImmutableMap.<Operator, Object>builder()
//                .put(Operator.AND, ImmutableList.of(
//                        ImmutableMap.builder()
//                                .put(Operator.EQ, ImmutableList.of(
//                                        ImmutableMap.of(Operator.PATH, ImmutableMap.of(Operator.STRLST, Lists.newArrayList("Address","City"))),
//                                        ImmutableMap.of(Operator.STR, "Ahmedabad")
//                                )).build(),
//                        ImmutableMap.builder()
//                                .put(Operator.EQ, ImmutableList.of(
//                                        ImmutableMap.of(Operator.PATH, ImmutableMap.of(Operator.STRLST, Lists.newArrayList("Address","State"))),
//                                        ImmutableMap.of(Operator.STR, "Gujarat")
//                                )).build()
//                ))
//                .build();

//        Map<Operator,Object> input2 = ImmutableMap.<Operator,Object>builder()
//                .put(Operator,ImmutableList.of(
//                        ImmutableMap.of(Operator.PATH,
//                                ImmutableMap.of(Operator.PATH,ImmutableMap.of(Operator.STRLST,Lists.newArrayList("Town")))),
//                        ImmutableMap.of(Operator.STR,"Ahmedabad")
//                )).build();

//        ImmutableMap<Operator, Object> input3 = ImmutableMap.<Operator,Object>builder()
//                .put(Operator.LET,ImmutableMap.of(
//                        Operator.DEF, ImmutableList.of(
//                                ImmutableMap.of(Operator.NAME,"x",Operator.VALUE,ImmutableMap.of(Operator.PATH, ImmutableMap.of(Operator.STRLST, Lists.newArrayList("Mentor"))))
//                        ),
//                        Operator.BODY,ImmutableMap.of(Operator.EQ, ImmutableList.of(
//                                        ImmutableMap.of(Operator.VAR,"x"),
//                                        ImmutableMap.of(Operator.STR, "Hemanshu")))
//                )).build();
//
//        ImmutableMap<Operator,Object> input4 = ImmutableMap.<Operator,Object>builder()
//                .put(Operator.EQ,ImmutableList.of(
//                                                ImmutableMap.of(Operator.FREAD,ImmutableMap.of(Operator.PATH,ImmutableMap.of(Operator.STRLST,Lists.newArrayList("FilePath")))),
//                                                ImmutableMap.of(Operator.STR,"String")
//                        )).build();

        //Class<?>[] ClassList =  Operator
        // System.out.println(Arrays.toString(ClassList));
        Reflections reflections = new Reflections("org.configRule");
        Set<Class<? extends NodeFactory>> classes = reflections.getSubTypesOf(NodeFactory.class);

        Iterator iterator = classes.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next().toString();
            String[] currencies = name.split(" ");
            Class.forName(currencies[1]);
        }

        loadSampleRules();
        demo();

//        Collection list = new ArrayList();
//        list.add("Mentor");
//        JSONPObject jsonpObject1 = new JSONPObject("STR","Hemanshu");
//        JSONPObject jsonpObject2 = new JSONPObject("SLIST",list);
//        JSONPObject jsonpObject3 = new JSONPObject("Path",jsonpObject2);
//        Collection list2 = new ArrayList();
//        list2.add(jsonpObject3);
//        list2.add(jsonpObject1);
//        JSONPObject jsonpObject = new JSONPObject("Eq",list2);
//        AndOperator andOperator = new AndOperator();
//        EqualsOperator equalsOperator = new EqualsOperator();
//        PathOperator pathOperator = new PathOperator();
//        StringOperator stringOperator = new StringOperator();
//        CollectionStringOperator collectionStringOperator = new CollectionStringOperator();

        String json = "{\"EQ\": [" +
                " { \"PTH\" : {\"STRLIST\" : [\"Mentor\"] } }," +
                " { \"STR\" : \"Hemanshu\" } " +
                "]" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = (Map<String, Object>) mapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
//
        String key = map.keySet().iterator().next();
        Map<String, Object> sT = new HashMap<>();
        Node<Boolean> rule = null;
        try {
            rule = NodeManager.create(key, map.get(key), sT);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(rule.apply(getDoc()));


//        if(input2.size() == 1) {
//            Map<String,Object> map = new HashMap<>();
//            Operator key = input2.keySet().iterator().next();
//            Node<Boolean> rule = createNode(key,input2.get(key),map);
//            System.out.println(rule.apply(getDoc()));
//        }
//        else {
//            System.out.println("Invalid rule");
//        }

//        if(input2.size() == 1) {
//            Map<String,Node> map = new HashMap<>();
//            Operator key = input2.keySet().iterator().next();
//            Node<Boolean> rule = createNode(key,input2.get(key),map);
//            System.out.println(rule.apply(getDoc()));
//        }
//        else {
//            System.out.println("Invalid rule");
//        }
    }


    private static Map<String, Object> getDoc() {
        Map<String, Object> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add("Address");
        list.add("City");
        map.put("Town", list);
        map.put("Mentor", "Hemanshu");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("State", "Gujarat");
        map2.put("City", "Ahmedabad");
        map.put("Address", map2);

        List<String> path = new ArrayList<>();
        path.add("/Users");
        path.add("/hiren.va");
        path.add("/ok.txt");
        map.put("FilePath", path);
        return map;

    }

}
