package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    Sender mediator;

    TelegramBot(Sender mediator){
        this.mediator = mediator;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                    .setChatId(update.getMessage().getChatId())
//                    .setText(update.getMessage().getText());

            mediator.send(update.getMessage().getText()); // Call Mediator's method to send the message
        }
    }

    @Override
    public String getBotUsername() {
        return "Connectu_bot";
    }

    @Override
    public String getBotToken() {
        return "1025292716:AAEm4MMS1pahdc394Y_Wpjnkz7_D0v5EfWQ";
    }
}
