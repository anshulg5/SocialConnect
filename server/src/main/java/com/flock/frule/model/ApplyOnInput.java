package com.flock.frule.model;

import java.util.concurrent.CompletionException;

public class ApplyOnInput implements DataOperation<JsonData>{
    private final DataOperation<JsonData> fetchData;

    public ApplyOnInput(DataOperation<JsonData> fetchData) {
        this.fetchData = fetchData;
    }

    @Override
    public JsonData execute(JsonData input) {
        try {
            return fetchData.execute(input).applyOn(input);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
