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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppModule extends AbstractModule {

    private Properties loadProperties() {

        Properties properties = new Properties();
        
        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        
        String propertiesFileName = "server/src/main/resources/" + System.getProperty("ENV") + "/" +  System.getProperty("ENV");
        Properties prop = null;
        try (InputStream input = new FileInputStream(propertiesFileName + ".properties")) {
        
            prop = new Properties();
            prop.load(input);
        
            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
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
        public PgDataSourceProvider(@Named("db.url") final String url,
                                    @Named("db.user") final String username,
                                    @Named("db.password") final String password,
                                    @Named("db.driver") final String driver) {
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
//            dataSource.setDriverClassName(driver);
            System.out.println(url);
//            final DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
            return new JdbcTemplate(dataSource);
        }
    }

}
