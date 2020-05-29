package com.flock;

import com.flock.frule.configRule.nodes.PathNode;
import com.flock.frule.configRule.nodes.primitivenodes.StringNode;
import com.flock.frule.helpers.GuavaMapBuilder;
import com.flock.frule.main.Main;
import com.flock.frule.model.*;
import com.flock.frule.util.HttpClientWrapper;
import com.google.common.collect.ImmutableMap;
import org.eclipse.jetty.http.HttpMethod;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PostRequestExample {
    public static void main(String[] args) throws IllegalAccessException {

        if(System.getProperty("ENV") == null || System.getProperty("ENV").isEmpty()){
            System.setProperty("ENV","local");
        }
        Main.createInjector();

        HttpClientWrapper httpClient =new HttpClientWrapper();

        Map<String, DataOperation<JsonData>> vars = ImmutableMap.of(
                "var_httprequestdata", new MakeHttpRequest(HttpMethod.POST,
                        new PathNode<>(ImmutableMap.of("STRLIST", Arrays.asList("endpoint"))),
                        new ApplyOnInput(new ReadJsonFile(
                                new PathNode<>(ImmutableMap.of("STRLIST", Arrays.asList("filelocation"))))),
                        httpClient),

                "var_filedata", new ReadJsonFile(new StringNode("server/src/test/resources/example.json"))
        );

        TargetAction action = input -> {
            System.out.println("got input data for action: " + input);
            return CompletableFuture.completedFuture(null);
        };

        Target target = new Target(vars, action);

        JsonData input = JsonData.createEmpty();
        input.put("filelocation","server/src/test/resources/example3.json");
        input.put("endpoint","http://localhost:1080/rulemanager/fetch");

        GuavaMapBuilder<String, Object> mapBuilder = new GuavaMapBuilder<>();
        Map<String, Object> payload = mapBuilder.get()
                .put("from",mapBuilder.get()
                        .put("firstName","Anshul")
                        .put("lastName","Gupta")
                        .build())
                .put("text","Hi")
                .put("group","FlockTesting")
                .build();
        input.put("payload",payload);

        target.execute(input);
    }
}
