package org.guice.module;

import com.google.inject.servlet.ServletModule;
import org.services.AddBotServlet;
import org.services.AddConfigServlet;

public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/addconfig").with(AddConfigServlet.class);
        serve("/addbot").with(AddBotServlet.class);
    }
}
