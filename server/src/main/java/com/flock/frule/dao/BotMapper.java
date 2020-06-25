package com.flock.frule.dao;


import com.flock.frule.model.BotDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BotMapper implements RowMapper<BotDetail> {

    public BotDetail mapRow(ResultSet resultSet, int i) throws SQLException {

       BotDetail botDetail = new BotDetail();
       botDetail.setBotUserName(resultSet.getString("bot_username"));
       botDetail.setBotToken(resultSet.getString("bot_token"));
       botDetail.setMsgText(resultSet.getString("msg_text"));
       return botDetail;

    }
}
