package org.example.modules;

import com.google.inject.AbstractModule;
import org.example.main.NodeFactoriesLoader;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NodeFactoriesLoader.class).asEagerSingleton();
//        bind(RuleApp.class).asEagerSingleton();
        install(new PropertiesModule());
        install(new DatabaseModule());
        install(new AppServletModule());
    }
}
