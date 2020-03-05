package org.example.Servlet;

import com.google.inject.Singleton;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class WelcomeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "   <head>\n" +
                "      <title>HTML textarea Tag</title>\n" +
                "   </head>\n" +
                "\n" +
                "   <body>\n" +
                "      <form action = \"/flock\" method = \"post\">\n" +
                "         What improvements you want in College?\n" +
                "         <br>\n" +
                "         <textarea rows = \"5\" cols = \"60\" name = \"description\">\n" +
                "            Enter details here...\n" +
                "         </textarea><br>\n" +
                "         <input type = \"submit\" value = \"submit\" />\n" +
                "      </form>\n" +
                "        <button onclick=\"location.href='/flock'\">Click me</button> " +
                "   </body>\n" +
                "</html>");

    }
}
