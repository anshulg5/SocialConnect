package org.example;

import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.servlet.ServletModule;
import org.example.Servlet.WelcomeServlet;

public class AppServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(WelcomeServlet.class);
        serve("/*").with(WelcomeServlet.class);
    }
}
