package org.example.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.TestExtension;
import org.example.rule.RuleApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestExtension.class)
public class RulesApi {
    @Inject
    public RuleApp ruleApp;

    @AfterEach
    void clearDb(){
        ruleApp.deleteAllRules();
    }

    @Test
    public void shouldReturnTrue_whenNewCorrectRuleIsAdded() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //given
        String id = "random";
        String ruleString = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        Boolean status = ruleApp.addRule(id,rule);

        //then
        assertTrue(status);
        assertEquals(rule,ruleApp.fetchRuleMapById(id));
    }

    @Test
    public void shouldReturnFalse_whenDuplicateCorrectRuleIsAdded() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //given
        String id = "random";
        String ruleString = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        ruleApp.addRule(id,rule);
        Boolean status = ruleApp.addRule(id,rule);

        //then
        assertFalse(status);
    }

    @Test
    public void shouldReturnTrue_whenRuleIsUpdated() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //given
        String id = "random";
        String ruleString = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";
        String newRuleString = "{\"STR\":\"Anshul\"}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        Map<String,Object> newRule = (Map<String, Object>) mapper.readValue(newRuleString,new TypeReference<Map<String,Object>>(){});

        //when
        ruleApp.addRule(id,rule);
        Boolean status = ruleApp.updateRule(id,newRule);

        //then
        assertTrue(status);
        assertEquals(newRule,ruleApp.fetchRuleMapById(id));
    }

    @Test
    public void shouldReturnFalse_whenRuleToBeUpdatedIsNotPresent() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //given
        String id = "random";
        String ruleString = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        Boolean status = ruleApp.updateRule(id,rule);

        //then
        assertFalse(status);
        assertNull(ruleApp.fetchRuleMapById(id));
    }
    
    @Test
    public void shouldReturnTrue_whenRuleIsDeleted() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //given
        String id = "random";
        String ruleString = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> rule = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});

        //when
        ruleApp.addRule(id,rule);
        Boolean status = ruleApp.deleteRule(id);

        //then
        assertTrue(status);
        assertNull(ruleApp.fetchRuleMapById(id));
    }

    @Test
    public void shouldReturnFalse_whenRuleToBeDeletedIsNotPresent() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //given
        String id = "random";

        //when
        Boolean status = ruleApp.deleteRule(id);

        //then
        assertFalse(status);
        assertNull(ruleApp.fetchRuleMapById(id));
    }


}
