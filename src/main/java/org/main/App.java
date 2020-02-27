package org.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.guice.module.AppModule;
import org.guice.module.AppServletModule;

public class App {

    public static void main(String[] args){
        Injector injector = Guice.createInjector(new AppModule(), new AppServletModule());
        injector.getInstance(Bootstrap.class).start();
//        injector.getInstance(TelegramReceiver.class);
    }

}
