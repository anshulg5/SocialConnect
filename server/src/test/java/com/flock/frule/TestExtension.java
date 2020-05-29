package com.flock.frule;

import com.flock.frule.main.Main;
import com.flock.frule.modules.TestModule;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class TestExtension implements TestInstancePostProcessor {

//    private final static Logger logger = LoggerFactory.getLogger(TestExtension.class);
    private static Injector INJECTOR = null;


    private void createInjectorandBindDependencies() {
        if (INJECTOR == null) {
            INJECTOR = Main.createInjector(new TestModule());
        }
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) {
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        createInjectorandBindDependencies();
        INJECTOR.injectMembers(testInstance);
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
