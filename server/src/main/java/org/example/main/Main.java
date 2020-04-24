package org.example.main;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.example.modules.AppModule;
import org.example.modules.AppServletModule;
import org.example.modules.NodeModule;


public class Main {

    public static void main(String[] args){
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        start();
    }

    public static Injector start(Module... modules){
        Injector injector = Guice.createInjector(Modules.override(new MainModule()).with(modules));
        injector.getInstance(Bootstrap.class).start();
        return injector;
    }

    private static class MainModule extends AbstractModule{

        @Override
        protected void configure() {
            install(new AppModule());
        }
    }

}
