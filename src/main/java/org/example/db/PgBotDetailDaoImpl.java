package org.example.db;

import com.google.inject.Inject;
import org.example.model.BotDetail;
import org.example.model.BotMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class PgBotDetailDaoImpl implements BotDetailDao {
    private final JdbcTemplate db;

    @Inject
    PgBotDetailDaoImpl(JdbcTemplate db) {
        this.db = db;
    }
    public List<BotDetail> getAllBots() {
        return this.db.query("SELECT * from botDetail", new BotMapper());
    }
}
