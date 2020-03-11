package org.services;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        String htmlHeader = "";
        htmlHeader += "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>SocialConnect</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/gh/kognise/water.css@latest/dist/dark.min.css\">"+
                "</head>\n";
        resp.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                 htmlHeader +
                "<body>\n" +
                "    <!-- header -->\n" +
                "    <div>\n" +
                "        <h1>Welcome<//h1>\n" +
                "    </div>\n" +
                "\n" +
           //     " <script> document.write(\"Hello JavaScript by JavaScript\"); </script>" +
                "    <div>       <!-- content -->\n" +
                "        <div>    <!-- button holder -->\n" +
                "            <button onclick=\"location.href='/rule/list'\">List Rules</button>\n" +
                "            <button onclick=\"location.href='/rule/add'\">Add Rule</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
