package org.example;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.ImmutableMap;
import org.json.JSONObject;

import java.util.*;

public class RuleApp {


    private static MediatorApp app = null;

    public static MediatorApp getApp() {
        return app;
    }

    public static void setApp(MediatorApp app) {
        RuleApp.app = app;
    }

//    public static Node createNode(Operator key, Object value, Map<String,Object> symbolTable) {
//        switch (key)
//        {
//            case Operator.GT:
//                return new GreaterThanNode((List<Map<Operator, Object>>) value,symbolTable);
//            case Operator.LT:
//                return new LessThanNode((List<Map<Operator, Object>>) value,symbolTable);
//            case Operator.EQ:
//                return new EqualsNode((List<Map<Operator, Object>>) value,symbolTable);
//            case Operator.AND:
//                return new AndNode((List<Map<Operator,Object>>) value,symbolTable);
//            case Operator.OR:
//                return new OrNode((List<Map<Operator,Object>>) value,symbolTable);
//            case Operator.NOT:
//                return new NotNode((Map<Operator,Object>)value,symbolTable);
//            case Operator.INT:
//                return new IntegerNode((Integer) value); // search for what to do if integer is there as string?
//            case Operator.STR:
//                return new StringNode((String)value);
//            case Operator.BOOL:
//                return new BooleanNode((Boolean)value);
//            case Operator.STRLST:
//                return new CollectionStringNode((Collection<String>) value);
//            case Operator.PATH:
//                return new PathNode((Map<Operator,Object>) value,symbolTable);
//            case Operator.LET:
//                return new LetNode((Map<Operator,Object>)value,symbolTable);
//            case Operator.DEF:
//                return new DefinationNode((List<Map<Operator, Object>>) value,symbolTable);
//            case Operator.VAR:
//                return new VarNode((String) value,symbolTable);
//            case Operator.FREAD:
//                return new FileReaderNode((Map<Operator, Object>) value,symbolTable);
//            case Operator.GETMSG:
//                return new GetNextMsg(app);
//            default:
//                return null;
//        }
//    }

    public static void main(String[] args) {

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

        Collection list = new ArrayList();
        list.add("Mentor");
        JSONPObject jsonpObject1 = new JSONPObject("STR","Hemanshu");
        JSONPObject jsonpObject2 = new JSONPObject("SLIST",list);
        JSONPObject jsonpObject3 = new JSONPObject("Path",jsonpObject2);
        Collection list2 = new ArrayList();
        list2.add(jsonpObject3);
        list2.add(jsonpObject1);
        JSONPObject jsonpObject = new JSONPObject("Eq",list2);

        Map<Operator,Object> map = OperatorManager.parse(jsonpObject);

        Operator key = map.keySet().iterator().next();
        Map<String,Object> sT = new HashMap<>();
        System.out.println(key);
        Node<Boolean> rule = key.getInstance(map.get(key),sT);

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
    Map<String,Object> map = new HashMap<>();

    List<String> list = new ArrayList<>();
    list.add("Address");
    list.add("City");
    map.put("Town",list);
    map.put("Mentor","Hemanshu");
    Map<String,Object> map2 = new HashMap<>();
    map2.put("State","Gujarat");
    map2.put("City","Ahmedabad");
    map.put("Address",map2);

    List<String> path = new ArrayList<>();
    path.add("/Users");
    path.add("/hiren.va");
    path.add("/ok.txt");
    map.put("FilePath",path);
    return map;

    }
}
