package com.flock.frule.main;

import com.flock.frule.model.NodeFactory;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

public class NodeFactoriesLoader {
    @Inject
    @Named("nodefactory-package")
    private String packages;

    @Inject
    public void load(){
        String[] packageList = packages.split(",");
        Reflections reflections = new Reflections(packageList);
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
