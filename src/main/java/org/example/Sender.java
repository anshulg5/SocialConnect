package org.example;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Sender {
    public void send(String msg);
}
