package org.example;

import org.json.JSONObject;

public class Message {

    private String platform;
    private String channelName;
    private String userName;
    private String msgBody;

    Message(String channelName, String userName, String msgBody){
        this.channelName = channelName;
        this.userName = userName;
        this.msgBody = msgBody;
        this.platform = "Undefined";
    }

    Message(String platform, String channelName, String userName, String msgBody){
        this(channelName, userName, msgBody);
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getUserName() {
        return userName;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public JSONObject getJSON(){
        JSONObject json = new JSONObject();
        json.put("platform",platform);
        json.put("channel",channelName);
        json.put("user",userName);
        json.put("msg",msgBody);
        return json;
    }
}
