package com.flock.frule;

import com.flock.frule.model.AppMessage;

public interface ReceiverApp {
    Boolean sendMessage(AppMessage appMessage);
}
