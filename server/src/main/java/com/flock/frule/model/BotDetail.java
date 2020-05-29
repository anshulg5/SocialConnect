package com.flock.frule.model;

public class BotDetail {
    String botUserName;
    String botToken;
    String msgText;

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getMsgText() { return msgText; }

    public void setMsgText(String botMsgText) { this.msgText = msgText; }
}
