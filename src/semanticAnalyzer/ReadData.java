/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticAnalyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Sumi Mim
 */
public class ReadData {
    Scanner scan;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    /*  public ReadData() {
      System.out.println("okkk");
        connection = ConnectDB.getConnection("bangla_word_semantic");
        
        this.scan = new Scanner(new File("C:/Users/Sumi Mim/Desktop/words collection/bb/num.txt"));
        
         while(scan.hasNextLine()){
            
        String line = scan.nextLine();
            // System.out.println("line: "+line);
        boolean found = check(line);
           //  System.out.println("found: "+found);
        if(!found)// N, P, Adj, v
        {
             String sql = "INSERT INTO `bangla_word_semantic`.`table_semantic` "
                     + "(`sl`, `word`, `tag`, `animate`, `human`, `gender`, `adult`, `intelligent`, `number`, `agent`, `alive`, `honor`, "
                     + "`person`, `emphasis`, `tense`, `numerics`, `subject`, `legs`, `hands`, `wings`)"
                     + " VALUES (NULL, '"+line+"', 'Num', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1');";
             System.out.println("sql: "+sql);

        preparedStatement = connection.prepareStatement(sql);
       preparedStatement.execute();


          System.out.println("Not found: "+line);
        }
        else
         System.out.println("Found  "+line);
        //Here you can manipulate the string the way you want
     
             
       }
    }*/
    public static void WriteData(String s, String t, String g, String p, String sf, String v ) throws IOException
    {
       try {
           String input = "Input Sentence: "+s+"\r\n";
           String token ="Token:"+"\r\n"+t+"\r\n";
           String grammar = "Grammar Rule: "+"\r\n"+g+"\r\n";
           String parseTree = "Parse Tree: "+p+"\r\n";
           String semantic = "Semantic Feature: "+"\r\n"+sf+"\r\n";
            String verification = "";
            //"Varification Result: "+v+"\r\n";
           if(v.equals("sv"))
           {
               verification = "বাক্যটিতে কর্তা ও ক্রিয়ার কালের সামঞ্জস্য ঠিক নেই";
           }
           else if(v.equals("a"))
           {
               verification = "বাক্যটি অর্থগতভাবে অশুদ্ধ";
           }
          else
               verification = "বাক্যটি অর্থগতভাবে শুদ্ধ";
           
           String content = input+token+grammar+parseTree+semantic+verification;
        String filename ="C:/Users/Sumi Mim/Desktop/Report/chapter_five/resultset.txt" ;
        File file = new File(filename);
    

        // if file doesnt exists, then create it
        if (!file.exists()) {
           // System.out.println("notttttt");
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

        System.out.println("Done");
        //   RemoveDuplicate(filename);

    } catch (IOException e) {
        e.printStackTrace();
    }
     
}
    
    public boolean check(String word) throws SQLException
    {
        boolean f = false;
        String sql = "SELECT * FROM `table_semantic` WHERE tag = 'Num'";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next())
        {
                 if(word.equals(resultSet.getString("word")))
                 {
                     f = true;
                     break;
                 }
        }
        return f;
    }
    public static void RemoveDuplicate(String filename) throws FileNotFoundException, IOException
    {
         BufferedReader reader = new BufferedReader(new FileReader(filename));
    Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
    String line;
    while ((line = reader.readLine()) != null) {  
        lines.add(line);
    }
    reader.close();
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    for (String unique : lines) {
        writer.write(unique);
        writer.newLine();
    }
    writer.close();
    }
    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException
    {
        ReadData ob = new ReadData();
     //  ob.WriteData("গরু বই পড়ে","গরু -> Noun","s->SS","s(ss)","goru : Anim[2]","শুদ্ধ");
    }
}
