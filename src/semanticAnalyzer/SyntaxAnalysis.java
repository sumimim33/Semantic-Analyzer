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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Sumi Mim
 */
public class SyntaxAnalysis {

   String holder[][];
    public SyntaxAnalysis(String holder[][]) {
        this.holder = holder;
    }
    
    
    
    
 public  String makeStruct(boolean simple,boolean complex, boolean compound,int num_rule, String srule, String[] rule)
 {
   
     String used_structure="";
     System.out.println("comlex:"+complex);
     System.out.println("Compound: "+compound);
     System.out.println("Simple: "+simple);
     System.out.println("Used::::::::::::::: "+srule);
     for(int i=0;i<num_rule;i++)
     {
         System.out.println("rule["+i+"]"+rule[i]);
     }
    String SS1 ="";
    String SS2="";
      if(complex||compound)
        {
            String temp ="";
            boolean sc= false, s1= false, s2 = false;
            int ii =0;
            if(complex)
                ii=1;
            for(int i=ii;i<num_rule;i++)
            {
                if(i==ii||sc)
                {
                    s1= true;
                }
                if(rule[i].equals("subcom")||rule[i].equals("Conj"))
                {
                  sc = true;
                  s2 = true;
                }
                else if(!sc)
                {
                    if(s1)
                    {
                         SS1 = SS1+rule[i];
                         s1=false;
                    }
                    else
                    {
                         SS1 =SS1+" "+rule[i]; 
                       
                    }
                }
                else 
                {
                    if(s2)
                    {
                        SS2 =SS2+rule[i];
                        s2=false;
                    }
                    else
                    {
                        SS2 =SS2+" "+rule[i];
                    }
                }
                    
            }
           
           String ss11= StructMap(SS1);
          String ss21=  StructMap(SS2);
          if(complex)
          used_structure = "S -> CXS,CXS -> subord SS1 subcom SS2,SS1 -> S,"+ss11+",SS2 -> S,"+ss21;
          else if(compound)
              used_structure = "S -> CMS,CMS -> SS1 Conj SS2,SS1 -> S,"+ss11+",SS2 -> S,"+ss21;
          //  System.out.println("SS1: "+SS1);
           // System.out.println("SS2: "+SS2);    
            
        }
      else
      {
          used_structure = StructMap(srule);
         //  System.out.println("Simple sentence rule: "+used_structure);
          //  System.out.println("Used rule rule: "+used_rule);
      }
      
      return used_structure;
 }
    public  String[] SepareteStructure(String s)
    {
          String rule_array[] = new String[20];
        int  count_rule=0;
        String temp ="";
         for(int i=0;i<s.length();i++)
                {
                    if(s.charAt(i)!=',')
                    {
                          temp= temp+s.charAt(i);
                          continue;
                    }
                    else 
                    {
                        rule_array[count_rule++]= temp; 
                        temp="";
                    }
                }
                    rule_array[count_rule++]= temp; 
                    return (rule_array);
    }
    
