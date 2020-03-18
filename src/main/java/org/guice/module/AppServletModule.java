package org.guice.module;

import com.google.inject.servlet.ServletModule;
import org.services.*;

public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/addbot/").with(AddBotServlet.class);
        serve("/").with(WelcomeServlet.class);
        serve("/rule/add").with(AddRuleServlet.class);
        serve("/rule/list").with(RuleListServlet.class);
    }
}
