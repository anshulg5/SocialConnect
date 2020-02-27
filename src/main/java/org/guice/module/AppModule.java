package org.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.Bot.Bot;
import org.Bot.BotFactory;
import org.sender.telegram.TelegramBot;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(AddConfigServlet.class).annotatedWith(Names.named("AddConfigServlet")).to(AddConfigServlet.class);
        install(new FactoryModuleBuilder().implement(Bot.class, TelegramBot.class).build(BotFactory.class));
    }
}
