package com.flock.frule.api;

import com.flock.frule.TestExtension;
import com.flock.frule.model.Rule;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;
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
    private static JsonObject jsonObject1, jsonObject2, jsonObject3, jsonObject4, jsonObject5, jsonObject6, jsonObject7, jsonObject8, jsonObject9, jsonObject10,
            jsonObject11, jsonObject12, jsonObject13, jsonObject14, jsonObject15, jsonObject16, jsonObject17, jsonObject18, jsonObject19, jsonObject20;

    @ParameterizedTest
    @MethodSource
    public void shouldReturnTrue_whenRulePassOnMsg(Rule rule, JsonObject input) {
        assertTrue(rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg(Rule rule, JsonObject input) {
        assertFalse(rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg(Rule rule, JsonObject input) {
        assertThrows(NullPointerException.class, () -> rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg(Rule rule, JsonObject input) {
        assertThrows(IndexOutOfBoundsException.class, () -> rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex(Rule rule, JsonObject input) {
        assertThrows(NumberFormatException.class, () -> rule.validate(input));
    }

    @ParameterizedTest
    @MethodSource
    public void shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList(Rule rule, JsonObject input) {
        assertThrows(ClassCastException.class, () -> rule.validate(input));
    }


    private static Stream<Arguments> shouldReturnTrue_whenRulePassOnMsg() {
        return Stream.of(
                Arguments.of(rule1, jsonObject1),
                Arguments.of(rule1, jsonObject2),
                Arguments.of(rule2, jsonObject7),
                Arguments.of(rule3, jsonObject10),
                Arguments.of(rule3, jsonObject11),
                Arguments.of(rule5, jsonObject19)
        );
    }

    private static Stream<Arguments> shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg() {
        return Stream.of(
                Arguments.of(rule1, jsonObject3),
                Arguments.of(rule1, jsonObject4),
                Arguments.of(rule2, jsonObject8),
                Arguments.of(rule2, jsonObject9),
                Arguments.of(rule3, jsonObject16),
                Arguments.of(rule3, jsonObject17),
                Arguments.of(rule3, jsonObject18),
                Arguments.of(rule5, jsonObject20)
        );
    }

    private static Stream<Arguments> shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg() {
        return Stream.of(
                Arguments.of(rule1, jsonObject6),
                Arguments.of(rule1, jsonObject7),
                Arguments.of(rule1, jsonObject8),
                Arguments.of(rule1, jsonObject9),

                Arguments.of(rule2, jsonObject1),
                Arguments.of(rule2, jsonObject2),
                Arguments.of(rule2, jsonObject3),
                Arguments.of(rule2, jsonObject4),
                Arguments.of(rule2, jsonObject5),
                Arguments.of(rule2, jsonObject6),


                Arguments.of(rule3, jsonObject1),
                Arguments.of(rule3, jsonObject2),
                Arguments.of(rule3, jsonObject3),
                Arguments.of(rule3, jsonObject4),
                Arguments.of(rule3, jsonObject5),
                Arguments.of(rule3, jsonObject6),
                Arguments.of(rule3, jsonObject7),
                Arguments.of(rule3, jsonObject8),
                Arguments.of(rule3, jsonObject9)

        );
    }

    private static Stream<Arguments> shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg() {
        return Stream.of(
                Arguments.of(rule1, jsonObject5)
        );
    }

    private static Stream<Arguments> shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex() {
        return Stream.of(
                Arguments.of(rule4, jsonObject1)
        );
    }

    private static Stream<Arguments> shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList() {
        return Stream.of(
                Arguments.of(rule3, jsonObject12),
                Arguments.of(rule3, jsonObject13),
                Arguments.of(rule3, jsonObject14),
                Arguments.of(rule3, jsonObject15)
        );
    }


    @BeforeAll
    private void loadRuleList() throws IOException, IllegalAccessException {

        JsonObject ruleObjec1 = obj(
                "EQ", arr(
                        singletonListObject("PTH","array", 1),
                        obj("INT", of(52))
                )
        );
        rule1 = new Rule("id1", ruleObjec1);

        JsonObject ruleObject2 = obj(
                "AND", arr(
                        obj(
                                "EQ", arr(
                                        obj("STR", of("Anshul")),
                                        singletonListObject("PTH", "from", "firstName")

                                )
                        ),
                        obj(
                                "NOT", arr(
                                        obj(
                                                "EQ", arr(
                                                        obj("STR", of("Hi")),
                                                        singletonListObject("PTH", "text")
                                                )
                                        )
                                )
                        )
                )
        );
        rule2 = new Rule("id2", ruleObject2);

        JsonObject ruleObject3 = obj(
                "EQ", arr(
                        obj("STR", of("Anshul")),
                        obj("PTH", singletonListObject("PTH", "path_to"))
                )
        );
        rule3 = new Rule("id3", ruleObject3);

        JsonObject ruleObject4 = obj(
                "EQ", arr(
                        singletonListObject("PTH", "array", "non-numeric-index"),
                        obj("INT", of(52))
                )
        );
        rule4 = new Rule("id4", ruleObject4);

        JsonObject ruleObject5 = obj(
                "EQ", arr(
                        singletonListObject("PTH", "array"),
                        singletonListObject("JSONArr", 1, 2, "flock")
                )
        );
        rule5 = new Rule("id5", ruleObject5);

    }

    @BeforeAll
    private static void loadMsgList() {

        jsonObject1 = singletonListObject("array", "asd", 52, "aaa", 23432);

        jsonObject2 = singletonListObject("array", 51, 52);

        jsonObject3 = singletonListObject("array", "asd", 53, "aaa", 23432);

        jsonObject4 = singletonListObject("array", "asd", "adsad", "aaa", 23432);

        jsonObject5 = singletonListObject("array", "single-value");

        jsonObject6 = obj("1", of(52));

        jsonObject7 = obj(
                "from", obj(
                        "firstName", of("Anshul"),
                        "lastName", of("Gupta")
                ),
                "text", of("Bye"),
                "group", of("FlockTesting")
        );

        jsonObject8 = obj(
                "from", obj(
                        "firstName", of("Anshul"),
                        "lastName", of("Gupta")
                ),
                "text", of("Hi"),
                "group", of("FlockTesting")
        );

        jsonObject9 = obj(
                "from", obj(
                        "lastName", of("Gupta")
                ),
                "text", of("Bye"),
                "group", of("FlockTesting")
        );

        jsonObject10 = obj(
                "path_to", arr("name"),
                "name", of("Anshul")
        );

        jsonObject11 = obj(
                "path_to", arr("from", "name"),
                "from", obj("name", of("Anshul")),
                "name", of("Anshul")
        );

        jsonObject12 = obj(
                "path_to", obj("name", of("Anshul")),
                "name", of("Anshul")
        );

        jsonObject13 = obj(
                "path_to", of(100),
                "name", of("Anshul")
        );

        jsonObject14 = obj(
                "path_to", of("name"),
                "name", of("Anshul")
        );

        jsonObject15 = obj(
                "path_to", of(true),
                "name", of("Anshul")
        );

        jsonObject16 = obj(
                "path_to", arr("name"),
                "name", of("Unknown")
        );

        jsonObject17 = singletonListObject("path_to", "name");

        jsonObject18 = obj(
                "path_to", arr(),
                "name", of("Anshul")
        );

        jsonObject19 = singletonListObject("array",1, 2, "flock");

        jsonObject20 = singletonListObject("array", 1, 2, 3);

    }

    private static JsonObject singletonListObject(String key, Object ... listItems) {
        return obj(key, arr(listItems));
    }

    private static JsonArray arr(Object... entries) {
        JsonArray result = new JsonArray();
        for (Object entry : entries) {
            result.add(of(entry));
        }
        return result;
    }

    static JsonType of(Object entry) {
        if (entry instanceof JsonType) return (JsonType) entry;
        if (entry instanceof String || entry instanceof Boolean || entry instanceof Number) {
//            return JsonPrimitive.of(entry);
            return new JsonPrimitive(entry);
        }
        else
            throw new RuntimeException();
//        else if (entry instanceof Collection || entry.getClass().isArray()) {
////            return JsonArray.of(entry);
//            JsonArray jsonArray = new JsonArray();
//
//
//        }
    }

    static JsonObject obj(String k1, JsonType v1) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(k1,v1);
        return jsonObject;
    }

    static JsonObject obj(String k1, JsonType v1, String k2, JsonType v2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(k1,v1);
        jsonObject.put(k2,v2);
        return jsonObject;
    }

    static JsonObject obj(String k1, JsonType v1, String k2, JsonType v2, String k3, JsonType v3) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(k1,v1);
        jsonObject.put(k2,v2);
        jsonObject.put(k3,v3);
        return jsonObject;
    }

}
