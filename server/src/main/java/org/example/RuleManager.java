package org.example;

import com.google.inject.Singleton;
import org.configRule.Node;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class RuleManager {
    private Map<String, Node> ruleList;

    RuleManager(){
        ruleList = new ConcurrentHashMap<>();
    }
    void addRule(String ruleName, JSONObject jsonObject){
        ruleList.put(ruleName,parse(jsonObject));
    }
    private Node parse(JSONObject jsonObject) {
        return null;
    }
}
