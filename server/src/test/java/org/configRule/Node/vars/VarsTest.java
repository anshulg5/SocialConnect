package org.configRule.Node.vars;

import com.flock.frule.configRule.util.HttpClientWrapper;
import com.flock.frule.model.JsonData;
import com.google.gson.JsonParser;
import org.example.TestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
class VarsTest {

    private static HttpClientWrapper httpClient = new HttpClientWrapper();

    @Test
    public void varJsonDataTest() {
        String jsonString = JsonParser.parseString(
                "{" +
                        "url : {" +
                                    "PTH : {" +
                                                "STRLIST : [url] " +
                                            "} " +
                                "} ," +

                        "post_data : {" +
                                        "name : { PTH : { STRLIST : [from, firstname] } } ," +
                                        "text : { PTH : { STRLIST : [text] } } ," +
                                        "group : { PTH : { STRLIST : [group] } }" +
                                    "}" +
                    "}"
        ).toString();
        System.out.println(jsonString);
        JsonData jsonData = JsonData.fromJson(jsonString);
        VarJsonData varJsonData = new VarJsonData(jsonData);

        String inputJsonString = JsonParser.parseString(
                "{" +
                        "from : {" +
                                    "firstname : Anshul ," +
                                    "lastname : Gupta" +
                                "} ," +
                        "text : hi ," +
                        "group : FlockTesting ," +
                        "url : \"http://example.com\"" +
                    "}"
        ).toString();
        System.out.println(inputJsonString);
        JsonData input = JsonData.fromJson(inputJsonString);


        JsonData varOutput = varJsonData.apply(input);

    }

    @Test
    public void varGetTest() {
        String jsonString = JsonParser.parseString(
                "{" +
                        "url : {" +
                                    "PTH : {" +
                                                "STRLIST : [to, address] " +
                                            "}" +
                                "}" +
                    "}"
        ).toString();
        System.out.println(jsonString);
        JsonData jsonData = JsonData.fromJson(jsonString);
        VarJsonData varJsonData = new VarJsonData(jsonData);

        VarGetRequest varGetRequest = new VarGetRequest(varJsonData,httpClient);

        String inputJsonString = JsonParser.parseString(
                "{" +
                        "to : {" +
                                    "address : \"http://localhost:1080/rulemanager/fetch\" " +
                              "}" +
                    "}"
        ).toString();
        JsonData input = JsonData.fromJson(inputJsonString);

        JsonData varOutput = varGetRequest.apply(input);

    }
}