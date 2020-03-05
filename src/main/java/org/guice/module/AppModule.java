package org.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.Bot.Bot;
import org.Bot.BotFactory;
import org.sender.telegram.TelegramBot;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(RuleManagerServlet.class).annotatedWith(Names.named("RuleManagerServlet")).to(AddRuServlet.class);
        install(new FactoryModuleBuilder().implement(Bot.class, TelegramBot.class).build(BotFactory.class));
    }
}
