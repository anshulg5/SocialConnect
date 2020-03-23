package org.example.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.AppModule;
import org.example.AppServletModule;
import org.example.NodeFactory;
import org.reflections.Reflections;

import java.util.Iterator;
import java.util.Set;


public class App {

    public static void main(String[] args){
        Reflections reflections = new Reflections("org.configRule");
        Set<Class<? extends NodeFactory>> classes = reflections.getSubTypesOf(NodeFactory.class);

        Iterator iterator = classes.iterator();
        while(iterator.hasNext()){
            String name = iterator.next().toString();
            String[] currencies = name.split(" ");
            try {
                Class.forName(currencies[1]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Injector injector = Guice.createInjector(new AppModule(), new AppServletModule());
        injector.getInstance(Bootstrap.class).start();
//        injector.getInstance(TelegramReceiver.class);
    }

}
