package org.example.model;

public class AppMessage {

    private String ChannelId;
    private String User;
    private String Text;

    public AppMessage() {
    }
    public AppMessage(String channelId, String user, String text) {
        ChannelId = channelId;
        User = user;
        Text = text;
    }
    public void setChannelId(String channelID) {
        ChannelId = channelID;
    }

    public void setUser(String user) {
        User = user;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getChannelId() {
        return ChannelId;
    }

    public String getUser() {
        return User;
    }



    public String getText() {
        return Text;
    }

}


