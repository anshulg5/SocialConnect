package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import org.example.modules.AppModule;
import org.example.modules.AppServletModule;
import org.example.modules.NodeModule;
import org.example.modules.TestModule;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestExtension implements TestInstancePostProcessor, BeforeAllCallback {

//    private final static Logger logger = LoggerFactory.getLogger(TestExtension.class);
//    private static boolean started = false;
    private static Injector INJECTOR = null;


    private void createInjectorandBindDependencies() {
        if (INJECTOR == null) {
            INJECTOR = Guice.createInjector(Modules
                                                .override(new AppModule(), new NodeModule())
                                                .with(new TestModule()));
        }
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) throws Exception {
        beforeAll(extensionContext);
        INJECTOR.injectMembers(testInstance);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
//        if (!started) {
//            logger.info("Starting server");
//            started = true;
//            startServerAndBindDependencies();
//        }
//        else {
//            logger.error("Not starting server");
//        }
        createInjectorandBindDependencies();
    }
}
