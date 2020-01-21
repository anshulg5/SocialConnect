package org.example;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * Hello world!
 *
 */
public class Receiver_app
{
    private Sender mediator;

    Receiver_app(Sender mediator){
        this.mediator = mediator;
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TelegramBot(this.mediator));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
