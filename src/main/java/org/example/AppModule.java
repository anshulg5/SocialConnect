package org.example;

import com.google.inject.AbstractModule;
import org.example.db.ConnectionDetailDao;
import org.example.db.PgConnectionDaoImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConnectionDetailDao.class).to(PgConnectionDaoImpl.class);
    }
}
