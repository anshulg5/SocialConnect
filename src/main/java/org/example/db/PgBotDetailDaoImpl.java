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
        return db.query("SELECT * from botDetail", new BotMapper());
    }

    @Override
    public void addBotDetail(String botUserName, String botToken, String msgText) {
        db.update("INSERT INTO botDetail (botusername,bottoken,msgtext) VALUES (?, ?, ?)", botUserName, botToken, msgText);
    }

    @Override
    public void removeBotDetail(String botUserName) {
        db.update("DELETE FROM botDetail WHERE botusername = ?",botUserName);
    }

    @Override
    public void editBotMsgDetail(String botUserName, String msgText) {
        // edit here
    }
}
