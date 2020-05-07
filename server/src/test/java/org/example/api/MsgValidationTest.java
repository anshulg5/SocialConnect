package org.example.api;

import com.google.common.collect.ImmutableMap;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
public class MsgValidationTest {

    private static List<Rule> ruleList = new ArrayList<>();
    private static List<Map<String,?>> msgList = new ArrayList<>();

    @ParameterizedTest
    @MethodSource
    public void shouldReturnTrue_whenRulePassOnMsg(Rule rule, Map<String,?> msg){
        assertTrue(rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(Rule rule, Map<String,?> msg){
        assertFalse(rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(Rule rule, Map<String,?> msg){
        assertThrows(NullPointerException.class, () -> rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(Rule rule, Map<String,?> msg){
        assertThrows(IndexOutOfBoundsException.class, () -> rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(Rule rule, Map<String,?> msg){
        assertThrows(NumberFormatException.class, () -> rule.validate(msg));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(Rule rule, Map<String,?> msg){
        assertThrows(ClassCastException.class, () -> rule.validate(msg));
    }


    private static Stream<Arguments> shouldReturnTrue_whenRulePassOnMsg(){
        return Stream.of(
                Arguments.of(ruleList.get(0),msgList.get(0)),
                Arguments.of(ruleList.get(0),msgList.get(1)),
                Arguments.of(ruleList.get(1),msgList.get(6)),
                Arguments.of(ruleList.get(2),msgList.get(9)),
                Arguments.of(ruleList.get(2),msgList.get(10)),
                Arguments.of(ruleList.get(4),msgList.get(18))
        );
    }

    private static Stream<Arguments> shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(){
        return Stream.of(
                Arguments.of(ruleList.get(0),msgList.get(2)),
                Arguments.of(ruleList.get(0),msgList.get(3)),
                Arguments.of(ruleList.get(1),msgList.get(7)),
                Arguments.of(ruleList.get(2),msgList.get(15)),
                Arguments.of(ruleList.get(2),msgList.get(17)),
                Arguments.of(ruleList.get(4),msgList.get(19))
        );
    }

    private static Stream<Arguments> shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(){
        return Stream.of(
                Arguments.of(ruleList.get(0),msgList.get(5)),
                Arguments.of(ruleList.get(0),msgList.get(6)),
                Arguments.of(ruleList.get(0),msgList.get(7)),
                Arguments.of(ruleList.get(0),msgList.get(8)),

                Arguments.of(ruleList.get(1),msgList.get(0)),
                Arguments.of(ruleList.get(1),msgList.get(1)),
                Arguments.of(ruleList.get(1),msgList.get(2)),
                Arguments.of(ruleList.get(1),msgList.get(3)),
                Arguments.of(ruleList.get(1),msgList.get(4)),
                Arguments.of(ruleList.get(1),msgList.get(5)),
                Arguments.of(ruleList.get(1),msgList.get(8)),

                Arguments.of(ruleList.get(2),msgList.get(0)),
                Arguments.of(ruleList.get(2),msgList.get(1)),
                Arguments.of(ruleList.get(2),msgList.get(2)),
                Arguments.of(ruleList.get(2),msgList.get(3)),
                Arguments.of(ruleList.get(2),msgList.get(4)),
                Arguments.of(ruleList.get(2),msgList.get(5)),
                Arguments.of(ruleList.get(2),msgList.get(6)),
                Arguments.of(ruleList.get(2),msgList.get(7)),
                Arguments.of(ruleList.get(2),msgList.get(8)),
                Arguments.of(ruleList.get(2),msgList.get(16))
        );
    }

    private static Stream<Arguments> shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(){
        return Stream.of(
                Arguments.of(ruleList.get(0),msgList.get(4))
        );
    }

    private static Stream<Arguments> shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(){
        return Stream.of(
                Arguments.of(ruleList.get(3),msgList.get(0))
        );
    }

    private static Stream<Arguments> shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(){
        return Stream.of(
                Arguments.of(ruleList.get(2),msgList.get(11)),
                Arguments.of(ruleList.get(2),msgList.get(12)),
                Arguments.of(ruleList.get(2),msgList.get(13)),
                Arguments.of(ruleList.get(2),msgList.get(14))
        );
    }



    @BeforeAll
    private static void loadRuleList() throws IOException, IllegalAccessException {
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
        Rule rule1 = new Rule("id1",ruleString1);
        ruleList.add(rule1);



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
        Rule rule2 = new Rule("id2",ruleString2);
        ruleList.add(rule2);

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
        Rule rule3 = new Rule("id3",ruleString3);
        ruleList.add(rule3);

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
        Rule rule4 = new Rule("id4",ruleString4);
        ruleList.add(rule4);

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
        Rule rule5 = new Rule("id4",ruleString5);
        ruleList.add(rule5);
    }

    @BeforeAll
    private static void loadMsgList(){
        GuavaMapBuilder<String, Object> mapBuilder = new GuavaMapBuilder<>();
        GuavaListBuilder<Object> listBuilder = new GuavaListBuilder<>();

        ImmutableMap<String, Object> map;

//
        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add("asd",52,"aaa",23432)
                    .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add(51,52)
                        .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                        .add("asd",53,"aaa",23432)
                        .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add("asd","adsad","aaa",23432)
                    .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add("single_value")
                    .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",mapBuilder.get()
                        .put("1",52)
                        .build())
                .build();
        msgList.add(map);

//
        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("firstName","Anshul")
                    .put("lastName","Gupta")
                    .build())
                .put("text","Bye")
                .put("group","FlockTesting")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("firstName","Anshul")
                    .put("lastName","Gupta")
                    .build())
                .put("text","Hi")
                .put("group","FlockTesting")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("from",mapBuilder.get()
                    .put("lastName","Gupta")
                    .build())
                .put("text","Bye")
                .put("group","FlockTesting")
                .build();
        msgList.add(map);

//
        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .put("name","Anshul")
                .build();
        msgList.add(map);

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
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to",mapBuilder.get()
                    .put("name","Anshul")
                    .build())
                .put("name","Anshul")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to",100)
                .put("name","Anshul")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to","name")
                .put("name","Anshul")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to",true)
                .put("name","Anshul")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .put("name","Unknown")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .add("name")
                    .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("path_to",listBuilder.get()
                    .build())
                .put("name","Anshul")
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add(1,2,"flock")
                    .build())
                .build();
        msgList.add(map);

        map = mapBuilder.get()
                .put("array",listBuilder.get()
                    .add(1,2,3)
                    .build())
                .build();
        msgList.add(map);




    }

}
