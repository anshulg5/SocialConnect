package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import org.example.TestExtension;
import org.example.app.RuleApp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;
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
        Map<String,Object> rule = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        Boolean status = ruleApp.addRule(id,rule);

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
        Map<String,Object> rule1 = mapper.readValue(ruleString1,new TypeReference<Map<String,Object>>(){});
        Map<String,Object> rule2 = mapper.readValue(ruleString2,new TypeReference<Map<String,Object>>(){});

        //when
        ruleApp.addRule(id,rule1);
        Boolean status = ruleApp.addRule(id,rule2);

        //then
        assertFalse(status);

        //clean
        ruleApp.deleteRule(id);
    }

    @ParameterizedTest
    @MethodSource("oneRuleIdAndTwoRuleStringProvider")
    public void shouldReturnTrue_whenRuleIsUpdated(String id, String ruleString1, String ruleString2) throws IOException, IllegalAccessException {
        //given
        Map<String,Object> rule = mapper.readValue(ruleString1,new TypeReference<Map<String,Object>>(){});
        Map<String,Object> newRule = mapper.readValue(ruleString2,new TypeReference<Map<String,Object>>(){});

        //when
        ruleApp.addRule(id,rule);
        Boolean status = ruleApp.updateRule(id,newRule);

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
        Map<String,Object> rule = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        Boolean status = ruleApp.updateRule(id,rule);

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
        Map<String,Object> rule = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        ruleApp.addRule(id,rule);
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
        Map<String,Object> rule = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when_then
        assertThrows(IllegalAccessException.class, () -> ruleApp.addRule(id,rule));
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
                                    "PTH: { STRLIST: [arr,1] }" +
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
                                                            "PTH: { STRLIST: [text] } " +
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
                                    "PTH: { PTH: { STRLIST: [path_to] } }" +
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
                                    "PTH: { PTH: { STRLIST: [path_to] } }" +
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
                                    "PTH: { PATH: { STRLIST: [path_to] } }" +
                                "}," +
                                "{ " +
                                    "STR: Anshul " +
                                "}" +
                            "]" +
                    "}").toString();

    }



}
