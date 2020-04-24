package org.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.example.db.PgBotDetailDaoImpl;
import org.example.db.PgConnectionDaoImpl;
import org.example.db.RuleDaoImpl;
import org.example.db.dao.BotDetailDao;
import org.example.db.dao.ConnectionDetailDao;
import org.example.db.dao.RuleDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new PropertiesModule());
        install(new DatabaseModule());
        install(new AppServletModule());
        install(new NodeModule());
    }
}
