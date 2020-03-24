package org.example;

import com.google.inject.servlet.ServletModule;
import org.example.Servlet.BotControllerServlet;
import org.example.Servlet.ConfigServlet;
import org.example.services.RuleManagerServlet;


public class AppServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/config").with(ConfigServlet.class);
        serve("/bot/*").with(BotControllerServlet.class);
    }
}
