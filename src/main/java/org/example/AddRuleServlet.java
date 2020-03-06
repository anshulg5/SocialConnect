package org.example;

import javax.inject.Singleton;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AddRuleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>AddRules</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <!-- header -->\n" +
                "    <div>\n" +
                "        <h1>Add Rule<//h1>\n" +
                "    </div>\n" +
                "\n" +  "<form action=\"/rulemanager/add\" id=\"ruleForm\" method=\"post\" >" +
                    "Name : <input type=\"text\" name=\"ruleName\">" +
                    "<input type=\"submit\">" +
                "</form>" + "<br>" +
                "<textarea rows=\"4\" cols=\"50\" name=\"rule\" form=\"ruleForm\">" +
                "Enter Rule Here...</textarea>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
