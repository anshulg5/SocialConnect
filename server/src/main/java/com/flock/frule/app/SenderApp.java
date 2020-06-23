package com.flock.frule.app;

import com.google.inject.Singleton;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Singleton
public class SenderApp {

    HttpClient client;
    String URL="https://api.flock.com/hooks/sendMessage/88e00f4f-eb77-407f-9f6d-a79565178efe";
    String URL2 = "https://api.flock.com/hooks/sendMessage/6cb0be5e-0f03-4c5b-a440-a7a6c5c887a3";

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

    public boolean send(String ChannelId , String msg){
        Request request = client.POST(URL2);
        request.header(HttpHeader.CONTENT_TYPE, "application/json");
        JSONObject json = new JSONObject();
        json.put("text",msg);
        request.content(new StringContentProvider(json.toString()));
        try {
            request.send();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
