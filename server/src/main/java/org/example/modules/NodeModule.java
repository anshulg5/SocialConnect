package org.example.modules;

import com.google.inject.AbstractModule;
import org.example.NodeFactory;
import org.reflections.Reflections;

import java.util.Iterator;
import java.util.Set;

public class NodeModule extends AbstractModule {
    @Override
    protected void configure() {
        loadNodeFactories();
    }

    private void loadNodeFactories(){
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
    }
}