  /*  boolean mappp(String rule)
    {
        boolean found = false;
      String  mapp[][] = {
        {"N V Aux",                               "S -> SS,SS -> NP VP,NP -> N,VP -> V Aux"},//1
        {"P V Aux",                            "S -> SS,SS -> NP VP,NP -> P,VP -> V Aux"},//1  // goru pore
        
        {"N N V Aux",                           "S -> SS,SS -> NP VP,NP -> N,VP -> N VP,VP -> V Aux"}, // 2
        {"P N V Aux",                           "S -> SS,SS -> NP VP,NP -> P,VP -> N VP,VP -> V Aux"}, // 6 ami namaz por
        {"N P V Aux",                           "S -> SS,SS -> NP VP,NP -> N,VP -> P VP,VP -> V Aux"}, // 6 // goru boi poRe
        {"P P V Aux",                           "S -> SS,SS -> NP VP,NP -> P,VP -> P VP,VP -> V Aux"}, // 6/ i
        
         {"N P N V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> P N,VP -> V Aux"}, // 3 goru amder dudh dey
         {"P P N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux"}, // 2
         {"N N N V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N N,VP -> V Aux"}, // 2
         {"P N N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N N,VP -> V Aux"}, // 2 tar ma ranna kore
         
          {"P Adj N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> V Aux"}, // 4 tar ma ranna kore
           {"P Adj N Neg",                      "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> null Neg"}, // 4 tar ma ranna kore
          {"N Adj N V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N N,VP -> V Aux"}, // 2 tar ma ranna kore
         
         
        {"N N biv V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N biv,VP -> V Aux"}, // 2
        {"P N biv V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N biv,VP -> V Aux"}, // 2
         
        {"P N biv N V Aux",                    "S -> SS,SS -> NP VP,NP -> P NP,NP -> N biv,VP -> N VP,VP -> V Aux"},//4
        {"P V Aux",                              "S -> SS,SS -> NP VP,NP -> P,VP -> V Aux"}, //5
      
        {"N pp Adj",                             "S -> SS,SS -> NP VP,NP -> N pp,VP -> Adj V,V -> null"}, // 7
        {"N pp N V Aux",                       "S -> SS,SS -> NP VP,NP -> N pp,VP -> N VP,VP -> V Aux"}, // 8
        {"Adj N pp N V Aux",                  "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N pp,VP -> N VP,VP -> V Aux"}, // 9
        {"N biv Num pp N V Aux",           "S -> SS,SS -> NP VP,NP -> N biv,VP -> NP VP,NP -> NP N,NP -> Num pp,VP -> V Aux"}, //  10
        {"P P N V Aux",                        "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux"}, // 11
        {"P Adv N biv V Aux",                 "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adv NP,NP -> N biv,VP -> V Aux"},// 12
        {"Num pp N V Aux",                  "S -> SS,SS -> NP VP,NP -> Num pp,VP -> N VP,VP -> V Aux"},// 13
        {"N biv N V Aux",                     "S -> SS,SS -> NP VP,NP -> N biv,VP -> N VP,VP -> V Aux"},// 14
        {"Num pp N biv N V Aux",           "S -> SS,SS -> NP VP,NP -> Num pp,VP -> NP VP,NP -> N biv N,VP -> V Aux"},// 14
        {"Num pp N N V Aux",                "S -> SS,SS -> NP VP,NP -> Num pp NP,NP -> N,VP -> N VP,VP -> V Aux"},//15
        {"P N N V Aux",                        "S -> SS,SS -> NP VP,NP -> P N,VP -> N VP,VP -> V Aux"} ,// 16
     
              
      };
        for(int i=0;i<mapp.length;i++)
        {
            for(int j=0;j<2;j++)
            {
               String sss = mapp[i][j];
                     found =  StringMatc(rule,sss);
                     System.out.println("found:::: "+found);
                     if(found)
                     {
                         return found;
                     }
            }

        }      
        System.out.println("mapp called with "+rule);
        return  found;
    }
   
   boolean StringMatc(String st1, String st2)
    {
        boolean f=false;
     int len1=st1.length();
        int len2=st2.length();
        int y=1;
        if(len1==len2){
            char ch1=st1.charAt(0);
            char ch2=st2.charAt(0);
            int con1=ch1 , con2=ch2;
            if(con1==con2){
                for(int x=1;x<len1;x++){
                    char Ch1=st1.charAt(x);
                    char Ch2=st2.charAt(x);
                    if(Ch1==Ch2){
                        y++;
                    }
                }
            }
        }
        if(y==len1){
            f=true;
        }
        else if(y!=len1){
           f=false;
        }
        return f;
    }
   */
    
