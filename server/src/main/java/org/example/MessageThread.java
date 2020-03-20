package org.example;

import org.example.model.AppMessage;

public class MessageThread implements Runnable {

    private MediatorApp app;
    private AppMessage msg;
    private String Template;


    @Override
    public void run() {
       // app.sendMessage(msg)
    }
}
