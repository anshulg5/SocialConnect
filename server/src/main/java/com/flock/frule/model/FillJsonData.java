package com.flock.frule.model;

import org.example.helpers.JsonHelper;

import java.util.Map;
import java.util.concurrent.CompletionException;

public class FillJsonData implements DataOperation<JsonData>{
    private final DataOperation<JsonData> fetchData;

    public FillJsonData(DataOperation<JsonData> fetchData) {
        this.fetchData = fetchData;
    }



    // TODO: either combine fetch and fill operations together,
//           or look for some way to fetch dataspec in constructor itself
    @Override
    public JsonData execute(JsonData input) {
        try {
            //fetch dataSpec
            Map<String, ?> dataSpecMap = JsonHelper.createCopy(fetchData.execute(input).asMap(),null);
            JsonData dataSpec = JsonData.createEmpty();
            dataSpecMap.forEach(dataSpec::put);

            //fill the dataSpec
            Map<String, ?> filledDataMap = JsonHelper.createCopy(dataSpec.asMap(), input.asMap());
            JsonData filledData = JsonData.createEmpty();
            filledDataMap.forEach(filledData::put);
            return filledData;
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
