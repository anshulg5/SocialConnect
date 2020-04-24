package org.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.example.db.PgBotDetailDaoImpl;
import org.example.db.PgConnectionDaoImpl;
import org.example.db.RuleDaoImpl;
import org.example.db.dao.BotDetailDao;
import org.example.db.dao.ConnectionDetailDao;
import org.example.db.dao.RuleDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConnectionDetailDao.class).to(PgConnectionDaoImpl.class);
        bind(BotDetailDao.class).to(PgBotDetailDaoImpl.class);
        bind(RuleDao.class).to(RuleDaoImpl.class);
    }

    @Provides
    @Singleton
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Provides
    @Singleton
    public DataSource createDataSource(@Named("db.url") final String url,
                                       @Named("db.driver") final String driver){

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        System.out.println(url);
        return dataSource;
    }
}
