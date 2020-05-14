package org.example.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import org.configRule.Node.PathNode;
import org.example.Node;
import org.example.NodeManager;
import org.example.TestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
public class JsonHelperTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws JsonProcessingException, IllegalAccessException {
        String string = "{\"PTH\": {\"STRLIST\": [\"from\",\"firstName\"] } }";
        Map<String, Object> map = mapper.readValue(string,new TypeReference<Map<String,Object>>(){});
        Node pathNode = (Node) JsonHelper.createCopy(map,null);
        assertTrue(pathNode instanceof PathNode);
    }
    
    @Test
    public void test2() throws JsonProcessingException, IllegalAccessException {
        String string = JsonParser.parseString(
            "{" +
                "id:unique-id," +
                "prepare:{" +
                    "var1:{" +
                        "API:{" +
                            "endpoint:api_url," +
                            "post_data:\"... constants, some data from message\"" +
                        "}" +
                    "}," +
                    "var2:..." +
                "}," +
                "action:{" +
                    "API:{" +
                        "endpoint:api_url," +
                        "post_data:{" +
                            "key1:{PTH: {STRLIST: [text] } }," +
                            "key2:{" +
                                "key2.1:123," +
                                "key2.2:string," +
                                "key2.3:$var1.some.path" +
                            "}," +
                            "key3:{PTH: {STRLIST: [from,firstName] } }" +
                        "}" +
                    "}" +
                "}" +
            "}").toString();
        Map<String, Object> map = mapper.readValue(string,new TypeReference<Map<String,Object>>(){});
        System.out.println("jsonMap: "+map);
        Map<String, Object> targetMap = (Map<String, Object>) JsonHelper.createCopy(map,null);
        System.out.println("targetMap: "+targetMap);

        GuavaMapBuilder<String, Object> mapBuilder = new GuavaMapBuilder<>();
        Map<String, Object> payload = mapBuilder.get()
                .put("from",mapBuilder.get()
                        .put("firstName","Anshul")
                        .put("lastName","Gupta")
                        .build())
                .put("text","Hi")
                .put("group","FlockTesting")
                .build();
        System.out.println("payload: "+payload);


        Map<String,Object> postData = (Map<String, Object>) JsonHelper.createCopy(targetMap,payload);
        System.out.println("postData: "+postData);

        Map<String, Object> pathMap = mapper.readValue(JsonParser
                .parseString("{PTH : { STRLIST : [action, API, post_data, key3] } }")
                .toString(), new TypeReference<Map<String, Object>>(){});
        Node pathNode = NodeManager.create(pathMap);
        assertEquals(pathNode.apply(postData),"Anshul");



    }
}
