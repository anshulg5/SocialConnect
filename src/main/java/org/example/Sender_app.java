package org.example;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Sender_app {

    HttpClient client;
    String URL="https://api.flock.com/hooks/sendMessage/88e00f4f-eb77-407f-9f6d-a79565178efe";

    Sender_app(){
        SslContextFactory.Client sslContextFactory = new SslContextFactory.Client();
        client = new HttpClient(sslContextFactory);
        client.setFollowRedirects(true);
        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void send(Message msg){
        Request request = client.POST(URL);
//            request = request.content(request.getContent(),"application/json");
        request.header(HttpHeader.CONTENT_TYPE, "application/json");

        JSONObject json = new JSONObject();
        json.put("user",msg.getFrom().getFirstName());
        json.put("message",msg.getText());
        request.content(new StringContentProvider(json.toString()));

        System.out.println(msg);
        System.out.println(request);
        try {
            request.send();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
