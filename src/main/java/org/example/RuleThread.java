package org.example;

import org.example.configRule.Node;

import java.util.Map;


public class RuleThread implements Runnable {
    private Node<Boolean> node;

    private Map<String,Object> input;


    public RuleThread(Node<Boolean> node, Map<String, Object> input) {
        this.node = node;
        this.input = input;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public Node<Boolean> getNode() {
        return node;
    }

    public void setNode(Node<Boolean> node) {
        this.node = node;
    }

    @Override
    public void run() {
        System.out.println(getNode().apply(getInput()));
    }
}
