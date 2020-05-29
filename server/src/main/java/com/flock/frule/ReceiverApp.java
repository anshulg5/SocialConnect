package com.flock.frule;

import com.flock.frule.model.AppMessage;

public interface ReceiverApp {
    public Boolean sendMessage(AppMessage appMessage);
}
