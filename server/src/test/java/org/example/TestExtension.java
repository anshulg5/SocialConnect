package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import org.example.main.Main;
import org.example.modules.AppModule;
import org.example.modules.NodeModule;
import org.example.modules.TestModule;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class TestExtension implements TestInstancePostProcessor, BeforeAllCallback {

//    private final static Logger logger = LoggerFactory.getLogger(TestExtension.class);
//    private static boolean started = false;
    private static Injector INJECTOR = null;


    private void createInjectorandBindDependencies() {
        if (INJECTOR == null) {
            INJECTOR = Main.start(new TestModule());
        }
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) throws Exception {
        beforeAll(extensionContext);
        INJECTOR.injectMembers(testInstance);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
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