 public String StructMap(String rule)
    {
      boolean found = false;
      String structure ="";
   String  mapp[][] = {
       {"N pp N biv V Aux","S -> SS,SS -> NP VP,NP -> N pp,VP -> NP VP,NP -> N biv,VP -> V Aux"},
       {"P P biv N V Aux","S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P biv NP,NP -> N,VP -> V Aux"},
       {"N pp N biv","S -> SS,SS -> NP VP,NP -> N pp,VP -> NP VP,NP -> N biv,VP -> V Aux"},
       {"N Neg V Aux", "S -> SS,SS -> NP VP,NP -> N,VP -> Neg VP,VP -> V Aux"},
       {"Adj V Aux","S -> SS,SS -> NP VP,NP -> null,VP ->NP VP,NP -> Adj,VP -> V Aux"},
       {"Num pp N N biv V Aux","S -> SS,SS -> NP VP,NP -> Num pp NP,NP -> N,VP -> NP VP,NP -> N biv,VP -> V Aux"},
       
       {"Adj N V Aux","S -> SS,SS -> NP VP,NP -> Adj N,VP -> V Aux"},
       {"P P N V Aux","S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux"},
       {"P N Adj Adj","S -> SS,SS -> NP VP,NP -> P N,VP -> NP VP,NP -> Adj Adj,VP -> null"},
       {"P N biv Adj V Aux","S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N biv,VP -> Adj VP,VP -> V Aux"},
       {"P N N biv V Aux", "S -> SS,SS -> NP VP,NP -> P N,VP -> NP VP,NP -> N biv,VP -> V Aux"},
       {"P P V Aux Neg","S -> SS,SS -> NP VP,NP -> P,VP -> P VP,VP -> V Aux Neg"},
       {"N pp P V Aux","S -> SS,SS -> NP VP,NP -> N pp,VP -> P VP,VP -> V Aux" },
       {"Adj N","S -> SS,SS -> NP VP,NP -> Adj N,VP -> null"},
       {"P biv N biv N","S -> SS,SS -> NP VP,NP -> P biv NP,NP -> N biv NP,NP -> N,VP -> null"},
       {"N biv N Adj N V Aux","S -> SS,SS -> NP VP,NP -> N biv NP,NP -> N,VP -> NP VP,NP -> Adj N,VP -> V Aux"},
       {"Adj N biv Adj","S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N biv,VP -> Adj VP,VP -> null"},
       {"N biv N biv Adj N V Aux","S -> SS,SS -> NP VP,NP -> N biv NP,NP -> N biv,VP -> NP VP,NP -> Adj N,VP -> V Aux"},
       {"Adv N V Aux","S -> SS,SS -> NP VP,NP -> Adv N,VP -> V Aux"},
         { "Adv P N biv V Aux",  "S -> SS,SS -> NP VP,NP -> Adv P,VP -> NP VP,NP -> N biv,VP -> V Aux"},
          {"P P Adv V Aux","S -> SS,SS -> NP VP,NP -> P,VP -> P VP,VP -> Adv VP,VP -> V Aux"},
       
          { "P P N biv V Aux",  "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P NP,NP -> N biv,VP -> V Aux"},
         {"Adj N biv N biv P V Aux Neg",          "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N biv NP,NP -> N biv NP,NP -> P,VP -> V Aux Neg"} ,// 16
        {"N Adj",                               "S -> SS,SS -> NP VP,NP -> N Adj,VP -> null"},//1
        {"N Adj Adj",                          "S -> SS,SS -> NP VP,NP -> Nj,VP -> null VP,VP -> Adj Adj"},//1
        
        {"P Adj",                               "S -> SS,SS -> NP VP,NP -> P Adj,VP -> null"},//1
        {"P Adj Adj",                               "S -> SS,SS -> NP VP,NP -> P,VP -> null VP,VP -> Adj Adj"},//1
        {"N V Aux",                               "S -> SS,SS -> NP VP,NP -> N,VP -> V Aux"},//1
        {"N V Aux Neg",                               "S -> SS,SS -> NP VP,NP -> N,VP -> V Aux Neg"},//1
        {"P V Aux",                            "S -> SS,SS -> NP VP,NP -> P,VP -> V Aux"},//1  // goru pore
         {"P V Aux Neg",                            "S -> SS,SS -> NP VP,NP -> P,VP -> V Aux Neg"},//1  // goru pore
        
        {"N N V Aux",                           "S -> SS,SS -> NP VP,NP -> N,VP -> N VP,VP -> V Aux"}, // 2
        {"N N Neg",                           "S -> SS,SS -> NP VP,NP -> N,VP -> N VP,VP -> null Neg"}, // 2
        {"N N V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> N,VP -> N VP,VP -> V Aux Neg"}, // 2
        {"P N V Aux",                           "S -> SS,SS -> NP VP,NP -> P,VP -> N VP,VP -> V Aux"}, // 6 ami namaz por
        {"P N Neg",                           "S -> SS,SS -> NP VP,NP -> P,VP -> N VP,VP -> null Neg"}, // 6 ami namaz por
        {"P N V Aux Neg",                           "S -> SS,SS -> NP VP,NP -> P,VP -> N VP,VP -> V Aux Neg"}, // 6 ami namaz por
        {"N P V Aux",                           "S -> SS,SS -> NP VP,NP -> N,VP -> P VP,VP -> V Aux"}, // 6 // goru boi poRe
         {"N P V Aux Neg",                           "S -> SS,SS -> NP VP,NP -> N,VP -> P VP,VP -> V Aux Neg"}, // 6 // goru boi poRe
        {"P P V Aux",                           "S -> SS,SS -> NP VP,NP -> P,VP -> P VP,VP -> V Aux"}, // 6/ se take valobase
        
         {"N P N V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> P N,VP -> V Aux"}, // 3 goru amder dudh dey
         {"N P N V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> P N,VP -> V Aux Neg"}, // 3 goru amder dudh dey
         {"P P N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux"}, // 2
         {"P P N V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux Neg"}, // 2
         {"N N N V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N N,VP -> V Aux"}, // 2
         {"N N N V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N N,VP -> V Aux Neg"}, // 2

         {"P N N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N N,VP -> V Aux"}, // 2 tar ma ranna kore
         {"P N N V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N N,VP -> V Aux Neg"}, // 2 tar ma ranna kore
         
          {"P Adj N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> V Aux"}, // 4 tar valo jama ache
          {"P N Neg",                          "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N,VP -> null Neg"}, // 4 tar  jama nei
          {"P Adj N Neg",                      "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> null Neg"}, // 4 tar valo jama lei
          {"P Adj N V Aux",                    "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> V Aux"}, // 2 se valo jama pore
          {"P N V Aux Neg",                   "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N N,VP -> V Aux Neg"}, // 2 se valo jama pore
          {"P Adj N V Aux Neg",               "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> V Aux Neg"}, // se valo jama pore na
          
          {"P Adj V Aux",                    "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj,VP -> V Aux"}, // 2 se valo jama pore
          {"N Adj V Aux",                    "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> Adj,VP -> V Aux"}, // 2 se valo jama pore
          
           {"P Adj V Aux Neg",                    "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj,VP -> V Aux Neg"}, // 2 se valo jama pore
          {"N Adj V Aux Neg",                    "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> Adj,VP -> V Aux Neg"}, // 2 se valo jama pore
          
          {"N biv Adj N V Aux",                   "S -> SS,SS -> NP VP,NP -> N biv,VP -> NP VP,NP -> Adj N,VP -> V Aux"}, // 2 gorur valo jama ache
          {"N biv Adj N Neg",                     "S -> SS,SS -> NP VP,NP -> N biv,VP -> NP VP,NP -> Adj N,VP -> null Neg"}, // 2 gorur valo jama nei
          {"N Adj N V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> Adj N,VP -> V Aux"}, // 2 goru valo khabar khai
          {"N Adj N V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> Adj N,VP -> V Aux Neg"}, // 2 goru valo khabar khai
          {"P Adj N V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adj N,VP -> V Aux"}, // 2 goru valo khabar khai
          {"P Adj N V Aux Neg",               "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N N,VP -> V Aux Neg"}, // 2 goru valo khabar khai na
          
         {"Adj N P Adv V Aux Neg",           "S -> SS,SS -> NP VP,NP -> Adj N,VP -> NP VP,NP -> P Adv,VP -> V Aux Neg"}, // Mithaa kotha ami pasondo kori na 
         {"Adj N N Adv V Aux Neg",           "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N P,VP -> Adv VP,VP -> V Aux Neg"}, // 2 goru valo khabar khai na
         {"Adj N P Adv V Aux",           "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N P,VP -> Adv VP,VP -> V Aux"}, // 2 goru valo khabar khai na
         {"Adj N N Adv V Aux",           "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N N,VP -> Adv VP,VP -> V Aux"}, // 2 goru valo khabar khai na
         
        {"N N biv V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N biv,VP -> V Aux"}, // 2// se patre khai
        {"P N biv V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> N biv,VP -> V Aux"}, // 2
        
         {"P Adv V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> P,VP -> Adv VP,VP -> V Aux Neg"}, // 2
           {"P Adv V Aux",                     "S -> SS,SS -> NP VP,NP -> P,VP -> Adv VP,VP -> V Aux"}, // 2
        {"N Adv V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> N,VP -> Adv VP,VP -> V Aux Neg"}, // 2
        
         {"P Adv Adv V Aux Neg",            "S -> SS,SS -> NP VP,NP -> P,VP -> Adv VP,VP -> Adv VP,VP -> V Aux Neg"}, // 2 se valo vabe raga ragi kor na
        {"N Adv V Aux Neg",                     "S -> SS,SS -> NP VP,NP -> N,VP -> Adv VP,VP -> Adv VP,VP -> V Aux Neg"}, // 2
        {"P Adv Adv V Aux",            "S -> SS,SS -> NP VP,NP -> P,VP -> Adv VP,VP -> Adv VP,VP -> V Aux"}, // 2
        {"N Adv V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> Adv VP,VP -> Adv VP,VP -> V Aux"}, // 2
        
        {"P N biv N V Aux",                    "S -> SS,SS -> NP VP,NP -> P NP,NP -> N biv,VP -> N VP,VP -> V Aux"},//4
        {"P V Aux",                              "S -> SS,SS -> NP VP,NP -> P,VP -> V Aux"}, //5
      
        {"N pp Adj",                             "S -> SS,SS -> NP VP,NP -> N pp,VP -> Adj V,V -> null"}, // 7
        {"N pp N V Aux",                       "S -> SS,SS -> NP VP,NP -> N pp,VP -> N VP,VP -> V Aux"}, // 8
        {"Adj N pp N V Aux",                  "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N pp,VP -> N VP,VP -> V Aux"}, // 9
        {"N biv Num pp N V Aux",           "S -> SS,SS -> NP VP,NP -> N biv,VP -> NP VP,NP -> NP N,NP -> Num pp,VP -> V Aux"}, //  10
        {"P P N V Aux",                        "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux"}, // 11
        {"P Adv N biv V Aux",                 "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adv NP,NP -> N biv,VP -> V Aux"},// 12
        {"Num pp N V Aux",                  "S -> SS,SS -> NP VP,NP -> Num pp,VP -> N VP,VP -> V Aux"},// 13
        {"N biv N V Aux",                     "S -> SS,SS -> NP VP,NP -> N biv,VP -> N VP,VP -> V Aux"},// 14
        {"Num pp N biv N V Aux",           "S -> SS,SS -> NP VP,NP -> Num pp,VP -> NP VP,NP -> N biv N,VP -> V Aux"},// 14
        {"Num pp N N V Aux",                "S -> SS,SS -> NP VP,NP -> Num pp NP,NP -> N,VP -> N VP,VP -> V Aux"},//15
         {"Num pp N pp N V Aux",           "S -> SS,SS -> NP VP,NP -> Num pp NP,NP -> N pp,VP -> N VP,VP -> V Aux"},//15// tinti meyegulo gan kore
           {"Num pp N pp N V Aux",           "S -> SS,SS -> NP VP,NP -> Num pp NP,NP -> N pp,VP -> N VP,VP -> V Aux"},//15
        {"P N N V Aux",                        "S -> SS,SS -> NP VP,NP -> P N,VP -> N VP,VP -> V Aux"} ,// 16
         {"N Adv N biv V Aux",               "S -> SS,SS -> NP VP,NP -> N,VP -> Adv VP,VP -> NP VP,NP -> N biv,VP -> V Aux"} ,// 16
        
              
      };
        for(int i=0;i<mapp.length;i++)
        {
            for(int j=0;j<2;j++)
            {
                if(j==0)
                {
                    String sss = mapp[i][j];
                     if(rule.equals(sss))
                     {
                         structure = mapp[i][j+1];
                         return structure;
                     }
                }
            }
        }    
      return structure;
    }
    
