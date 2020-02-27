package org.example;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.inject.Singleton;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Singleton
public class SenderApp {

    HttpClient client;
    String URLold="https://api.flock.com/hooks/sendMessage/88e00f4f-eb77-407f-9f6d-a79565178efe";
    String URL = "https://api.flock.com/hooks/sendMessage/6cb0be5e-0f03-4c5b-a440-a7a6c5c887a3";

    SenderApp(){
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
        json.put("group",msg.getChat().getTitle());
        request.content(new StringContentProvider(json.toString()));

//        System.out.println(msgm
        try {
            request.send();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
