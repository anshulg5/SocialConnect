package org.example;

import org.telegram.telegrambots.meta.api.objects.Message;

public class Mediator_app implements Sender {

    Receiver_app recv_app;
    Sender_app send_app;


    @Override
    public void send(Message msg) {
        send_app.send(msg);
    }

    Mediator_app(){
        recv_app = new Receiver_app(this);
        send_app = new Sender_app();
    }

    public static void main(String[] args){
        new Mediator_app();
    }

}
