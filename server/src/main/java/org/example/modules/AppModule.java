package org.example.modules;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new PropertiesModule());
        install(new DatabaseModule());
        install(new AppServletModule());

    }
}
