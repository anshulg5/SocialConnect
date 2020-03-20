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
    private ExecutorService executorService = null;
    private final Integer MAX_THREAD = 10;

    @Inject
    TelegramBot(MediatorApp app){
        this.app = app;
        executorService = Executors.newFixedThreadPool(MAX_THREAD);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message msg = update.getMessage();
            executorService.execute(new TelgramSender(this,msg));
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
        executorService.shutdown();
    }

    public String getBotMsgText() { return botMsgText; }

    public void setBotMsgText(String botMsgText) { this.botMsgText = botMsgText; }
}
