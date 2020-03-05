package org.guice.module;

import com.google.inject.servlet.ServletModule;
import org.services.AddBotServlet;
import org.services.AddConfigServlet;
import org.services.RuleManagerServlet;

public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/rulemanager/*").with(RuleManagerServlet.class);
        serve("/addbot/").with(AddBotServlet.class);
    }
}
