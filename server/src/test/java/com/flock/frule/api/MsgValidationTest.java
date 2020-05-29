package com.flock.frule.api;

import com.flock.frule.TestExtension;
import com.flock.frule.helpers.GuavaListBuilder;
import com.flock.frule.helpers.GuavaMapBuilder;
import com.flock.frule.model.JsonData;
import com.flock.frule.model.Rule;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
public class MsgValidationTest {

    private static Rule rule1, rule2, rule3, rule4, rule5;
    private static JsonData jsonData1, jsonData2, jsonData3, jsonData4, jsonData5, jsonData6, jsonData7 ,jsonData8, jsonData9, jsonData10,
            jsonData11, jsonData12, jsonData13, jsonData14, jsonData15, jsonData16, jsonData17 ,jsonData18, jsonData19, jsonData20;

    @ParameterizedTest
    @MethodSource
    public void shouldReturnTrue_whenRulePassOnMsg(Rule rule, JsonData input){
        assertTrue(rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(Rule rule, JsonData input){
        assertFalse(rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(Rule rule, JsonData input){
        assertThrows(NullPointerException.class, () -> rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(Rule rule, JsonData input){
        assertThrows(IndexOutOfBoundsException.class, () -> rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(Rule rule, JsonData input){
        assertThrows(NumberFormatException.class, () -> rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(Rule rule, JsonData input){
        assertThrows(ClassCastException.class, () -> rule.validate(input));
    }


    private static Stream<Arguments> shouldReturnTrue_whenRulePassOnMsg(){
        return Stream.of(
                Arguments.of(rule1,jsonData1),
                Arguments.of(rule1,jsonData2),
                Arguments.of(rule2,jsonData7),
                Arguments.of(rule3,jsonData10),
                Arguments.of(rule3,jsonData11),
                Arguments.of(rule5,jsonData19)
        );
    }

    private static Stream<Arguments> shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(){
        return Stream.of(
                Arguments.of(rule1,jsonData3),
                Arguments.of(rule1,jsonData4),
                Arguments.of(rule2,jsonData8),
                Arguments.of(rule3,jsonData16),
                Arguments.of(rule3,jsonData18),
                Arguments.of(rule5,jsonData20)
        );
    }

    private static Stream<Arguments> shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(){
        return Stream.of(
                Arguments.of(rule1,jsonData6),
                Arguments.of(rule1,jsonData7),
                Arguments.of(rule1,jsonData8),
                Arguments.of(rule1,jsonData9),

                Arguments.of(rule2,jsonData1),
                Arguments.of(rule2,jsonData2),
                Arguments.of(rule2,jsonData3),
                Arguments.of(rule2,jsonData4),
                Arguments.of(rule2,jsonData5),
                Arguments.of(rule2,jsonData6),
                Arguments.of(rule2,jsonData9),

                Arguments.of(rule3,jsonData1),
                Arguments.of(rule3,jsonData2),
                Arguments.of(rule3,jsonData3),
                Arguments.of(rule3,jsonData4),
                Arguments.of(rule3,jsonData5),
                Arguments.of(rule3,jsonData6),
                Arguments.of(rule3,jsonData7),
                Arguments.of(rule3,jsonData8),
                Arguments.of(rule3,jsonData9),
                Arguments.of(rule3,jsonData17)
        );
    }

    private static Stream<Arguments> shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(){
        return Stream.of(
                Arguments.of(rule1,jsonData5)
        );
    }

    private static Stream<Arguments> shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(){
        return Stream.of(
                Arguments.of(rule4,jsonData1)
        );
    }

    private static Stream<Arguments> shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(){
        return Stream.of(
                Arguments.of(rule3,jsonData12),
                Arguments.of(rule3,jsonData13),
                Arguments.of(rule3,jsonData14),
                Arguments.of(rule3,jsonData15)
        );
    }



    @BeforeAll
    private void loadRuleList() throws IOException, IllegalAccessException {
        String ruleString1 = JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { STRLIST: [array,1] }" +
                                "}," +
                                "{" +
                                    "INT: 52" +
                                "}" +
                            "]" +
                      "}").toString();
        rule1 = new Rule("id1",ruleString1);

        String ruleString2 = JsonParser.parseString(
                "{" +
                        "AND: [" +
                                "{" +
                                    "EQ: [" +
                                            "{" +
                                                "PTH: { STRLIST: [from,firstName] } " +
                                            "}," +
                                            "{" +
                                                "STR: Anshul" +
                                            "}" +
                                        "]" +
                                "}," +
                                "{" +
                                    "NOT: [" +
                                            "{" +
                                                "EQ: [" +
                                                        "{" +
                                                            "STR: Hi" +
                                                        "}," +
                                                        "{" +
                                                            "PTH: { STRLIST: [text] } " +
                                                        "}" +
                                                    "]" +
                                            "}" +
                                        "]" +
                                "}" +
                            "]" +
                    "}").toString();
        rule2 = new Rule("id2",ruleString2);

        String ruleString3 = JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { PTH: { STRLIST: [path_to] } }" +
                                "}," +
                                "{ " +
                                    "STR: Anshul " +
                                "}" +
                            "]" +
                    "}").toString();
        rule3 = new Rule("id3",ruleString3);

        String ruleString4 = JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { STRLIST: [array,non-numeric-index] }" +
                                "}," +
                                "{" +
                                    "INT: 52" +
                                "}" +
                            "]" +
                      "}").toString();
        rule4 = new Rule("id4",ruleString4);

        String ruleString5 = JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { STRLIST: [array] }" +
                                "}," +
                                "{" +
                                    "STRLIST: [1,2,flock]" +
                                "}" +
                            "]" +
                      "}").toString();
        rule5 = new Rule("id5",ruleString5);

    }

    @BeforeAll
    private static void loadMsgList(){
        GuavaMapBuilder<String, Object> mapBuilder = new GuavaMapBuilder<>();
        GuavaListBuilder<Object> listBuilder = new GuavaListBuilder<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ImmutableMap<String, Object> map;

//
        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add("asd",52,"aaa",23432)
                    .build())
                .build();
//        jsonData1 = new HashMap<>(map);
        jsonData1 = JsonData.fromJson(gson.toJson(map));
        System.out.println("yup "+jsonData1);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add(51,52)
                        .build())
                .build();
//        jsonData2 = new HashMap<>(map);
        jsonData2 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("asd",53,"aaa",23432)
                        .build())
                .build();
//        jsonData3 = new HashMap<>(map);
        jsonData3 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("asd","adsad","aaa",23432)
                        .build())
                .build();
//        jsonData4 = new HashMap<>(map);
        jsonData4 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("single-value")
                        .build())
                .build();
//        jsonData5 = new HashMap<>(map);
        jsonData5 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",mapBuilder.get()
                        .put("1",52)
                        .build())
                .build();
//        jsonData6 = new HashMap<>(map);
        jsonData6 = JsonData.fromJson(gson.toJson(map));

//
        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("firstName","Anshul")
                    .put("lastName","Gupta")
                    .build())
                .put("text","Bye")
                .put("group","FlockTesting")
                .build();
//        jsonData7 = new HashMap<>(map);
        jsonData7 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("firstName","Anshul")
                    .put("lastName","Gupta")
                    .build())
                .put("text","Hi")
                .put("group","FlockTesting")
                .build();
