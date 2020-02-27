package org.config;

import org.json.JSONObject;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class RuleConfig {
    List<Node> configurations = new ArrayList<>();

    public void loadSampleConfig(){
        System.out.println("\nLoading Sample Rule configurations");

        JSONObject json = new JSONObject("{\"EQ\":[{\"PTH\":[\"from\",\"firstName\"]},{\"STR\":\"Anshu\"}]}");
        System.out.println(json);

        JSONObject json2 = new JSONObject("{\"AND\":[{\"EQ\":[{\"PTH\":[\"from\",\"firstName\"]},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":[\"text\"]},{\"STR\":\"Hi\"}]}]}]}");
        System.out.println(json2);

        JSONObject json3 = new JSONObject("{\"AND\":[{\"EQ\":[{\"PTH\":[\"from\",\"firstName\"]},{\"STR\":\"Anshu\"}]},{\"BOOL\":true}]}");
        System.out.println(json3);

        try {
            configurations.add(NodeFactory.createNode(json));
            configurations.add(NodeFactory.createNode(json2));
            configurations.add(NodeFactory.createNode(json3));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void demo(){
        JSONObject jsonMsg2 = new JSONObject("{\"name\":\"Anshul\",\"arr\":[\"random_str\",[\"path_to\"]],\"path_to\":[\"name\"]}");

        JSONObject json4 = new JSONObject("{\"EQ\":[{\"PTH\":{\"PTH\":{\"PTH\":[\"arr\",\"1\"]}}},{\"STR\":\"Anshul\"}]}");
        System.out.println(json4);
        Node config4 = null;
        try {
            config4 = NodeFactory.createNode(json4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(config4.apply(jsonMsg2));

        JSONObject json5 = new JSONObject("{\"EQ\":[{\"PTH\":{\"PTH\":[\"path_to\"]}},{\"STR\":\"Anshul\"}]}");
        System.out.println(json5);
        Node config5 = null;
        try {
            config5 = NodeFactory.createNode(json5);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(config5.apply(jsonMsg2));
    }

    public Boolean validateConfig(int index, JSONObject msg){
        System.out.println("Validating the message on Config "+index);
        return (Boolean) configurations.get(index).apply(msg);
    }
}
