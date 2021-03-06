package com.flock.frule.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flock.frule.dao.ConnectionDetailDao;
import com.flock.frule.model.AppMessage;
import com.flock.frule.model.ConnectionDetail;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Singleton
public class MediatorApp {

    private RuleService ruleService;
    private SenderApp MessageSender;
    private ConnectionDetailDao dao;
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
    public MediatorApp(RuleService ruleService, SenderApp MessageSender, ConnectionDetailDao dao) {
        this.ruleService = ruleService;
        this.dao = dao;
        this.MessageSender = MessageSender;
        channelDetails = new ConcurrentHashMap<>();
    }


    public void addConfig(String source, String target) {
        if (source == null || target == null) return;
        channelDetails.put(source, target);
        for (Map.Entry<String, String> entry : channelDetails.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        dao.saveDetails(new ConnectionDetail(source, target));
    }
    
    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public boolean sendMessage(AppMessage msg, String template){

        // which rule to apply?

        setCurrAppmsg(msg);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("firstname",msg.getSentBy());
        objectMap.put("group",msg.getChannelName());
        objectMap.put("provider",msg.getProvider());
        if(isJSONValid(msg.getText())) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                objectMap.put("msg", mapper.readValue(msg.getText(), new TypeReference<Map<String, Object>>() {}));
            }
            catch (Exception e){
                return false;
            }
        }
        else {
            objectMap.put("msg",msg.getText());
        }
        System.out.println(objectMap);

        // TODO: replace empty jsonObject
        JsonObject jsonObject = new JsonObject(); //

        boolean success = ruleService.validateByID("third", jsonObject).getStatus();
        if (success) {  // check here
            System.out.println(msg + "passed Rule");
            return MessageSender.send(msg.getChannelId(),msg.getText());
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