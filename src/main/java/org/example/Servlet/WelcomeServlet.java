package org.example.Servlet;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.eclipse.jetty.http.HttpStatus;
import org.example.AppModule;
import org.example.MediatorApp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class WelcomeServlet extends HttpServlet {

    private MediatorApp app = null;
    @Inject private Injector inject = null;

    public WelcomeServlet(){
        this.app = this.inject.getInstance(MediatorApp.class);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req,resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK_200);
        String source = req.getParameter("source");
        String target = req.getParameter("target");
        app.addConfig(source,target);
    }
}
