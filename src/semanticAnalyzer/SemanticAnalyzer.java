/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticAnalyzer;

import database.ConnectDB;
import database.SqlQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sumi Mim
 */
public class SemanticAnalyzer {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public static String word ="";
   public static String Col_semantic_pos ="pos";
   public static String tag="";
   
   public static int animate;
   public static int human;
   public static int gender;
   public static int _adult;
   public static int intelligent;
   public static int number;
   public static int agent;
   public static int alive ;
   public static int honor;
   public static int person;
   public static int emphasis;
   public static int tense ;
   public static int numerics;
   public static int subject;
   public static int legs;
   public static int hands ;
   public static int wings;
    public SemanticAnalyzer(String s, String tag) {
        
        
    }
    
    public void selcetSemanticFeatures(String sql) throws SQLException
    {
        connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            animate = Integer.parseInt(resultSet.getString("animate"));
            human = Integer.parseInt(resultSet.getString("human"));
            gender = Integer.parseInt(resultSet.getString("gender"));
            
            
        }
        
        
    }    
    
    public static void main(String[] args)
    {
        SemanticAnalyzer ob = new SemanticAnalyzer("তিন","Num");
    }
    
}
