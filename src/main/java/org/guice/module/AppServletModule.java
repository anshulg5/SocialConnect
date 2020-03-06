package org.guice.module;

import com.google.inject.servlet.ServletModule;
import org.example.AddRuleServlet;
import org.example.WelcomeServlet;
import org.services.AddBotServlet;
import org.services.AddConfigServlet;
import org.services.RuleManagerServlet;

public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/addbot/").with(AddBotServlet.class);
        serve("/").with(WelcomeServlet.class);
        serve("/rule/add").with(AddRuleServlet.class);
    }
}
