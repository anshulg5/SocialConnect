package com.flock.frule.servlet;

import com.flock.frule.app.RuleService;
import com.flock.frule.model.Response;
import com.flock.frule.model.Rule;
import com.flock.frule.model.jsondata.JsonType;
import com.flock.frule.util.Serializer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//TODO: remove code duplication
@Singleton
public class RuleManagerServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    RuleService ruleService;

    @Inject
    RuleManagerServlet(RuleService ruleService){
        this.ruleService = ruleService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//        resp.addHeader("Access-Control-Max-Age", "1728000");
        String path = req.getPathInfo();
        log.info(path);
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
            case "/apply":
                applyInput(req,resp);
            default:
        }
        log.info("status"+resp.getStatus());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }

    private void addRule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        String jsonString = req.getParameter("rule");
        Rule rule;
        if(id == null || jsonString == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Parameter missing");
            return;
        }
        try {
            JsonType json = Serializer.fromJson(jsonString);
            rule = new Rule(id,json);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Invalid Arguement");
            return;
        }
        Response response = ruleService.addRule(id,rule);
        if(!response.getStatus())
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.write(response.getMessage());
    }

    private void updateRule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        String jsonString = req.getParameter("rule");
        Rule rule;
        if(id == null || jsonString == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Parameter missing");
            return;
        }
        try {
            JsonType json = Serializer.fromJson(jsonString);
            rule = new Rule(id,json);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Invalid Arguement");
            return;
        }
        Response response = ruleService.updateRule(id,rule);
        if(!response.getStatus())
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.write(response.getMessage());
    }

    private void deleteRule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        if(id == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Parameter missing");
            return;
        }
        Response response = ruleService.deleteRule(id);
        if(!response.getStatus())
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.write(response.getMessage());
    }

    private void fetchRule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        Map<String, Rule> rulesMap = ruleService.fetchRules();
        Map<String, String> rules = new HashMap<>();
        rulesMap.forEach((k,v) -> rules.put(k,v.getRuleString()));
        JSONObject json = new JSONObject(rules);
        resp.setContentType("application/json");
        out.write(json.toString());

    }

    // takes input from the POST Request Body
    private void applyInput(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String inputString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info(inputString);
//        if(inputString == null){
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.write("Parameter missing");
//            return;
//        }
        JsonType input;
        try{
            input = Serializer.fromJson(inputString);
        } catch(Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Invalid Arguement");
            return;
        }
        ruleService.applyInput(input);
        out.write("Applied rules on input");
        log.info("Applied rules on input");
    }
}
