package org.services;

import org.rule.RuleApp;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Singleton
public class RuleListServlet extends HttpServlet {
    @Inject
    RuleApp ruleApp;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>AddRules</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/gh/kognise/water.css@latest/dist/dark.min.css\">" +
                "</head>\n");
        Map<String,String> ruleList = ruleApp.fetchRules();
        String htmlBody = "";
        htmlBody += "<body>";
        htmlBody += "<h1>List of Rules</h1>";
        htmlBody += "<table>" ;
        Iterator<String> iterator = ruleList.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            htmlBody += "<tr>";
            htmlBody += "<td>" + key + "</td>";
            htmlBody += "<td>" + "<button onclick=\"location.href='/rule/delete?id=" + key + "'\">Delete</button>\n" + "</td>";
            htmlBody += "<td>" + "<button onclick=\"location.href='/rule/update?id=" + key + "'\">Update</button>\n" + "</td>";
            htmlBody += "</tr>";
        }
        htmlBody += "</table>";
        htmlBody += "</body>";
        resp.getWriter().println(htmlBody);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
