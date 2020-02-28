package org.example.configRule;

import org.example.configRule.primitiveType.StringNode;

import java.util.*;

public class PathNode<T> implements Node<T> {

    Node<Collection<String>> collectionNode;

    public PathNode() {
    }

    public PathNode(Map<Operator,Object> map,Map<String,Object> symbolTable){
        if(map.size()==1) {
            Iterator<Operator> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Operator key = iterator.next();
                collectionNode = key.getInstance(map.get(key),symbolTable);;
            }
        }
    }

    @Override
    public T apply(Map<String,?> input){
        Collection<String> collection = collectionNode.apply(input);
        Iterator<String> iterator = collection.iterator();
        Object object = input;
        while(iterator.hasNext()) {
            object = ((Map) object).get(iterator.next());
        }
        return (T)object;
//            if(object.getClass().equals(input.getClass()))
//                object = ((Map) object).get(value);
//            else
//            {
//                break;
//             //   System.out.println("Error");
//              //  throw new Exception("Input invaild");
//            }
//        if(object instanceof String)
//            return (T)object;
//        else {
//            System.out.println("ERROR");
//            // Throw it
//        }
//        return null;
//        T o = theclass.newInstance();
//        if((object instanceof Collection) && ((Collection) object).stream().noneMatch((o1 -> !(o1 instanceof String)))) {
//            Method method = o.getClass().getMethod("add",Object.class);
//            Collection<String> object1 = (Collection<String>) object;
//            for(String value : object1) {
//                method.invoke(o,new MyString(value));
//            }
//        }
//        else if(object instanceof String) {
//                Method method = o.getClass().getMethod("setValue",String.class);
//                method.invoke(o,object);
//            }
//        else {̄
//            System.out.println("error");
//        }
//        return o;
//    return null;
    }

//    public static void main(String[] args) throws Exception {
//        MyString myString = new MyString("name");
//        Collection<MyString> myStrings = new ArrayList<>();
//        myStrings.add(myString);
//
//        Map<String, Object> map = getDoc();
//
//        PathNode<Collection<MyString>> pathNode1 = new PathNode(myStrings,java.util.ArrayList.class);
//        PathNode<MyString> pathNode = new PathNode(pathNode1.apply(map),MyString.class);
//        System.out.println(pathNode.apply(map).apply(map));
//    }
//
//    private static Map<String, Object> getDoc() {
//        Map<String,Object> map = new HashMap<>();
//        Map<String,Object> map2 = new HashMap<>();
//        List<String> list = new ArrayList<>();
//        list.add("p1");
//        list.add("p2");
//        map.put("name",list);
//        map2.put("p2","hiren");
//        map.put("p1",map2);
//        return map;
//    }
}
