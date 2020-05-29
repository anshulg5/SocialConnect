package com.flock.frule.modules;

import com.flock.frule.main.NodeFactoriesLoader;
import com.google.inject.AbstractModule;

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
