package org.example.db;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.db.ConnectionDetailDao;
import org.example.model.ConnectionDetail;
import org.example.model.ConnectionMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

@Singleton
public class PgConnectionDaoImpl implements ConnectionDetailDao {

    private final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private final String USER = "testdb";
    private final String DRIVER = "org.postgresql.Driver";
    private final String PASSWORD = "zxcvbnm";
    private JdbcTemplate db = null;

    @Inject
    public PgConnectionDaoImpl(DriverManagerDataSource driverManagerDataSource) {
        this.db = new JdbcTemplate(Createdatasource(driverManagerDataSource));
    }

    private DataSource Createdatasource(DriverManagerDataSource driverManagerDataSource) {
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setPassword(PASSWORD);
        driverManagerDataSource.setDriverClassName(DRIVER);
        return driverManagerDataSource;
    }

    @Override
    public List<ConnectionDetail> getAllConnections() {
        return db.query("SELECT * from connection", new ConnectionMapper());
    }

    @Override
    public boolean saveDetails(ConnectionDetail detail) {
        return db.update("INSERT into connection(sourceid,targetid) values(?,?)",detail.getSourceID(),detail.getTargetID()) > 0;
    }

}
