package org.jdbc.dao;

import org.jdbc.ConnectionFactory;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class TelegramBotDao {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private void getConnection() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public Map<String, String> getAllBots(){
        Map<String, String> map = new HashMap<>();
        String queryString = "SELECT * FROM BOT;";
        try {
            executeQuery(queryString);
            while(resultSet.next()){
                map.put(resultSet.getString("NAME"),resultSet.getString("TOKEN"));
            }
        } catch (SQLException e) {
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


    public String showAllBots(){
        String queryString = "SELECT * FROM BOT;";
        StringBuffer sbf = new StringBuffer();
        try {
            executeQuery(queryString);
            while(resultSet.next()){
                sbf.append("Name: "+resultSet.getString("NAME") + "Token: "+resultSet.getString("TOKEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sbf.toString();
    }

    public String getBotByName(String name){
        String queryString = "SELECT * FROM BOT WHERE NAME=" + name + ";";
        StringBuffer sbf = new StringBuffer();
        try {
            executeQuery(queryString);
            while(resultSet.next()){
                sbf.append("Name: "+resultSet.getString("NAME") + "Token: "+resultSet.getString("TOKEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sbf.toString();
    }

    public void executeQuery(String queryString) throws SQLException {
        getConnection();
        ptmt = connection.prepareStatement(queryString);
        resultSet = ptmt.executeQuery();
    }

    public void addBot(String name, String token){
        String insertString = "INSERT INTO BOT (NAME,TOKEN) VALUES (?,?);";
        try {
            getConnection();
            ptmt = connection.prepareStatement(insertString);
            ptmt.setString(1,name);
            ptmt.setString(2,token);
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
        System.out.println("bot added into database");

    }

    public void close() throws SQLException {
        if(resultSet!=null)
            resultSet.close();
        if (ptmt != null)
            ptmt.close();
        if (connection != null)
            connection.close();

    }

//    public deleteBot()


}
