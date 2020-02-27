package org.config;

import org.json.JSONObject;

public interface Node<T> {

    T apply(JSONObject msg);
}
