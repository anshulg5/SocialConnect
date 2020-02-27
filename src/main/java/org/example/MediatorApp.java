package org.example;

import org.config.RuleConfig;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Mediator {
    RuleConfig ruleConfig;
    SenderApp senderApp;

    @Inject
    Mediator(RuleConfig ruleConfig, SenderApp senderApp) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ruleConfig = ruleConfig;
        ruleConfig.loadSampleConfig();
        this.senderApp = senderApp;
    }

    public void send(Message msg){
        JSONObject jsonMsg = new JSONObject(msg);
        System.out.println("Message Received:\n"+jsonMsg);
        System.out.println(ruleConfig.validateConfig(0,jsonMsg));
        System.out.println(ruleConfig.validateConfig(1,jsonMsg));
        System.out.println(ruleConfig.validateConfig(2,jsonMsg));
        senderApp.send(msg);
    }
}
