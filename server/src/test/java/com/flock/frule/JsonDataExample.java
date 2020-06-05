package com.flock.frule;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.util.Arrays;

public class JsonDataExample {

    public static void main(String[] args) {
        //JsonData example
        JsonData jsonData = JsonData.createEmpty();
        JsonData jsonData2 = JsonData.createEmpty();

        jsonData2.put("key", "value");
        jsonData2.put("array", Arrays.asList(1,2,3));
        jsonData.put("nestedjsondata",jsonData2);
        jsonData.put("name","Anshul");
        System.out.println(jsonData);

        //JsonObject example
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        jsonObject2.put("key","value");
        jsonObject2.put("array",Arrays.asList(1,2,3));
        jsonObject.put("nestedJsonObject",jsonObject2);
        jsonObject.put("name","Anshul");
        System.out.println(jsonObject);

        //Gson example
        Gson gson = new Gson();
        JsonObject gsonObject = new JsonObject();
        JsonObject gsonObject2 = new JsonObject();

        gsonObject2.add("key",new com.google.gson.JsonPrimitive("value"));
        gsonObject2.add("array", gson.toJsonTree(Arrays.asList(1,2,3)));
        gsonObject.add("nestedJsonObject",gsonObject2);
        gsonObject.add("name",gson.toJsonTree("Anshul"));
        System.out.println(gsonObject);

        JsonObject gsonObject3 = JsonParser.parseString("{\"nestedJsonObject\":{\"key\":\"value\",\"array\":[1,2,3]},\"name\":\"Anshul\"}").getAsJsonObject();
        System.out.println(gsonObject3);

        //JsonPrimitive example
        JsonPrimitive jsonPrimitive = new JsonPrimitive("5");
        int val = jsonPrimitive.getAsInt();
        System.out.println(val);

        String val2 = jsonPrimitive.getAsString();
        System.out.println(val2);

        double val3 = jsonPrimitive.getAsDouble();
        System.out.println(val3);

    }
}
