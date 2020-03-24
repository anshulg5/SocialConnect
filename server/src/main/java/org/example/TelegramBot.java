package org.example;

import com.google.inject.Inject;
import org.example.model.AppMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            System.out.println(message);
            AppMessage appMessage = new AppMessage();
            appMessage.setChannelId(String.valueOf(message.getChatId()));
            appMessage.setText(message.getText());
            appMessage.setChannelName(message.getChat().getTitle());
            appMessage.setProvider("Telegram");
            appMessage.setSentBy(message.getFrom().getFirstName());
            sendMessage(appMessage);
            sendMessage(appMessage);
        }
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

    @Override
    public void onClosing() {
        super.onClosing();
    }

    public String getBotMsgText() { return botMsgText; }

    public void setBotMsgText(String botMsgText) { this.botMsgText = botMsgText; }
}
