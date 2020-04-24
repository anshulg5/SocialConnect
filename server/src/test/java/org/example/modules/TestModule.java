package org.example.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.embedded.LiquibasePreparer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JdbcTemplate.class).toProvider(PgTestDataSourceProvider.class).in(Scopes.SINGLETON);
    }

    static class PgTestDataSourceProvider implements Provider<JdbcTemplate> {

        @Override
        public JdbcTemplate get() {
            DataSource dataSource = null;
            LiquibasePreparer liquibasePreparer = LiquibasePreparer.forClasspathLocation("changelog.xml");
            try {
                dataSource = EmbeddedPostgres.builder()
                        .start().getPostgresDatabase();
                liquibasePreparer.prepare(dataSource);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            return new JdbcTemplate(dataSource);
        }
    }
}
