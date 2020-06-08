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

    private static Rule rule1, rule2, rule3, rule4, rule5, rule6;
    private static JsonObject input1, input2, input3, input4, input5, input6, input7, input8, input9, input10,
            input11, input12, input13, input14, input15, input16, input17, input18, input19, input20;

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

//    @ParameterizedTest
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
                Arguments.of(rule1, input1),
                Arguments.of(rule1, input2),
                Arguments.of(rule2, input7),
                Arguments.of(rule3, input10),
                Arguments.of(rule3, input11),
                Arguments.of(rule5, input19)
        );
    }

    private static Stream<Arguments> shouldReturnFalse_whenSimilarStructureOfRuleAndMsg_and_whenRuleFailsOnMsg() {
        return Stream.of(
                Arguments.of(rule1, input3),
                Arguments.of(rule1, input4),
                Arguments.of(rule2, input8),
                Arguments.of(rule2, input9),
                Arguments.of(rule3, input16),
                Arguments.of(rule3, input17),
                Arguments.of(rule3, input18),
                Arguments.of(rule5, input20),

                // earlier threw NullPointer Exception

                Arguments.of(rule1, input6),
                Arguments.of(rule1, input7),
                Arguments.of(rule1, input8),
                Arguments.of(rule1, input9),

                Arguments.of(rule2, input1),
                Arguments.of(rule2, input2),
                Arguments.of(rule2, input3),
                Arguments.of(rule2, input4),
                Arguments.of(rule2, input5),
                Arguments.of(rule2, input6),


                Arguments.of(rule3, input1),
                Arguments.of(rule3, input2),
                Arguments.of(rule3, input3),
                Arguments.of(rule3, input4),
                Arguments.of(rule3, input5),
                Arguments.of(rule3, input6),
                Arguments.of(rule3, input7),
                Arguments.of(rule3, input8),
                Arguments.of(rule3, input9),

                Arguments.of(rule6, input1)
        );
    }

    private static Stream<Arguments> shouldThrowNullPointerException_whenKeyInRuleIsNotFoundInMsg() {
        return Stream.of(


        );
    }

    private static Stream<Arguments> shouldThrowIndexOutOfBoundsException_whenOutOfRangeIndexInListIsAccessedInMsg() {
        return Stream.of(
                Arguments.of(rule1, input5)
        );
    }

    private static Stream<Arguments> shouldThrowNumberFormatException_whenListInMsgGetsANonNumericIndex() {
        return Stream.of(
                Arguments.of(rule4, input1)
        );
    }

    private static Stream<Arguments> shouldThrowClassCastException_whenInnerPathInRuleDoesNotReturnList() {
        return Stream.of(
                Arguments.of(rule3, input12),
                Arguments.of(rule3, input13),
                Arguments.of(rule3, input14),
                Arguments.of(rule3, input15)
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

        JsonObject ruleObject6 = obj(
                "EQ", arr(
                        singletonListObject("PTH", "invalid-key"),
                        singletonListObject("PTH", "invalid-key")
                )
        );
        rule6 = new Rule("id6", ruleObject6);

    }

    @BeforeAll
    private static void loadMsgList() {

        input1 = singletonListObject("array", "asd", 52, "aaa", 23432);

        input2 = singletonListObject("array", 51, 52);

        input3 = singletonListObject("array", "asd", 53, "aaa", 23432);

        input4 = singletonListObject("array", "asd", "adsad", "aaa", 23432);

        input5 = singletonListObject("array", "single-value");

        input6 = obj("1", of(52));

        input7 = obj(
                "from", obj(
                        "firstName", of("Anshul"),
                        "lastName", of("Gupta")
                ),
                "text", of("Bye"),
                "group", of("FlockTesting")
        );

        input8 = obj(
                "from", obj(
                        "firstName", of("Anshul"),
                        "lastName", of("Gupta")
                ),
                "text", of("Hi"),
                "group", of("FlockTesting")
        );

        input9 = obj(
                "from", obj(
                        "lastName", of("Gupta")
                ),
                "text", of("Bye"),
                "group", of("FlockTesting")
        );

        input10 = obj(
                "path_to", arr("name"),
                "name", of("Anshul")
        );

        input11 = obj(
                "path_to", arr("from", "name"),
                "from", obj("name", of("Anshul")),
                "name", of("Anshul")
        );

        input12 = obj(
                "path_to", obj("name", of("Anshul")),
                "name", of("Anshul")
        );

        input13 = obj(
                "path_to", of(100),
                "name", of("Anshul")
        );

        input14 = obj(
                "path_to", of("name"),
                "name", of("Anshul")
        );

        input15 = obj(
                "path_to", of(true),
                "name", of("Anshul")
        );

        input16 = obj(
                "path_to", arr("name"),
                "name", of("Unknown")
        );

        input17 = singletonListObject("path_to", "name");

        input18 = obj(
                "path_to", arr(),
                "name", of("Anshul")
        );

        input19 = singletonListObject("array",1, 2, "flock");

        input20 = singletonListObject("array", 1, 2, 3);

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
        JsonObject input = new JsonObject();
        input.put(k1,v1);
        return input;
    }

    static JsonObject obj(String k1, JsonType v1, String k2, JsonType v2) {
        JsonObject input = new JsonObject();
        input.put(k1,v1);
        input.put(k2,v2);
        return input;
    }

    static JsonObject obj(String k1, JsonType v1, String k2, JsonType v2, String k3, JsonType v3) {
        JsonObject input = new JsonObject();
        input.put(k1,v1);
        input.put(k2,v2);
        input.put(k3,v3);
        return input;
    }

}
