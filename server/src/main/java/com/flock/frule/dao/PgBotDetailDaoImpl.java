package com.flock.frule.dao;

import com.flock.frule.model.BotDetail;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PgBotDetailDaoImpl implements BotDetailDao {
    private final JdbcTemplate db;

    @Inject
    PgBotDetailDaoImpl(JdbcTemplate db) {
        this.db = db;
    }
    public List<BotDetail> getAllBots() {
        return db.query("SELECT * from botdetail", new BotMapper());
    }

    @Override
    public void addBotDetail(String botUserName, String botToken, String msgText) {
        db.update("INSERT INTO botdetail (bot_username,bot_token,msg_text) VALUES (?, ?, ?)", botUserName, botToken, msgText);
    }

    @Override
    public void removeBotDetail(String botUserName) {
        db.update("DELETE FROM botdetail WHERE bot_username = ?",botUserName);
    }

    @Override
    public void editBotMsgDetail(String botUserName, String msgText) {
        db.update("UPDATE botdetail SET msg_text = ? WHERE bot_username = ?",msgText,botUserName);
    }
}
