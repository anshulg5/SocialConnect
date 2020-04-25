package org.example.main;

import org.example.app.MediatorApp;
import org.example.app.RuleApp;

import javax.inject.Inject;

public class Bootstrapper {
    private final Server server;
    private final NodeFactoriesLoader nodeFactoriesLoader;
    private final RuleApp ruleApp;
    private final MediatorApp mediatorApp;

    @Inject
    Bootstrapper(NodeFactoriesLoader nodeFactoriesLoader,
                 MediatorApp mediatorApp,
                 RuleApp ruleApp,
                 Server server){
        this.nodeFactoriesLoader = nodeFactoriesLoader;
        this.mediatorApp = mediatorApp;
        this.ruleApp = ruleApp;
        this.server = server;
    }

    public void start(){
        server.start();
        nodeFactoriesLoader.load();
        ruleApp.init();
    }

}
