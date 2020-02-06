package org.example;

import com.google.inject.Inject;
import org.example.model.AppMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot implements ReceiverApp{

    private MediatorApp app;
    private String botUserName;
    private String botToken;
    private String botMsgText = null;

    @Inject
    TelegramBot(MediatorApp app){
        this.app = app;
    }

    @Override
    public void onUpdateReceived(Update update) {
       // if (update.hasMessage() && update.getMessage().hasText()) {
            Message msg = update.getMessage();
    //
        // process in other function
        AppMessage appMessage = new AppMessage();
        appMessage.setChannelId(String.valueOf(msg.getChatId()));
        appMessage.setText(msg.getText());
        appMessage.setChannelName(msg.getChat().getTitle());
        appMessage.setProvider("Telegram");
        appMessage.setSentBy(msg.getFrom().getFirstName());
        sendMessage(appMessage);
     //       sendMessage(update.getMessage()); // Call Mediator's method to send the message
        //}
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public Boolean sendMessage(AppMessage appMessage) {
        return this.app.sendMessage(appMessage,botMsgText);
    }

    public String getBotMsgText() { return botMsgText; }

    public void setBotMsgText(String botMsgText) { this.botMsgText = botMsgText; }
}