 int h=0;
   public String search_rules (String t,String rule[],int n)
    {
     //   System.out.println("n: "+n);
       /* for(int i=0;i<n;i++)
        {
            System.out.println("rule["+i+"]"+rule[i]);
        }
*/
        int sendH[] = new int [10];
        int sh =0;
        int i,k=0,j,f=0;
        String temp[]=new String[100],tt1,tt2,r="";
        int tempHolder[] = new int [10];
        int holder_count= 0;
     
        for (i=0;i<n;i++)
        {
            k=0;
            f=0;
            tt1=rule[i];
            tt2="";
            for(j=0;j<tt1.length();j++)
            {
                 
                if (tt1.charAt(j)==' ')
                {
                    temp[k++]=tt2;
                    tt2="";
                }
                else
                {tt2+=tt1.charAt(j);
                }
            }
            temp[k++]=tt2;
      
            if(temp[0].equals(t))
            {
                rule[i]="";
                f=1;
            }
            if (f==1)
            {
                r+="("+temp[0]+"";
                for(j=1;j<k;j++)
                {
                    
                    String tag = holder[h][2];
                    String w = holder[h][0];  
                    
                      if(temp[j].equals("null"))
                    {
                           r+="(V,null)";
                    }
                    
              else   if(temp[j].equals(tag))
                    {
                        r+="("+temp[j]+","+w+")";
                        tempHolder[holder_count] = h;
                            holder[h][20] ="t"; 
                            h++;
                            holder_count++;
                    }
                    else if (!temp[j].equals("->"))
                        r+=search_rules(temp[j],rule,n);
                }
                r+=")"; 
                return r;
            }
        }
        return r;
    }
   
     
    
