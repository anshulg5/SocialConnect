package org.example.modules;

import com.google.inject.servlet.ServletModule;
import org.example.servlet.BotControllerServlet;
import org.example.servlet.ConfigServlet;
import org.example.servlet.RuleManagerServlet;


public class AppServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/config").with(ConfigServlet.class);
        serve("/bot/*").with(BotControllerServlet.class);
    }
}
