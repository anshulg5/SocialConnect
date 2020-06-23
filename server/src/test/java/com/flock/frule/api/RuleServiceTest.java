package com.flock.frule.api;

import com.flock.frule.TestExtension;
import com.flock.frule.app.RuleService;
import com.flock.frule.model.Rule;
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
public class RuleServiceTest {
    @Inject
    private RuleService ruleService;

    // TODO: Supply rule object to tests instead of ruleString
    private static String ruleString1, ruleString2, ruleString3, ruleString4, ruleString5;

    @ParameterizedTest
    @MethodSource("oneRuleIdAndOneRuleStringProvider")
    public void shouldReturnTrue_whenNewCorrectRuleIsAdded(String id, String ruleString) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);
        Rule rule = new Rule(id,jsonRule);

        //when
        boolean status = ruleService.addRule(id,rule).getStatus();

        //then
        assertTrue(status);
        assertEquals(ruleString, ruleService.fetchRules().get(id).getRuleString());

        //clean
        ruleService.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndTwoRuleStringProvider")
    public void shouldReturnFalse_whenDuplicateCorrectRuleIsAdded(String id, String ruleString1, String ruleString2) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule1 = Serializer.fromJson(ruleString1);
        Rule rule1 = new Rule(id,jsonRule1);
        JsonType jsonRule2 = Serializer.fromJson(ruleString2);
        Rule rule2 = new Rule(id,jsonRule2);

        //when
        ruleService.addRule(id,rule1);
        boolean status = ruleService.addRule(id,rule2).getStatus();

        //then
        assertFalse(status);

        //clean
        ruleService.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndTwoRuleStringProvider")
    public void shouldReturnTrue_whenRuleIsUpdated(String id, String ruleString1, String ruleString2) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule1 = Serializer.fromJson(ruleString1);
        Rule rule1 = new Rule(id,jsonRule1);
        JsonType jsonRule2 = Serializer.fromJson(ruleString2);
        Rule rule2 = new Rule(id,jsonRule2);

        //when
        ruleService.addRule(id,rule1);
        boolean status = ruleService.updateRule(id,rule2).getStatus();

        //then
        assertTrue(status);
        assertEquals(ruleString2, ruleService.fetchRules().get(id).getRuleString());

        //clean
        ruleService.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndOneRuleStringProvider")
    public void shouldReturnFalse_whenRuleToBeUpdatedIsNotPresent(String id, String ruleString) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);
        Rule rule = new Rule(id,jsonRule);

        //when
        boolean status = ruleService.updateRule(id,rule).getStatus();

        //then
        assertFalse(status);
        assertNull(ruleService.fetchRules().get(id));

        //clean
        ruleService.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndOneRuleStringProvider")
    public void shouldReturnTrue_whenRuleIsDeleted(String id, String ruleString) throws IOException, IllegalAccessException {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);
        Rule rule = new Rule(id,jsonRule);

        //when
        ruleService.addRule(id,rule);
        boolean status = ruleService.deleteRule(id).getStatus();

        //then
        assertTrue(status);
        assertNull(ruleService.fetchRules().get(id));

        //clean
        ruleService.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowIllegalAccessException_whenKeyInRuleIsUnknown(String id, String ruleString) {
        //given
        JsonType jsonRule = Serializer.fromJson(ruleString);

        //when_then
        assertThrows(IllegalAccessException.class, () -> new Rule(id,jsonRule));
        assertNull(ruleService.fetchRules().get(id));

        //clean
        ruleService.deleteRule(id);
    }

    @Test
    public void shouldReturnFalse_whenRuleToBeDeletedIsNotPresent() {
        //given
        String id = "random";

        //when
        boolean status = ruleService.deleteRule(id).getStatus();

        //then
        assertFalse(status);
        assertNull(ruleService.fetchRules().get(id));
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
