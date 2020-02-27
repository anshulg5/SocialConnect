package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import org.telegram.telegrambots.meta.api.objects.Message;

public class App {

    public static void main(String[] args){
        Injector injector = Guice.createInjector(new AppModule(), new AppServletModule());
        injector.getInstance(RequestHandler.class).start();
    }

}
