package org.receiver.telegram;

import org.jdbc.dao.TelegramBotDao;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class TelegramReceiver
{
//    private Sender mediator;
    TelegramBotsApi botsApi;
    TelegramBotDao telegramBotDao;
    Map<String, String> botsMap;

    @Inject
    TelegramReceiver(TelegramBotDao telegramBotDao){
//        this.mediator = mediator;
        this.telegramBotDao = telegramBotDao;
        ApiContextInitializer.init();
        this.botsApi = new TelegramBotsApi();
        init();
    }

    void init(){
        System.out.println("Initializing BotApi");
        botsMap = telegramBotDao.getAllBots();
        botsMap.forEach((k,v) -> {
            System.out.println("adding bots");
            try {
                System.out.println("adding bot:"+k);

                botsApi.registerBot(new TelegramBot(k,v));

            } catch (TelegramApiRequestException e) {
                e.printStackTrace();
            }
        });
    }

    public void addBot(String name, String token){
        try {

            botsApi.registerBot(new TelegramBot(name,token));
            telegramBotDao.addBot(name,token);
            botsMap.put(name,token);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
