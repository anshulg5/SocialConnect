package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.example.helpers.GuavaMapBuilder;
import org.example.model.Target;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
public class TargetTest {
    private ObjectMapper mapper = new ObjectMapper();

//    @BeforeAll
//    public void startServer(){
//
//    }

    @Test
    public void test() throws Exception {
        //Given
        String string = JsonParser.parseString(
            "{" +
                "id: id1," +
                "prepare:{" +
                    "var1:{" +
                        "API:{" +
                            "endpoint:'http://localhost:1080/rulemanager/fetch'," +
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
                "}," +
                "action:{" +
                    "API:{" +
                        "endpoint:'http://localhost:1080/rulemanager/fetch'," +
                        "post_data:{" +
                            "key1:{PTH: {STRLIST: [payload,text] } }," +
                            "key2:{" +
                                "key2.1:123," +
                                "key2.2:string," +
                                "key2.3:$var1.some.path" +
                            "}," +
                            "key3:{PTH: {STRLIST: [payload,from,firstName] } }" +
                        "}" +
                    "}" +
                "}" +
            "}").toString();
        Map<String, Object> map = mapper.readValue(string,new TypeReference<Map<String,Object>>(){});

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(map));



        Target target = new Target("id1",map);

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

        //when
        target.execute(payload);


    }
}
