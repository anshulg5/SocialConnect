package org.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.db.dao.ConnectionDetailDao;

import org.example.model.AppMessage;
import org.example.model.ConnectionDetail;
import org.example.rule.RuleApp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.*;


@Singleton
public class MediatorApp {

    private RuleApp ruleApp;
    private SenderApp MessageSender;
    private ConnectionDetailDao dao = null;
    private Map<String, String> channelDetails;
    private AppMessage currAppmsg = null;
    private Node<Boolean> rule = null;

    public AppMessage getCurrAppmsg() {
        return currAppmsg;
    }

    public void setCurrAppmsg(AppMessage currAppmsg) {
        this.currAppmsg = currAppmsg;
    }

    @Inject
    public MediatorApp(RuleApp ruleApp, SenderApp MessageSender) { //add dependency ConnectionDetailDao dao
        this.ruleApp = ruleApp;
        this.dao = dao;
        this.MessageSender = MessageSender;
        channelDetails = new ConcurrentHashMap();

//        List<ConnectionDetail> data = dao.getAllConnections();
//        Iterator<ConnectionDetail> iterator = data.iterator();
//
//
//        while (iterator.hasNext()) {
//            ConnectionDetail detail = iterator.next();
//            channelDetails.put(detail.getSourceID(), detail.getTargetID());
//        }


//        for (Map.Entry<String,String> entry : channelDetails.entrySet())
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
//        AppMessage msg = new AppMessage();
//        setCurrAppmsg(msg);
    }


    public void addConfig(String source, String target) {
        if (source == null || target == null) return;
        channelDetails.put(source, target);
        for (Map.Entry<String, String> entry : channelDetails.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        dao.saveDetails(new ConnectionDetail(source, target));
    }

    public boolean sendMessage(AppMessage msg, String template) {

        // which rule to apply?

        setCurrAppmsg(msg);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("msg", msg.getText());
        if (ruleApp.validateByID("first",objectMap)) {  // check here
            System.out.println(msg + "passed Rule");
        } else {
            System.out.println(msg + "failed Rule");
        }
        return true;
        //   return MessageSender.send(msg.getChannelId(),textmsg);
    }

    private String processMessage(AppMessage msg, String template) {
        //Here convert text from given params.
        if (template == null)
            return msg.toString();
        //regex
        template = template.replaceAll("\\$\\(json\\.provider\\)", msg.getProvider());
        template = template.replaceAll("\\$\\(json\\.channelId\\)", msg.getChannelId());
        template = template.replaceAll("\\$\\(json\\.channelName\\)", msg.getChannelName());
        template = template.replaceAll("\\$\\(json\\.sentby\\)", msg.getSentBy());
        template = template.replaceAll("\\$\\(json\\.text\\)", msg.getText());
        return template;
    }


}