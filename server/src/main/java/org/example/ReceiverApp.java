package org.example;

import org.example.model.AppMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ReceiverApp {
    public Boolean sendMessage(AppMessage appMessage);
}
