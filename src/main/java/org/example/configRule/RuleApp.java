package org.example.configRule;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.example.configRule.primitiveType.BooleanNode;
import org.example.configRule.primitiveType.IntegerNode;
import org.example.configRule.primitiveType.StringNode;
import java.util.*;

public class RuleApp {


    public static Node createNode(Operator key, Object value) {
        switch (key)
        {
            case GT:
                return new GreaterThanNode((Map<Operator,Object>)value);
            case LT:
                return new LessThanNode((Map<Operator,Object>)value);
            case EQ:
                return new EqualsNode((Map<Operator, Object>) value);
            case AND:
                return new AndNode((List<Map<Operator,Object>>) value);
            case OR:
                return new OrNode((List<Map<Operator,Object>>) value);
            case NOT:
                return new NotNode((Map<Operator,Object>)value);
            case INT:
                return new IntegerNode((Integer) value); // search for what to do if integer is there as string?
            case STR:
                return new StringNode((String)value);
            case BOOL:
                return new BooleanNode((Boolean)value);
            case STRLST:
                return new CollectionStringNode((Collection<String>) value);
            case PATH:
                return new PathNode((Map<Operator,Object>) value);
            default:
                return null;
        }
    }

    public static void main(String[] args) {

        Map<Operator, Object> input = ImmutableMap.<Operator, Object>builder()
                .put(Operator.AND, ImmutableList.of(
                        ImmutableMap.builder()
                                .put(Operator.EQ, ImmutableMap.of(
                                        Operator.PATH, ImmutableMap.of(Operator.STRLST, Lists.newArrayList("Address","City")),
                                        Operator.STR, "Ahmedabad"
                                )).build(),
                        ImmutableMap.builder()
                                .put(Operator.EQ, ImmutableMap.of(
                                        Operator.PATH, ImmutableMap.of(Operator.STRLST, Lists.newArrayList("Address","State")),
                                        Operator.STR, "Gujarat"
                                )).build()
                ))
                .build();

        Map<Operator,Object> input2 = ImmutableMap.<Operator,Object>builder()
                .put(Operator.EQ,ImmutableMap.of(
                        Operator.PATH,ImmutableMap.of(
                                Operator.PATH,ImmutableMap.of(
                                        Operator.STRLST,Lists.newArrayList("Name"))),
                        Operator.STR,"Gujarat"
                )).build();

        if(input.size() == 1) {
            Operator key = input.keySet().iterator().next();
            Node<Boolean> rule = createNode(key,input.get(key));
            System.out.println(rule.apply(getDoc()));
        }
        else {
            System.out.println("Invalid rule");
        }
    }

    private static Collection<StringNode> getStringNodes(Collection<String> stringCollection) {
        Collection<StringNode> collection = new ArrayList<>();
        for(String string : stringCollection) {
            StringNode stringNode = new StringNode(string);
            collection.add(stringNode);
        }
        return collection;
    }

    private static Map<String, Object> getDoc() {
    Map<String,Object> map = new HashMap<>();

    List<String> list = new ArrayList<>();
    list.add("Address");
    list.add("City");
    map.put("Town",list);
    Map<String,Object> map2 = new HashMap<>();
    map2.put("State","Gujarat");
    map2.put("City","Ahmedabad");
    map.put("Address",map2);
    return map;

    }
}
