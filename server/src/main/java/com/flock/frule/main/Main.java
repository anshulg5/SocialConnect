package com.flock.frule.main;

import com.flock.frule.modules.AppModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;


public class Main {

    public static void main(String[] args){
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        Injector injector = createInjector();
        injector.getInstance(Server.class).start();
    }

    public static Injector createInjector(Module... modules){
        Injector injector = Guice.createInjector(Modules.override(new MainModule()).with(modules));
        return injector;
    }

    private static class MainModule extends AbstractModule{

        @Override
        protected void configure() {
            install(new AppModule());
        }
    }

}