    public static void main(String args[]) throws SQLException
    {
  //  SyntaxAnalysis ob = new SyntaxAnalysis("গরুটি বই পড়ে");
   //  SyntaxAnalysis ob = new SyntaxAnalysis("সুন্দর মেয়েটি বই পড়ে");
    //  SyntaxAnalysis ob = new SyntaxAnalysis("মানুষের চারটি পা আছে");
   //   SyntaxAnalysis ob = new SyntaxAnalysis("সে বই পড়ে");
      // SyntaxAnalysis ob = new SyntaxAnalysis("যে বই পড়ে সে ভালো করে");
   //   SyntaxAnalysis ob = new SyntaxAnalysis("যদি সে বই পড়ে তাহলে সে পরীক্ষায় পাস করবে");
     // SyntaxAnalysis ob = new SyntaxAnalysis("যদি তুমি বই পড়ো তাহলে তুমি ভালো করবে");
    //   SyntaxAnalysis ob = new SyntaxAnalysis("ছেলেটি রুপবান এবং মেয়েটি রূপসী");
    //   SyntaxAnalysis ob = new SyntaxAnalysis("ছেলেটি রূপসী এবং মেয়েটি রুপবান");
     //    SyntaxAnalysis ob = new SyntaxAnalysis("আমি আগামীকাল গিয়েছিলাম");
        // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি ফুলগুলো ফুটেছে");
      //   SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি সুন্দর মেয়ে আমাকে দেখতে এসেছে");
        // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি সুন্দর মেয়ে আমার জন্য ফুল এনেছে");
        // SyntaxAnalysis ob = new SyntaxAnalysis("পাখি ডাকে");
       // SyntaxAnalysis ob = new SyntaxAnalysis("সে বই পড়ে এবং আমি ঘুমাই");
      // SyntaxAnalysis ob = new SyntaxAnalysis("তিনি আমাকে চকলেট দিয়েছিল");
        // SyntaxAnalysis ob = new SyntaxAnalysis("তিনগুলো ফুল ফুটেছে");
       //  SyntaxAnalysis ob = new SyntaxAnalysis("মানুষের ডানা আছে");
        // SyntaxAnalysis ob = new SyntaxAnalysis("যদি তুমি বই পড়ো তাহলে আমি বই পড়ো");
       //  SyntaxAnalysis ob = new SyntaxAnalysis("যখন আমি বাড়ি যাই তখন সে ভাত খাই");
      //     SyntaxAnalysis ob = new SyntaxAnalysis("মানুষরা গাড়ি চালায় আর পশুজন সভা কSরে");
         // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি মেয়ের বই হারিয়েছে");
          // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি মেয়ে বই হারিয়েছে");
        // SyntaxAnalysis ob = new SyntaxAnalysis("তিনগুলো মেয়ে বই হারিয়েছে");
         // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি মেয়েগুলোর বই হারিয়েছে");
          // SyntaxAnalysis ob = new SyntaxAnalysis("আমার ভাই বিদেশ থাকেন");
      //  System.out.println( "count pp "+ob.count_postfix_s);
    }
    
}
