package org.example;

import org.example.model.AppMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TelgramSender implements Runnable {

    TelegramBot bot;
    Message message;

    public TelgramSender(TelegramBot bot, Message message) {
        this.bot = bot;
        this.message = message;
    }

    @Override
    public void run() {
        AppMessage appMessage = new AppMessage();
        appMessage.setChannelId(String.valueOf(message.getChatId()));
        appMessage.setText(message.getText());
        appMessage.setChannelName(message.getChat().getTitle());
        appMessage.setProvider("Telegram");
        appMessage.setSentBy(message.getFrom().getFirstName());
        bot.sendMessage(appMessage);
    }
}
