package org.services;

import org.config.RuleApp;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AddRuleServlet extends HttpServlet {
    RuleApp ruleApp;

    @Inject
    AddRuleServlet(RuleApp ruleApp){
        this.ruleApp = ruleApp;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("id");
        JSONObject rule = new JSONObject(req.getParameter("rule"));
        Boolean success;
        try {
            success = ruleApp.addRule(ID,rule);
            System.out.println(success);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
