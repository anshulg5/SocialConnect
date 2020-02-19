package org.example.Servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.internal.util.$SourceProvider;
import jdk.nashorn.internal.parser.JSONParser;
import org.example.FlockWebHook;
import org.example.model.AppMessage;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Singleton
public class FWHtestingServlet extends HttpServlet {

    @Inject
    FlockWebHook flockWebHook;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        if(req.getParameter("token").equals(flockWebHook.getToken())) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//                String json = "";
//                if(br != null){
//                    json = br.readLine();
//                    System.out.println(json);
//                }
//                JSONObject jsonObject = new JSONObject(json);
//                AppMessage msg = new AppMessage();
//                msg.setProvider("Flock");
//                msg.setSentBy(jsonObject.getString("from"));
//                msg.setChannelName(flockWebHook.getUserName());
//                msg.setText(jsonObject.getString("text"));
//                flockWebHook.sendMessage(msg);
//
//        }
    }
}
