package org.receiver.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private String name = "Connectu_bot";
    private String token = "1025292716:AAEm4MMS1pahdc394Y_Wpjnkz7_D0v5EfWQ";

//    TelegramBot(Sender mediator){
//        this.mediator = mediator;
//    }

    public TelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                    .setChatId(update.getMessage().getChatId())
//                    .setText(update.getMessage().getText());

//            mediator.send(update.getMessage()); // Call Mediator's method to send the message
            System.out.println(update.getMessage());
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setToken(String token){
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
