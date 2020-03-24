package org.example.db;


import com.google.inject.Inject;
import org.example.db.dao.RuleDao;
import org.example.model.Rule;
import org.example.model.RuleMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Singleton
public class RuleDaoImpl implements RuleDao {
    private final JdbcTemplate db;

    @Inject
    RuleDaoImpl(JdbcTemplate db) {
        this.db = db;
    }

    public Map<String, Rule> getRules(){
        Map<String, Rule> map = new HashMap<>();
        List<Rule> list = db.query("SELECT * from RULE", new RuleMapper());
        Iterator<Rule> iterator = list.iterator();
        while(iterator.hasNext()) {
            Rule rule = iterator.next();
            map.put(rule.getID(), rule);
        }
        return map;
    }

    public void addRule(Rule rule){
        String insertString = "INSERT INTO RULE (ID,JSONString) VALUES (?,?)";
        db.update(insertString,rule.getID(),rule.getRuleString());
    }

    public void deleteRule(String ruleID){
        String removeString = "DELETE FROM RULE WHERE ID = ?";
        db.update(removeString,ruleID);
    }
}
