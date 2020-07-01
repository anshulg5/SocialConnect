package com.flock.frule.main;

import com.flock.frule.modules.PropertiesModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

public class LiquibaseHelper {

    private final String driver;
    private final String url;
    private final String changeLogFilePath;
    private static Logger log = LoggerFactory.getLogger(LiquibaseHelper.class.getCanonicalName());

    @Inject
    public LiquibaseHelper(@Named("db.driver") String driver,
                           @Named("db.url") String url,
                           @Named("db.changelog.path") String changeLogFilePath) {
        this.driver = driver;
        this.url = url;
        this.changeLogFilePath = changeLogFilePath;
    }

    public void update() {
        log.info("Running DB Schema Migrations");
        try {
            liquibase.integration.commandline.Main.run(new String[]{"--driver=" + driver,
                    "--changeLogFile=" + changeLogFilePath, "--url=" + url, "update"});
        } catch (Exception e) {
            log.error("Failed to updated database", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        Injector injector = Guice.createInjector(new PropertiesModule());
        LiquibaseHelper lbh = injector.getInstance(LiquibaseHelper.class);
        lbh.update();
    }
}
