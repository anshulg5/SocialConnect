package org.example;

import com.google.inject.Injector;
import org.example.main.Main;
import org.example.modules.TestModule;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class TestExtension implements TestInstancePostProcessor, BeforeAllCallback {

//    private final static Logger logger = LoggerFactory.getLogger(TestExtension.class);
    private static Injector INJECTOR = null;
    private static int cnt = 0;


    private void createInjectorandBindDependencies() {
        if (INJECTOR == null) {
            INJECTOR = Main.createInjector(new TestModule());
        }
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) {
        beforeAll(extensionContext);
        INJECTOR.injectMembers(testInstance);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        createInjectorandBindDependencies();
    }

//    @Override
//    public void afterEach(ExtensionContext context) throws Exception {
//        String truncateTablesQuery = "SELECT 'TRUNCATE ' || table_name || ';'\n" +
//                "FROM information_schema.tables\n" +
//                "WHERE table_schema='my_schema_name'\n" +
//                "AND table_type='BASE TABLE';\n";
//        JdbcTemplate jdbcTemplate = INJECTOR.getInstance(JdbcTemplate.class);
//        jdbcTemplate.execute(truncateTablesQuery);
//    }

}
