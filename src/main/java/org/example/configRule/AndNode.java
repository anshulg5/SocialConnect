package org.example.configRule;

import javax.xml.stream.events.EndDocument;
import java.util.*;

public class AndNode implements Node<Boolean> {

    Collection< Node<Boolean> > nodeCollection;

    public AndNode(Collection<Node<Boolean>> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public AndNode(List<Map<Operator,Object>>  ruleMap) {
        nodeCollection = new ArrayList<>();
        Iterator<Map<Operator, Object>> iterator = ruleMap.iterator();
        while(iterator.hasNext()){
            Map<Operator,Object> map = iterator.next();
            if(map.size() == 1) {
                Map.Entry<Operator,Object> entry = map.entrySet().iterator().next();
                Node<Boolean> node = RuleApp.createNode(entry.getKey(),entry.getValue());
                nodeCollection.add(node);
            }
            else {
                System.out.println("Invalid 'AND' format");
            }
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input){
        Boolean result = true;
        Iterator<Node<Boolean>> iterator = nodeCollection.iterator();
        while(iterator.hasNext()) {
            result &= iterator.next().apply(input);
        }
        return result;
    }

//    public static void main(String[] args) {
//        Map s = new HashMap();
//        s.put("and", "");
//        List<Node<Boolean>> a = getAndNode(s);
//        Node node = new AndNode(a.get(0), a.get(1)) {
//            @Override
//            public String apply(Map<String, ?> bindings) throws Exception {
//                return "value";
//            }
//        };
//    }
//
//    private static List<Node<Boolean>> getAndNode(Map<String, Object> input) {
//        String key = input.keySet().iterator().next();
//        if ("and".equals(key)){
//            List<Boolean> val = input.get(key);
//            Node<Boolean> n1 = new MyBoolean(val.get(0));
//            Node<Boolean> n2 = new MyBoolean(val);*/
//            new AndNode(n1,n2);
//        }
//    }
}
