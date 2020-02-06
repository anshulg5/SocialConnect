package org.example.model;

public class AppMessage {

    private String provider;
    private String channelId;
    private String channelName;
    private String sentBy;
    private String text;


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AppMessage{provider='" + provider + '\'' + ", channelId='" + channelId + '\'' + ", channelName='" + channelName + '\'' + ", sentBy='" + sentBy + '\'' + ", text='" + text + '\'' + '}';
    }
}


