package org.example.api;

import com.flock.frule.model.JsonData;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.example.TestExtension;
import org.example.helpers.GuavaListBuilder;
import org.example.helpers.GuavaMapBuilder;
import org.example.model.Rule;
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
    private static JsonData map1, map2, map3, map4, map5, map6, map7 ,map8, map9, map10,
            map11, map12, map13, map14, map15, map16, map17 ,map18, map19, map20;

    @ParameterizedTest
    @MethodSource
    public void shouldReturnTrue_whenRulePassOnMsg(Rule rule, JsonData msg){
        assertTrue(rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(Rule rule, JsonData msg){
        assertFalse(rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(Rule rule, JsonData msg){
        assertThrows(NullPointerException.class, () -> rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(Rule rule, JsonData msg){
        assertThrows(IndexOutOfBoundsException.class, () -> rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(Rule rule, JsonData msg){
        assertThrows(NumberFormatException.class, () -> rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(Rule rule, JsonData msg){
        assertThrows(ClassCastException.class, () -> rule.validate(msg));
    }


    private static Stream<Arguments> shouldReturnTrue_whenRulePassOnMsg(){
        return Stream.of(
                Arguments.of(rule1,map1),
                Arguments.of(rule1,map2),
                Arguments.of(rule2,map7),
                Arguments.of(rule3,map10),
                Arguments.of(rule3,map11),
                Arguments.of(rule5,map19)
        );
    }

    private static Stream<Arguments> shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(){
        return Stream.of(
                Arguments.of(rule1,map3),
                Arguments.of(rule1,map4),
                Arguments.of(rule2,map8),
                Arguments.of(rule3,map16),
                Arguments.of(rule3,map18),
                Arguments.of(rule5,map20)
        );
    }

    private static Stream<Arguments> shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(){
        return Stream.of(
                Arguments.of(rule1,map6),
                Arguments.of(rule1,map7),
                Arguments.of(rule1,map8),
                Arguments.of(rule1,map9),

                Arguments.of(rule2,map1),
                Arguments.of(rule2,map2),
                Arguments.of(rule2,map3),
                Arguments.of(rule2,map4),
                Arguments.of(rule2,map5),
                Arguments.of(rule2,map6),
                Arguments.of(rule2,map9),

                Arguments.of(rule3,map1),
                Arguments.of(rule3,map2),
                Arguments.of(rule3,map3),
                Arguments.of(rule3,map4),
                Arguments.of(rule3,map5),
                Arguments.of(rule3,map6),
                Arguments.of(rule3,map7),
                Arguments.of(rule3,map8),
                Arguments.of(rule3,map9),
                Arguments.of(rule3,map17)
        );
    }

    private static Stream<Arguments> shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(){
        return Stream.of(
                Arguments.of(rule1,map5)
        );
    }

    private static Stream<Arguments> shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(){
        return Stream.of(
                Arguments.of(rule4,map1)
        );
    }

    private static Stream<Arguments> shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(){
        return Stream.of(
                Arguments.of(rule3,map12),
                Arguments.of(rule3,map13),
                Arguments.of(rule3,map14),
                Arguments.of(rule3,map15)
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
//        map1 = new HashMap<>(map);
        map1 = JsonData.fromJson(gson.toJson(map));
        System.out.println(map1);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add(51,52)
                        .build())
                .build();
//        map2 = new HashMap<>(map);
        map2 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("asd",53,"aaa",23432)
                        .build())
                .build();
//        map3 = new HashMap<>(map);
        map3 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("asd","adsad","aaa",23432)
                        .build())
                .build();
//        map4 = new HashMap<>(map);
        map4 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("single-value")
                        .build())
                .build();
//        map5 = new HashMap<>(map);
        map5 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",mapBuilder.get()
                        .put("1",52)
                        .build())
                .build();
//        map6 = new HashMap<>(map);
        map6 = JsonData.fromJson(gson.toJson(map));

//
        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("firstName","Anshul")
                    .put("lastName","Gupta")
                    .build())
                .put("text","Bye")
                .put("group","FlockTesting")
                .build();
//        map7 = new HashMap<>(map);
        map7 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("firstName","Anshul")
                    .put("lastName","Gupta")
                    .build())
                .put("text","Hi")
                .put("group","FlockTesting")
                .build();
//        map8 = new HashMap<>(map);
        map8 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("lastName","Gupta")
                    .build())
                .put("text","Bye")
                .put("group","FlockTesting")
                .build();
//        map9 = new HashMap<>(map);
        map9 = JsonData.fromJson(gson.toJson(map));

//
        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .put("name","Anshul")
                .build();
//        map10 = new HashMap<>(map);
        map10 = JsonData.fromJson(gson.toJson(map));

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
//        map11 = new HashMap<>(map);
        map11 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",mapBuilder.get()
                    .put("name","Anshul")
                    .build())
                .put("name","Anshul")
                .build();
//        map12 = new HashMap<>(map);
        map12 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",100)
                .put("name","Anshul")
                .build();
//        map13 = new HashMap<>(map);
        map13 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to","name")
                .put("name","Anshul")
                .build();
//        map14 = new HashMap<>(map);
        map14 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",true)
                .put("name","Anshul")
                .build();
//        map15 = new HashMap<>(map);
        map15 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .put("name","Unknown")
                .build();
//        map16 = new HashMap<>(map);
        map16 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .build();
//        map17 = new HashMap<>(map);
        map17 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .build())
                .put("name","Anshul")
                .build();
//        map18 = new HashMap<>(map);
        map18 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add(1,2,"flock")
                    .build())
                .build();
//        map19 = new HashMap<>(map);
        map19 = JsonData.fromJson(gson.toJson(map));

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add(1,2,3)
                    .build())
                .build();
//        map20 = new HashMap<>(map);
        map20 = JsonData.fromJson(gson.toJson(map));

    }

}
