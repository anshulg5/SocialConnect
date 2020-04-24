package org.example.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jetty.http.HttpStatus;
import org.example.MediatorApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class ConfigServlet extends HttpServlet {

    @Inject
    private MediatorApp app;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK_200);
        String source = req.getParameter("source");
        String target = req.getParameter("target");
        app.addConfig(source, target);
    }
}

