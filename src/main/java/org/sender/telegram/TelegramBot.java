package org.sender.telegram;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.Bot.Bot;
import org.example.MediatorApp;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot implements Bot {

    MediatorApp mediatorApp;
    private String name = "Connectu_bot";
    private String token = "1025292716:AAEm4MMS1pahdc394Y_Wpjnkz7_D0v5EfWQ";


    @Inject
    public TelegramBot(MediatorApp mediatorApp, @Assisted("name") String name, @Assisted("token") String token) {
        this.mediatorApp = mediatorApp;
        this.name = name;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                    .setChatId(update.getMessage().getChatId())
//                    .setText(update.getMessage().getText());

//            mediatorApp.send(update.getMessage()); // Call MediatorApp's method to send the message
            Message msg = update.getMessage();
//            System.out.println(msg);
            mediatorApp.send(msg);
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
