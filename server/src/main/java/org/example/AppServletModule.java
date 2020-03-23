package org.example;

import com.google.inject.servlet.ServletModule;
import org.example.Servlet.BotControllerServlet;
import org.example.Servlet.ConfigServlet;
import org.example.Servlet.FWHtestingServlet;
import org.example.Servlet.WelcomeServlet;
import org.example.services.RuleManagerServlet;


public class AppServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/config").with(ConfigServlet.class);
        serve("/").with(WelcomeServlet.class);
        serve("/bot/*").with(BotControllerServlet.class);
        serve("/flock").with(FWHtestingServlet.class);
    }
}
