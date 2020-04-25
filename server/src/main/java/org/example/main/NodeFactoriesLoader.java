package org.example.main;

import org.example.NodeFactory;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Iterator;
import java.util.Set;

public class NodeFactoriesLoader {
    @Inject
    @Named("nodefactory-package")
    private String packageName;

    public void load(){
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends NodeFactory>> classes = reflections.getSubTypesOf(NodeFactory.class);

        for (Class<? extends NodeFactory> aClass : classes) {
            String className = aClass.toString()
                                        .split(" ")[1];
            try {
                Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
