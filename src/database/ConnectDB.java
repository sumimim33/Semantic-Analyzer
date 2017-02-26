/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sumi Mim
 */
public class ConnectDB {
   
   public static Connection getConnection(String dbName) throws SQLException
    {
       Connection con = null;
        
    try{
      
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";    
    String url = "jdbc:mysql://localhost:3306/"+dbName;
      //  System.out.println("url"+url);
        con = DriverManager.getConnection(url+unicode,"root","");
      //  JOptionPane.showMessageDialog(null, "Connection Succeed");
        
    }
   catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, ex);
             return null;
        }
       return con;
    
    }
    
}
