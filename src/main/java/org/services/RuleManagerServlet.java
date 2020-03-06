package org.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.config.RuleApp;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

@Singleton
public class RuleManagerServlet extends HttpServlet {
    RuleApp ruleApp;

    @Inject
    RuleManagerServlet(RuleApp ruleApp){
        this.ruleApp = ruleApp;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        System.out.println(path);
        System.out.println(req.getParameter("ruleName"));
        System.out.println(req.getParameter("rule"));
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
            default:
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        switch (path){
            case "/fetch":
                fetchRule(req,resp);
                break;
            default:
        }
    }

    private void addRule(HttpServletRequest req, HttpServletResponse resp){
        String ID = req.getParameter("id");
        JSONObject rule=null;
        try {
            String ruleString = req.getParameter("rule");
            if(ID==null || ruleString==null)
                throw new NullPointerException();
            rule = new JSONObject(ruleString);
        } catch (JSONException | NullPointerException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Boolean success=false;
        try {
            success = ruleApp.addRule(ID, rule);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setSuccessMsg(resp,success,"added","duplicate");
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
        JSONObject rule=null;
        try {
            String ruleString = req.getParameter("rule");
            if(ID==null || ruleString==null)
                throw new NullPointerException();
            rule = new JSONObject(ruleString);
        } catch (JSONException | NullPointerException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Boolean success=false;
        try {
            success = ruleApp.updateRule(ID, rule);
            System.out.println(success);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
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
