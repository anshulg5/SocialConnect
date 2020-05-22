package com.flock.frule.model;

import org.example.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

public class ReadJsonFile implements DataOperation<JsonData> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Node<String> arg;

    public ReadJsonFile(Node<String> arg) {
        this.arg = arg;
    }

    // TODO: replace concatenation with something efficient
    @Override
    public JsonData execute(JsonData input) {
        try {
            String fileName = arg.apply(input.asMap());
            log.debug("reading file: {}", fileName);
            JsonData jsonData = JsonData.fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))
//                    .lines().reduce("", (s1, s2) -> s1 + "\n" + s2));
                    .lines().collect(Collectors.joining("\n")));
            log.debug("read content: {}", jsonData);
            return jsonData;
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
