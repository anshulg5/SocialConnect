package com.flock.frule.modules;

import com.flock.frule.app.RuleService;
import com.flock.frule.app.RuleServiceImpl;
import com.flock.frule.main.NodeFactoriesLoader;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NodeFactoriesLoader.class).asEagerSingleton();
        bind(RuleService.class).to(RuleServiceImpl.class);
        install(new PropertiesModule());
        install(new DatabaseModule());
        install(new AppServletModule());
    }
}
