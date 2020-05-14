package org.example.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.example.helpers.HttpClientHelper;
import org.example.helpers.JsonHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Target {
    private String id;
    private Map<String, Object> targetMap;
    private ObjectMapper mapper = new ObjectMapper();

    public Target(String id, Map<String, Object> targetSpec) throws IllegalAccessException {
        this.id = id;
        this.targetMap = (Map<String, Object>) JsonHelper.createCopy(targetSpec,null);
    }

    public void execute(Map<String, Object> payload) throws Exception {
        try {
            Map<String, Object> obj = prepareStage(payload);
            actionStage(obj);
        }
        catch (Exception e){
            System.out.println("Target id: "+ id + " execution failed");
            throw e;
        }
    }

    private Map<String, Object> prepareStage(Map<String, Object> payload) throws IllegalAccessException, IOException {
        Map<String, Object> obj = new HashMap<>();
        obj.put("payload",payload);
        Map<String, Object> prepareMap = (Map<String, Object>) targetMap.get("prepare");

        for(String key: prepareMap.keySet()){
            Map<String, Object> apiMap = (Map<String, Object>) ((Map<String, Object>) prepareMap.get(key)).get("API");
            String url = (String) apiMap.get("endpoint");
            Map<String, Object> postDataMap = (Map<String, Object>) JsonHelper
                    .createCopy(apiMap.get("post_data"),payload);

            obj.put(key, getPOSTContent(url,postDataMap));
        }

        return obj;
    }

    private void actionStage(Map<String, Object> obj) throws IllegalAccessException, IOException {
        Map<String, Object> apiMap = (Map<String, Object>) ((Map)targetMap.get("action")).get("API");
        getPOSTContent((String)apiMap.get("endpoint"),
                (Map<String, Object>) JsonHelper.createCopy(apiMap.get("post_data"),obj));
    }

    private Map<String, Object> getPOSTContent(String url, Map<String, Object> map) throws IOException {
        HttpResponse response = HttpClientHelper.POST(url, map);

         BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufReader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }

        return mapper.readValue(builder.toString(), new TypeReference<Map<String, Object>>() {});

    }
}
