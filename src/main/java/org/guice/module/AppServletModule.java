package org.guice.module;

import com.google.inject.servlet.ServletModule;
import org.services.AddBotServlet;
import org.services.RuleManagerServlet;
import org.services.WelcomeServlet;

public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/addbot/").with(AddBotServlet.class);
        serve("/").with(WelcomeServlet.class);
    }
}
