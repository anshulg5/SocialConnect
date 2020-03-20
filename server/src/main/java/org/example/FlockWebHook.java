package org.example;

import com.google.inject.Inject;
import org.example.model.AppMessage;

public class FlockWebHook implements ReceiverApp {
    private String userName = "FlockBot";
    private String token = "ad1a2bfc-5ec0-43b2-a71d-06fb8ecb3429";
    private String msgText  = "This $(json.provider) message is sent by $(json.sentby) from group $(json.channelName) : $(json.text)";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    @Inject private MediatorApp app;


    @Override
    public Boolean sendMessage(AppMessage appMessage) {
        return app.sendMessage(appMessage,msgText);
    }
}
