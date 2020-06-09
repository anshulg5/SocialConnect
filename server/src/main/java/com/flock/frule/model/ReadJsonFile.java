package com.flock.frule.model;

import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionException;

public class ReadJsonFile implements Node<JsonType> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Node<String> arg;

    public ReadJsonFile(Node<String> arg) {
        this.arg = arg;
    }

    @Override
    public JsonType apply(JsonType input) {
        try {
            String fileName = arg.apply(input);
            log.debug("reading file: {}", fileName);
//            JsonType json = JsonType.fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))
//                    .lines().collect(Collectors.joining("\n")));
//            log.debug("read content: {}", json);
//            return json;
            return JsonObject.createEmpty();
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
