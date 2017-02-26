package semanticAnalyzer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import database.ConnectDB;
import database.SqlQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sumi
 */
public class SemanticAnalysis 
{    
    public static String Semantic_Feature(int count_total_word,String holder[][])
    {
        String semantic_feature = "";
          for(int j=0;j<count_total_word;j++)
              {
                  String w="", pos="", line="";
                  for(int k=0;k<20 ;k++)
                  {
                      if(k==0)
                      {
                         w = holder[j][k];
                         line = line+"  "+w+": ";
                          
                      }
                      else if(k==3)
                      {
                          line = line+" Ani["+holder[j][k]+"]"; 
                      }
                      else if(k==4)
                      {
                          line = line+",Hum["+holder[j][k]+"]"; 
                      }
                      else if(k==5)
                      {
                          line = line+",Gen["+holder[j][k]+"]"; 
                      }
                      else if(k==6)
                      {
                          line = line+",Adlt["+holder[j][k]+"]"; 
                      }
                      else if(k==7)
                      {
                          line = line+",Inteli["+holder[j][k]+"]"; 
                      }
                      else if(k==8)
                      {
                          line = line+",Num["+holder[j][k]+"]"; 
                      }
                      else if(k==9)
                      {
                          line = line+",Agn["+holder[j][k]+"]"; 
                      }
                      else if(k==10)
                      {
                          line = line+",Alive["+holder[j][k]+"]"; 
                      }
                      else if(k==11)
                      {
                          line = line+",Hon["+holder[j][k]+"]"; 
                      }
                      else if(k==12)
                      {
                          line = line+",Per["+holder[j][k]+"]"; 
                      }
                      else if(k==13)
                      {
                          line = line+",Emp["+holder[j][k]+"]"; 
                      }
                      else if(k==14)
                      {
                          line = line+",Ten["+holder[j][k]+"]"; 
                      }
                      else if(k==15)
                      {
                          line = line+",Numrs["+holder[j][k]+"]"; 
                      }
                      else if(k==16)
                      {
                          line = line+",Sub["+holder[j][k]+"]"; 
                      }
                      else if(k==17)
                      {
                          line = line+",Leg["+holder[j][k]+"]"; 
                      }
                      else if(k==18)
                      {
                          line = line+",Hand["+holder[j][k]+"]"; 
                      }
                      else if(k==19)
                      {
                          line = line+",Wing["+holder[j][k]+"]"; 
                      }
                     
                  }
                  semantic_feature = semantic_feature+ line+"\n";
   
              }
         return semantic_feature;
    }
    
    public static String abc(String s)
    {
        String b;
        b= Test2.found(s);
        System.out.println("bbbb: "+b);
        return b;
    }
    
    
   public static void main(String[] args)
   {
       
   }
}
