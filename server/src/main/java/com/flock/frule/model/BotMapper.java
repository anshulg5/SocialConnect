package com.flock.frule.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BotMapper implements RowMapper<BotDetail> {

    public BotDetail mapRow(ResultSet resultSet, int i) throws SQLException {

       BotDetail botDetail = new BotDetail();
       botDetail.setBotUserName(resultSet.getString("botUserName"));
       botDetail.setBotToken(resultSet.getString("botToken"));
       botDetail.setMsgText(resultSet.getString("msgText"));
       return botDetail;

    }
}
