package org.example;

public class Mediator_app implements Sender {

    Receiver_app recv_app;
    Sender_app send_app;


    @Override
    public void send(String msg) {
        send_app.send(msg);
    }

    Mediator_app(){
        Receiver_app recv_app = new Receiver_app(this);
        Sender_app send_app = new Sender_app();
    }

    public static void main(){
        new Mediator_app();
    }

}