//        jsonData8 = new HashMap<>(map);
        jsonData8 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("lastName","Gupta")
                    .build())
                .put("text","Bye")
                .put("group","FlockTesting")
                .build();
//        jsonData9 = new HashMap<>(map);
        jsonData9 = JsonData.fromJson(gson.toJson(map));

//
        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .put("name","Anshul")
                .build();
//        jsonData10 = new HashMap<>(map);
        jsonData10 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("from")
                    .add("name")
                    .build())
                .put("from",mapBuilder.get()
                    .put("name","Anshul")
                    .build())
                .put("name","Anshul")
                .build();
//        jsonData11 = new HashMap<>(map);
        jsonData11 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",mapBuilder.get()
                    .put("name","Anshul")
                    .build())
                .put("name","Anshul")
                .build();
//        jsonData12 = new HashMap<>(map);
        jsonData12 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",100)
                .put("name","Anshul")
                .build();
//        jsonData13 = new HashMap<>(map);
        jsonData13 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to","name")
                .put("name","Anshul")
                .build();
//        jsonData14 = new HashMap<>(map);
        jsonData14 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",true)
                .put("name","Anshul")
                .build();
//        jsonData15 = new HashMap<>(map);
        jsonData15 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .put("name","Unknown")
                .build();
//        jsonData16 = new HashMap<>(map);
        jsonData16 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .build();
//        jsonData17 = new HashMap<>(map);
        jsonData17 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .build())
                .put("name","Anshul")
                .build();
//        jsonData18 = new HashMap<>(map);
        jsonData18 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add(1,2,"flock")
                    .build())
                .build();
//        jsonData19 = new HashMap<>(map);
        jsonData19 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add(1,2,3)
                    .build())
                .build();
//        jsonData20 = new HashMap<>(map);
        jsonData20 = JsonData.fromJson(gson.toJson(map));

    }

}
