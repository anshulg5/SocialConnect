package org.example;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.model.AppMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {

    private MediatorApp app;

    @Inject
    TelegramBot(MediatorApp app){
        this.app = app;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendMessage(update.getMessage()); // Call Mediator's method to send the message
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


    public boolean sendMessage(Message msg) {
        AppMessage appmsg = new AppMessage();
        appmsg.setUser(msg.getFrom().getFirstName());
        appmsg.setChannelId(msg.getChatId().toString());
        appmsg.setText(msg.getText());
        return app.sendMessage(appmsg);
    }

}
