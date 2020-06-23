package com.flock.frule.modules;

import com.flock.frule.servlet.BotControllerServlet;
import com.flock.frule.servlet.ConfigServlet;
import com.flock.frule.servlet.RuleManagerServlet;
import com.flock.frule.servlet.TargetManagerServlet;
import com.google.inject.servlet.ServletModule;


public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/rule/*").with(RuleManagerServlet.class);
        serve("/target/*").with(TargetManagerServlet.class);
        serve("/config").with(ConfigServlet.class);
        serve("/bot/*").with(BotControllerServlet.class);
    }
}
