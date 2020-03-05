package org.sender.telegram;

import org.Bot.BotFactory;
import org.jdbc.dao.TelegramBotDao;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class TelegramSender
{
    TelegramBotsApi botsApi;
    TelegramBotDao telegramBotDao;
    BotFactory botFactory;
    Map<String, String> botsMap;

    @Inject
    TelegramSender(TelegramBotDao telegramBotDao, BotFactory botFactory){
//        this.mediatorApp = mediatorApp;
        this.telegramBotDao = telegramBotDao;
        this.botFactory = botFactory;
        ApiContextInitializer.init();
        this.botsApi = new TelegramBotsApi();
        init();
    }

    void init(){
        System.out.println("Initializing BotApi");
        botsMap = telegramBotDao.getAllBots();
        System.out.println("Adding bots");
        botsMap.forEach((k,v) -> {
            try {
                System.out.println("\n    adding:"+k);

                botsApi.registerBot((LongPollingBot) botFactory.create(k,v));

            } catch (TelegramApiRequestException e) {
                e.printStackTrace();
            }
        });
    }

    public void addBot(String name, String token){
        try {

            botsApi.registerBot((LongPollingBot) botFactory.create(name,token));
            if(!botsMap.containsKey(name)) {
                telegramBotDao.addBot(name, token);
                botsMap.put(name, token);
            }
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    //public deleteBot
}
