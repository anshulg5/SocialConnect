package org.example.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.modules.*;


public class App {

    public static void main(String[] args){
        Injector injector = Guice.createInjector(new AppModule(), new AppServletModule(), new NodeModule());
        injector.getInstance(Bootstrap.class).start();
//        injector.getInstance(TelegramReceiver.class);
    }

}
