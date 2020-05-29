package com.flock.frule.modules;

import com.flock.frule.servlet.BotControllerServlet;
import com.flock.frule.servlet.ConfigServlet;
import com.flock.frule.servlet.RuleManagerServlet;
import com.google.inject.servlet.ServletModule;


public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/config").with(ConfigServlet.class);
        serve("/bot/*").with(BotControllerServlet.class);
    }
}
