package org.database.dao;

import org.database.ConnectionFactory;
import org.rule.Rule;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RuleDao {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private void getConnection() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public Map<String, Rule> getRules(){
        Map<String, Rule> map = new HashMap<>();
        String queryString = "SELECT * FROM RULE";
        try{
            executeQuery(queryString);
            while(resultSet.next()){
                map.put(resultSet.getString("ID"),
                        new Rule(resultSet.getString("ID"),resultSet.getString("JSONString")));
            }
        } catch (SQLException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public void addRule(Rule rule){
        String insertString = "INSERT INTO RULE (ID,JSONString) VALUES (?,?);";
        try{
            getConnection();
            ptmt = connection.prepareStatement(insertString);
            ptmt.setString(1,rule.getID());
            ptmt.setString(2,rule.getJsonString());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("rule added into database");
    }

    public void deleteRule(String ruleID){
        String removeString = "DELETE FROM RULE WHERE ID = '"+ruleID+"';";
        try{
            getConnection();
            ptmt = connection.prepareStatement(removeString);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeQuery(String queryString) throws SQLException {
        getConnection();
        ptmt = connection.prepareStatement(queryString);
        resultSet = ptmt.executeQuery();
    }

    private void close() throws SQLException {
        if(resultSet!=null)
            resultSet.close();
        if (ptmt != null)
            ptmt.close();
        if (connection != null)
            connection.close();
    }


}
