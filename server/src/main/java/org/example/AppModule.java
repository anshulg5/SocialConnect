package org.example;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.example.db.RuleDaoImpl;
import org.example.db.dao.BotDetailDao;
import org.example.db.dao.ConnectionDetailDao;
import org.example.db.PgBotDetailDaoImpl;
import org.example.db.PgConnectionDaoImpl;
import org.example.db.dao.RuleDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Properties;

public class AppModule extends AbstractModule {

    private Properties loadProperties() {

        Properties properties = new Properties();
        properties.setProperty("url" , "jdbc:postgresql://localhost:5432/SocialConnect");
        properties.setProperty("username" , "root");
        properties.setProperty("password" , "root");
        properties.setProperty("driver" , "org.postgresql.Driver");

        return properties;
    }
    @Override
    protected void configure() {
        Names.bindProperties(binder(), loadProperties());
        bind(JdbcTemplate.class).toProvider(PgDataSourceProvider.class).in(Scopes.SINGLETON);
        bind(ConnectionDetailDao.class).to(PgConnectionDaoImpl.class);
        bind(BotDetailDao.class).to(PgBotDetailDaoImpl.class);
        bind(RuleDao.class).to(RuleDaoImpl.class);
    }

    static class PgDataSourceProvider implements Provider<JdbcTemplate> {

        private final String url;
        private final String username;
        private final String password;
        private final String driver;

        @Inject
        public PgDataSourceProvider(@Named("url") final String url,
                                    @Named("username") final String username,
                                    @Named("password") final String password,
                                    @Named("driver") final String driver) {
            this.url = url;
            this.username = username;
            this.password = password;
            this.driver = driver;
        }

        @Override
        public JdbcTemplate get() {
            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDriverClassName(driver);
            return new JdbcTemplate(dataSource);
        }
    }

}
