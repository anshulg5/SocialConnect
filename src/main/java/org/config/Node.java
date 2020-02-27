package org.config;

import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Node<T> {

    T apply(JSONObject msg);
}
