package com.flock.frule.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flock.frule.app.RuleApp;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Singleton
public class RuleManagerServlet extends HttpServlet {
    RuleApp ruleApp;

    @Inject
    RuleManagerServlet(RuleApp ruleApp){
        this.ruleApp = ruleApp;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//        resp.addHeader("Access-Control-Max-Age", "1728000");
        String path = req.getPathInfo();
        System.out.println(path);
        System.out.println("id: "+req.getParameter("id"));
        System.out.println("rule: "+req.getParameter("rule"));
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        switch(path){
            case "/add":
                addRule(req, resp);
                break;
            case "/delete":
                deleteRule(req,resp);
                break;
            case "/update":
                updateRule(req,resp);
                break;
            case "/fetch":
                fetchRule(req,resp);
                break;
            default:
        }
        System.out.println("status"+resp.getStatus());
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
//        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req,resp);
//        resp.sendRedirect("/");
    }

    private void addRule(HttpServletRequest req, HttpServletResponse resp){
        String ID = req.getParameter("id");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = null;
        try {
            String ruleString = req.getParameter("rule");
            if(ID==null || ruleString==null)
                throw new NullPointerException();
            rule = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        } catch (NullPointerException | IOException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Boolean success=false;
        try {
            success = ruleApp.addRule(ID, rule);
        } catch (IllegalAccessException | JsonProcessingException e) {
            e.printStackTrace();
        }
        setSuccessMsg(resp,success,"added","duplicate");    //used to set Error 403 in case of duplicacy
    }

    private void deleteRule(HttpServletRequest req, HttpServletResponse resp){
        String ID = req.getParameter("id");
        if(ID==null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Boolean success=false;
        success = ruleApp.deleteRule(ID);
        setSuccessMsg(resp,success,"deleted","not present");
    }

    private void updateRule(HttpServletRequest req, HttpServletResponse resp){
        String ID = req.getParameter("id");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = null;
        try {
            String ruleString = req.getParameter("rule");
            if(ID==null || ruleString==null)
                throw new NullPointerException();
            rule = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        } catch (NullPointerException | IOException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Boolean success=false;
        try {
            success = ruleApp.updateRule(ID, rule);
            System.out.println(success);
        } catch (IllegalAccessException | JsonProcessingException e) {
            e.printStackTrace();
        }
        setSuccessMsg(resp,success,"updated","not present");
    }

    private void setSuccessMsg(HttpServletResponse resp, Boolean success, String successMsg, String failureMsg){
        System.out.println("Success: "+success);
        try {
            if(success){
                resp.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out = resp.getWriter();
                out.write(successMsg);
                out.close();
            }
            else {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter out = resp.getWriter();
                out.write(failureMsg);
                out.close();
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void fetchRule(HttpServletRequest req, HttpServletResponse resp){
        Map<String, String> map = ruleApp.fetchRules();
        JSONObject json = new JSONObject(map);
        resp.setContentType("application/json");
        try {
            PrintWriter out = resp.getWriter();
            out.write(json.toString());
            out.close();
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}