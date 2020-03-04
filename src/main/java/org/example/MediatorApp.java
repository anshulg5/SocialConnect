package org.example;

import org.config.RuleApp;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MediatorApp {
    RuleApp ruleApp;
    SenderApp senderApp;

    @Inject
    MediatorApp(RuleApp ruleApp, SenderApp senderApp) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ruleApp = ruleApp;
//        ruleApp.loadSampleRules();
        this.senderApp = senderApp;
//        ruleApp.demo();
    }

    public void send(Message msg){
        JSONObject jsonMsg = new JSONObject(msg);
        System.out.println("Message Received:\n"+jsonMsg);
//        System.out.println(ruleApp.validateSampleRules(0,jsonMsg));
//        System.out.println(ruleApp.validateSampleRules(1,jsonMsg));
//        System.out.println(ruleApp.validateSampleRules(2,jsonMsg));
        System.out.println(ruleApp.validateByID("first",jsonMsg));
        senderApp.send(msg);
    }
}
