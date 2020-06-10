package com.flock.frule.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flock.frule.TestExtension;
import com.flock.frule.app.RuleApp;
import com.flock.frule.model.jsondata.JsonType;
import com.flock.frule.util.Serializer;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.inject.Inject;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
public class RulesApiTest {
    @Inject
    private RuleApp ruleApp;

    private ObjectMapper mapper = new ObjectMapper();
    private static String ruleString1, ruleString2, ruleString3, ruleString4, ruleString5;

    @ParameterizedTest
    @MethodSource("oneRuleIdAndOneRuleStringProvider")
    public void shouldReturnTrue_whenNewCorrectRuleIsAdded(String id, String ruleString) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);

        //when
        Boolean status = ruleApp.addRule(id,jsonRule);

        //then
        assertTrue(status);
        assertEquals(ruleString,ruleApp.fetchRules().get(id));

        //clean
        ruleApp.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndTwoRuleStringProvider")
    public void shouldReturnFalse_whenDuplicateCorrectRuleIsAdded(String id, String ruleString1, String ruleString2) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule1 = Serializer.fromJson(ruleString1);
        JsonType jsonRule2 = Serializer.fromJson(ruleString2);

        //when
        ruleApp.addRule(id,jsonRule1);
        Boolean status = ruleApp.addRule(id,jsonRule2);

        //then
        assertFalse(status);

        //clean
        ruleApp.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndTwoRuleStringProvider")
    public void shouldReturnTrue_whenRuleIsUpdated(String id, String ruleString1, String ruleString2) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule1 = Serializer.fromJson(ruleString1);
        JsonType jsonRule2 = Serializer.fromJson(ruleString2);

        //when
        ruleApp.addRule(id,jsonRule1);
        Boolean status = ruleApp.updateRule(id,jsonRule2);

        //then
        assertTrue(status);
        assertEquals(ruleString2,ruleApp.fetchRules().get(id));

        //clean
        ruleApp.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndOneRuleStringProvider")
    public void shouldReturnFalse_whenRuleToBeUpdatedIsNotPresent(String id, String ruleString) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);

        //when
        Boolean status = ruleApp.updateRule(id,jsonRule);

        //then
        assertFalse(status);
        assertNull(ruleApp.fetchRules().get(id));

        //clean
        ruleApp.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndOneRuleStringProvider")
    public void shouldReturnTrue_whenRuleIsDeleted(String id, String ruleString) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);

        //when
        ruleApp.addRule(id,jsonRule);
        Boolean status = ruleApp.deleteRule(id);

        //then
        assertTrue(status);
        assertNull(ruleApp.fetchRules().get(id));

        //clean
        ruleApp.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowIllegalAccessException_whenKeyInRuleIsUnknown(String id, String ruleString) throws JsonProcessingException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);

        //when_then
        assertThrows(IllegalAccessException.class, () -> ruleApp.addRule(id,jsonRule));
        assertNull(ruleApp.fetchRules().get(id));

        //clean
        ruleApp.deleteRule(id);
    }

    @Test
    public void shouldReturnFalse_whenRuleToBeDeletedIsNotPresent() {
        //given
        String id = "random";

        //when
        Boolean status = ruleApp.deleteRule(id);

        //then
        assertFalse(status);
        assertNull(ruleApp.fetchRules().get(id));
    }

    private static Stream<Arguments> oneRuleIdAndOneRuleStringProvider(){
        return Stream.of(
                Arguments.of("id",ruleString1),
                Arguments.of("id",ruleString2),
                Arguments.of("id",ruleString3)
        );
    }

    private static Stream<Arguments> oneRuleIdAndTwoRuleStringProvider(){
        return Stream.of(
                Arguments.of("id",ruleString1,ruleString2),
                Arguments.of("id",ruleString2,ruleString3),
                Arguments.of("id",ruleString3,ruleString1),
                Arguments.of("id",ruleString1,ruleString1)
        );
    }

    private static Stream<Arguments> shouldThrowIllegalAccessException_whenKeyInRuleIsUnknown(){
        return Stream.of(
                Arguments.of("id",ruleString4),
                Arguments.of("id",ruleString5)
        );
    }

    @BeforeAll
    private static void loadRuleStringList(){
        ruleString1 = JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: [arr,1]" +
                                "}," +
                                "{" +
                                    "INT: 52" +
                                "}" +
                            "]" +
                      "}").toString();

        ruleString2 =JsonParser.parseString(
                "{" +
                        "AND: [" +
                                "{" +
                                    "EQ: [" +
                                            "{" +
                                                "PTH: [from,firstName] " +
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
                                                            "PTH: [text]" +
                                                        "}," +
                                                        "{" +
                                                            "STR: Hi" +
                                                        "}" +
                                                    "]" +
                                            "}" +
                                        "]" +
                                "}" +
                            "]" +
                    "}").toString();

        ruleString3 =JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { PTH: [path_to] }" +
                                "}," +
                                "{ " +
                                    "STR: Anshul " +
                                "}" +
                            "]" +
                    "}").toString();

        ruleString4 =JsonParser.parseString(
                "{" +
                        "EQL: [" +
                                "{" +
                                    "PTH: { PTH: [path_to] }" +
                                "}," +
                                "{ " +
                                    "STR: Anshul " +
                                "}" +
                            "]" +
                    "}").toString();

        ruleString5 =JsonParser.parseString(
                "{" +
                        "EQ: [" +
                                "{" +
                                    "PTH: { PATH: [path_to] }" +
                                "}," +
                                "{ " +
                                    "STR: Anshul " +
                                "}" +
                            "]" +
                    "}").toString();

    }



}
