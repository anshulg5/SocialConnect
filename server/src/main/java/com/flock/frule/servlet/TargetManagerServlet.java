package com.flock.frule.servlet;

import com.flock.frule.app.RuleService;
import com.flock.frule.helpers.TargetBuilder;
import com.flock.frule.model.Response;
import com.flock.frule.model.Target;
import com.flock.frule.model.jsondata.JsonType;
import com.flock.frule.util.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//TODO: remove code duplication
@Singleton
public class TargetManagerServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RuleService ruleService;

    @Inject
    public TargetManagerServlet(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String path = req.getPathInfo();
        log.info(path);
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        switch(path){
            case "/add":
                addTarget(req, resp);
                break;
            case "/remove":
                removeTarget(req,resp);
                break;
            default:
        }
        log.info("status"+resp.getStatus());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }

    private void addTarget(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String ruleId = req.getParameter("ruleId");
        String targetJsonString = req.getParameter("target");
        Target target;
        if(ruleId == null || targetJsonString == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Parameter missing");
            return;
        }
        try {
            JsonType json = Serializer.fromJson(targetJsonString);
            target = TargetBuilder.fromJson(json.asObject());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Invalid Arguement");
            return;
        }
        Response response = ruleService.addTarget(ruleId,target);
        if(!response.getStatus())
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.write(response.getMessage());
    }

    private void removeTarget(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String ruleId = req.getParameter("ruleId");
        String targetId = req.getParameter("targetId");
        if(ruleId == null || targetId == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("Parameter missing");
            return;
        }
        Response response = ruleService.removeTarget(ruleId,targetId);
        if(!response.getStatus())
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        out.write(response.getMessage());
    }
}
