package com.flock.frule.dao;

import com.flock.frule.model.BotDetail;

import java.util.List;

public interface BotDetailDao {
    List<BotDetail> getAllBots();
    void addBotDetail(String botUserName,String botToken,String msgText);
    void removeBotDetail(String botUserName);
    void editBotMsgDetail(String botUserName,String msgText);
}
