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
public class Sample {
    String sql ="";
    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
     int count_word_in_postagger =0;
   int count_word_in_semantics =0;
   int count_noun_p = 0 ,count_noun_s = 0, count_pronoun_p = 0, count_pronoun_s = 0,count_adjective_p = 0 ,count_adjective_s = 0, 
           count_verb_p = 0, count_verb_s = 0, count_adverb_p = 0, count_adverb_s = 0, count_postfix_p = 0,count_postfix_s = 0, 
           count_neg_p = 0,count_neg_s = 0,count_biv_p = 0,count_biv_s = 0, count_aux_p =0 , count_aux_s =0, count_conj_p=0, count_conj_s=0,
           count_subord_p = 0, count_subord_s=0, count_subcom_p=0, count_subcom_s=0, count_num_p=0, count_num_s=0,
            count_prefix_p=0, count_prefix_s=0,
           count_total_struct=0;         
   String words[] = new String[100], wordSemantics[]  = new  String[2000], wordPostagger[] = new String[500];
   String noun_p[] = new String[100],noun_s[ ]= new String[1000];
   String pronoun_p[] = new String[100],pronoun_s[] = new String[1000];
   String adjective_p[] = new String[100],adjective_s[] = new String[1000];
   String verb_p[] = new String[100],verb_s[] = new String[1000];
   String adverb_p[] = new String[100],adverb_s[] = new String[1000];
   String postfix_p[] = new String[100],postfix_s[] = new String[1000];
   String bivokti_p[] = new String[100],bivokti_s[] = new String[1000];
   String negative_p[] = new String[100], negative_s[] = new String[1000];
   String auxilliary_p[] = new String[100], auxilliary_s[] = new String[1000];
    String conjunction_p[] = new String[100], conjunction_s[] = new String[1000];
 String numeric_p[] = new String[100], numeric_s[] = new String[1000]; 
 String subord_p[] = new String[100], subord_s[] = new String[1000];
 String subcom_p[] = new String[100], subcom_s[] = new String[1000]; 
    String prefix_p[] = new String[100], prefix_s[] = new String[1000];
   public static String tag="";
   public static String animate;
   public static String human;
   public static String gender;
   public static String adult;
   public static String intelligent;
   public static String number;
   public static String agent;
   public static String alive ;
   public static String honor;
   public static String person;
   public static String emphasis;
   public static String tense ;
   public static String numerics;
   public static String subject;
   public static String legs;
   public static String hands ;
   public static String wings;
   boolean flag=false;
   int count_word_in_sentence =0;
     
    public static String holder[][] = new String [20][21]; 
    public static String rule[] = new String[20];
    public static String token ="";
     boolean first = true, last=false;
       boolean num_check = false; 
       boolean seProcheck = false;
       boolean subcom = true;
       boolean hintsComplex= false;
       boolean pro = false;
       int num_rule=0;
       int count_seperation =0;
     public Map<String, String> structure = new HashMap<String, String>();
      int c=0;
      String temWord ="";
      public static String used_rule ="", used_structure="";
      boolean simple = true, complex=false,compound=false;
      String rule_array[] = new String[20];
      int count_rule =0;
         String SS1="", SS2=""; 
    String main_rule ="";
         int h =0;
    public Sample(String s) throws SQLException {
        seperateWord(s);
        wordFromSemantics();
       // MakeHolder();
       MakeHolderList();
     
         System.out.println("Token: "+token);
         System.out.println("Used rule: "+used_rule);  
         String used_s= makeStruct();
         System.out.println("used structure: "+used_s);
        SepareteArray(used_s);
       // System.out.println("Main Rule : "+st);
   // String pt =    search_rules("S", rule_array, count_rule);
     //   System.out.println("Parse tree: "+pt);
   for(int i=0;i< count_rule;i++)
       {
            System.out.println("Rule  struct*********"+i+": "+rule_array[i]);
       }
     for(int i=0;i< num_rule;i++)
       {
            System.out.println("Rule["+i+"] "+rule[i]);
       }
     
//    String pt =    search_rules("S", rule_array, count_rule);
  //  System.out.println("Parse tree: "+pt);
       /*
          for(int i=0;i< num_rule;i++)
       {
            System.out.println("Rule *********"+i+": "+rule[i]);
       }
         */
      // used_structure = StructMap(used_rule);
    // orderSaver();
    }
   int order [] = new int[50];
     int order_counter =0;
     boolean sub = false;
     int sub_index;
     int verb_index;
     int object_index ;
     public void orderSaver()
     {
        // System.out.println("ooo");
         for(int i =0;i<order_counter;i++)
         {
             //System.out.println("ooo");
             //System.out.println("order "+i+" "+order[i]);
         }
     }
 public String makeStruct()
 {
     System.out.println("comlex:"+complex);
     System.out.println("Compound: "+compound);
     System.out.println("Simple: "+simple);
      if(complex||compound)
        {
            System.out.println("Complex: "+complex); 
            System.out.println("Compound: "+compound); 
           // System.out.println("Rule Length: "+rr.length());
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
                         //System.out.println("okkk");
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
            System.out.println("SS1: "+SS1);
            System.out.println("SS2: "+SS2);        
        }
      else
      {
          used_structure = StructMap(used_rule);
      //     System.out.println("Simple sen: "+used_structure);
        //   System.out.println("used rule: "+used_rule);
      }
      return used_structure;
 }
    public void SepareteArray(String s)
    {
        String temp ="";
       // System.out.println("separate array for "+s);
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
    }
    
 public String StructMap(String rr)
    {
       // System.out.println("rr: "+rr);
        structure.put("N V Aux",                             "S -> SS,SS -> NP VP,NP -> N,VP -> V Aux"); // 1
        structure.put("N N biv V Aux",                     "S -> SS,SS -> NP VP,NP -> N,VP -> NP VP,NP -> N biv,VP -> V Aux"); // 2
        structure.put("P N V Aux",                          "S -> SS,SS -> NP VP,NP -> P,VP -> N VP,VP -> V Aux"); // 3
        structure.put("P N biv N V Aux",                    "S -> SS,SS -> NP VP,NP -> P NP,NP -> N biv,VP -> N VP,VP -> V Aux"); //4
        structure.put("P V Aux",                              "S -> SS,SS -> NP VP,NP -> P,VP -> V Aux"); //5
        structure.put("N N V Aux",                           "S -> SS,SS -> NP VP,NP -> N,VP -> N VP,VP -> V Aux"); // 6
        structure.put("N pp Adj",                             "S -> SS,SS -> NP VP,NP -> N pp,VP -> Adj V,V -> null"); // 7
        structure.put("N pp N V Aux",                       "S -> SS,SS -> NP VP,NP -> N pp,VP -> N VP,VP -> V Aux"); // 8
        structure.put("Adj N pp N V Aux",                  "S -> SS,SS -> NP VP,NP -> Adj NP,NP -> N pp,VP -> N VP,VP -> V Aux"); // 9
        structure.put("N biv Num pp N V Aux",           "S -> SS,SS -> NP VP,NP -> N biv,VP -> NP VP,NP -> NP N,NP -> Num pp,VP -> V Aux"); //  10
        structure.put("P P N V Aux",                        "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> P N,VP -> V Aux"); // 11
        structure.put("P Adv N biv V Aux",                 "S -> SS,SS -> NP VP,NP -> P,VP -> NP VP,NP -> Adv NP,NP -> N biv,VP -> V Aux");// 12
        structure.put("Num pp N V Aux",                  "S -> SS,SS -> NP VP,NP -> Num pp,VP -> N VP,VP -> V Aux");// 13
        structure.put("N biv N V Aux",                     "S -> SS,SS -> NP VP,NP -> N biv,VP -> N VP,VP -> V Aux");// 14
        structure.put("Num pp N biv N V Aux",           "S -> SS,SS -> NP VP,NP -> Num pp,VP -> NP VP,NP -> N biv N,VP -> V Aux");// 14
        structure.put("Num pp N N V Aux",                "S -> SS,SS -> NP VP,NP -> Num pp NP,NP -> N,VP -> N VP,VP -> V Aux");//15
        structure.put("P N N V Aux",                        "S -> SS,SS -> NP VP,NP -> P N,VP -> N VP,VP -> V Aux"); // 16
         
        
    //  structure.put("subord "+SS1+" subcom "+SS2,"S -> CXS,CXS -> subord SS1 subcom SS2,SS1 -> "+SS1+",SS2 -> "+SS2);
        Set key = structure.keySet();
        Iterator itr = key.iterator();
        String r="",s="";
        
     //   System.out.println("Rullllllll: "+rr);
        while(itr.hasNext())
        {
            r = (String)itr.next();
            s = (String)structure.get(r);
          /*  if(rr.equals(r))
            {
               
                break;
             }
            */
           System.out.println("r : "+r+" s: "+s);
        }
       return s;
    }
    
     String search_rules (String t,String rule[],int n)
    {
       // System.out.println("n== "+n);
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
         //  System.out.println("tt1 : "+tt1);
            tt2="";
         //   System.out.println("tt1 length: "+tt1.length());
            for(j=0;j<tt1.length();j++)
            {
                 
                if (tt1.charAt(j)==' ')
                {
                    temp[k++]=tt2;
                    tt2="";//
                }
                else
                {tt2+=tt1.charAt(j);//System.out.println("temp: "+k+": "+temp[k]);
                }
            }
           //System.out.println(tt2+"TT2");
            temp[k++]=tt2;
          //  System.out.println("K: "+tt2);  
            if(temp[0].equals(t))
            {
                rule[i]="";
                f=1;
            }
            if (f==1)
            {
                r+="("+temp[0]+"";
             //   System.out.println("KKKKK: "+k);
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
                    
                       // System.out.println("ok");
                       
                       // System.out.println("temp["+j+"] : "+temp[j]);
        
                        r+="("+temp[j]+","+w+", "+h+")";
                        tempHolder[holder_count] = h;
                       // System.out.println("Before: "+temp[j]+" = "+holder[h][20]);
                            // System.out.println("H: "+h+" temp "+temp[j]+" and t: "+holder[h][20]);
                             
                            holder[h][20] ="t"; 
                          
                            h++;
                            holder_count++;
                    
                            
                     //   System.out.println("After******8");
                   //    System.out.println("H: "+h+" temp "+temp[j]+" and t: "+holder[h][20]);
                      
                    }
                    else if (!temp[j].equals("->"))
                        r+=search_rules(temp[j],rule,n);
                }
                r+=")";
          
               //    boolean check = checkSemantics(r,tempHolder, holder_count);
               
                return r;
            }
            //System.err.println("After  check:***********8");
              //   showHolde();
        }
        return r;
    }
   
     public boolean checkSemantics(String r, int h [],int count)
     {
         System.out.println("Count: "+count);
       
         boolean ok=true;
         System.out.println("R: "+r);
         
         for(int i=0;i<count;i++)
         {
             System.out.println("h "+i+" : "+h[i]);
             order[order_counter] = h[i];
             order_counter++;
         }
        
         for(int k =0;k<count-1;k++)
         {
             for(int j=2;j<20;j++)
             {
                 if(j==2)
                 {
                     System.out.println("tag: "+holder[h[k]][j]+" and tag: "+holder[h[k+1]][j]);
                 }
                 else
                 {   if(holder[h[k]][j].equals("-1")||holder[h[k+1]][j].equals("-1"))
                        {
                            System.out.println("Skip: "+holder[h[k]][j]+" : "+holder[h[k+1]][j]);
                            continue;
                        }       
                else
                {
                    System.out.println("Cant  skipped: "+j);
                    
                 }
                     
                 }
              
             }
         }
          //  orderSaver();
     return ok;
     
     }
    
      public void seperateWord(String s) throws SQLException
   {
       String t = "";
        System.out.println("S= "+s);
        for(int i =0;i<s.length();i++)
        {
             if((s.charAt(i)==' ')&&flag)
            {
                words[count_word_in_sentence]=t;
                String newWord = t;
                t=""; 
                count_word_in_sentence++;
                flag=false;
            }
            else if ((s.charAt(i)!=' '))
            {
                t+=s.charAt(i);
                flag=true;
            }
        }
        words[count_word_in_sentence]=t;
        count_word_in_sentence++;
   }
    
      public void wordFromSemantics() throws SQLException
{
      // System.out.println("********Words from Semantics*******");
       sql = "SELECT * FROM "+SqlQuery.Table_semantic_feature;
   //   System.out.println("sql from semantics "+sql);
       boolean f = true;
       connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
       preparedStatement = connection.prepareStatement(sql);
       resultSet = preparedStatement.executeQuery();
       String ss="";
         while(resultSet.next())
          {
          switch (resultSet.getString("tag")) {
              case "N":
                  noun_s[count_noun_s] = resultSet.getString("word");
                  count_noun_s++;
                  count_word_in_semantics++;
                  wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "P":
                  pronoun_s[count_pronoun_s] = resultSet.getString("word");
                  count_pronoun_s++;
                  count_word_in_semantics++;
                    wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Adj":
                  adjective_s[count_adjective_s] = resultSet.getString("word");
                  count_adjective_s++;
                  count_word_in_semantics++;
                    wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "V":
                  verb_s[count_verb_s] = resultSet.getString("word");
                  count_verb_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Adv":
                  adverb_s[count_adverb_s] = resultSet.getString("word");
                  count_adverb_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "pp":
                  postfix_s[count_postfix_s] = resultSet.getString("word");
                  count_postfix_s++;
                  count_word_in_semantics++;
                    wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
          //(resultSet.getString("tag").equals("Neg"))
              case "Neg":
                  negative_s[count_neg_s] = resultSet.getString("word");
                  count_neg_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "biv":
                  bivokti_s[count_biv_s] = resultSet.getString("word");
                  count_biv_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Aux":
                  auxilliary_s[count_aux_s] = resultSet.getString("word");
                  count_aux_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Conj":
                  conjunction_s[count_conj_s] = resultSet.getString("word");
                  count_conj_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
             case "Num":
                  numeric_s[count_num_s] = resultSet.getString("word");
                  count_num_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
              case "subcom":
                  subcom_s[count_subcom_s] = resultSet.getString("word");
                  count_subcom_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
             case "subord":
                  subord_s[count_subord_s] = resultSet.getString("word");
                  count_subord_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
                 case "prep":
                   prefix_s[count_prefix_s] = resultSet.getString("word");
                  count_prefix_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                    break;
               default:
                  break;
          }
         }
     /*    for(int i=0;i<count_postfix_s;i++)
         {
             System.out.println("postfix "+i+": "+postfix_s[i]);
         }
 for(int i=0;i<count_num_s;i++)
         {
             System.out.println("numeric "+i+": "+numeric_s[i]);
         }
 
         */
}
   
    public void MakeHolderList() throws SQLException
    {
      //  int count_seperation =0;
         for(int i=0;i<count_word_in_sentence;i++)
          {
                   boolean b = true;
            for(int j=0;j<count_noun_s;j++)//************Noun************//
            {
                if(words[i].equals(noun_s[j]))
                {
                    
                    selectSemanticFeatures(noun_s[j],"N");
                    SetHolder(noun_s[j], "Noun", "N");
                    count_seperation++;
                    break;

                }   
            }//*************END NOUN************//

         for(int j=0;j<count_noun_s;j++)//************Noun with POSTFIX************//
        {
            
             for(int k=0;k<count_postfix_s;k++)
             {
                 temWord = noun_s[j]+postfix_s[k];
                
                   if(words[i].equals(temWord))
                   { //System.out.println("temp: "+temWord+"and i  j k : "+i+" "+j+" "+k);
                       System.out.println("ok");
                        selectSemanticFeatures(noun_s[j],"N");
                        SetHolder(noun_s[j], "Noun","N");
                        count_seperation++;
                         selectSemanticFeatures(postfix_s[k],"pp");
                        SetHolder(postfix_s[k], "Postfix", "pp");
                        count_seperation++;
                        b= false;
                        break;
                   }  
                   
             }
             if(!b)
             {
                 break;
             }
        }//*************END NOUN with pistfix************//
             for(int j=0;j<=count_noun_s;j++)//************Noun with POSTFIX************//
          {
             for(int k=0;k<count_biv_s;k++)
             {
                 temWord = noun_s[j]+bivokti_s[k];
                // System.out.println("temp: "+temWord);
                   if(words[i].equals(temWord))
                   {
                      // System.out.println("ok");
                        selectSemanticFeatures(noun_s[j],"N");
                        SetHolder(noun_s[j], "Noun","N");
                        count_seperation++;
                         selectSemanticFeatures(bivokti_s[k],"biv");
                        SetHolder(bivokti_s[k], "Bivokti","biv");
                        count_seperation++;
                        b= false;
                        break;
                   }  
             }
             if(!b)
             {
                 break;
             }
        }//*************END NOUN with Bivokti************//
         
       
          for(int j=0;j<count_num_s;j++)//************Number with POSTFIX************//
        {
             for(int k=0;k<count_postfix_s;k++)
             {
                 temWord = numeric_s[j]+postfix_s[k];
              //   System.out.println("temp: "+temWord);
                   if(words[i].equals(temWord))
                   {
                       
                      // System.out.println("ok");
                        selectSemanticFeatures(numeric_s[j],"Num");
                        SetHolder(numeric_s[j], "Numeric","Num");
                        count_seperation++;
                         selectSemanticFeatures(postfix_s[k],"pp");
                        SetHolder(postfix_s[k], "Postfix","pp");
                        count_seperation++;
    //                    System.out.println("C: "+c);
                        
                         int n = Integer.parseInt(holder[c-1][15]); // numeric // tinti gulo
                         int pp = Integer.parseInt(holder[c-2][15]);// numeric
                         //System.out.println("N : " +n+" and pp: "+pp);
                         if(n!=-1&&pp!=-1)
                         {
                             System.out.println("error");
                         }
                         
                        num_check= true;
                       /* if(!holder[2][15].equals("-1")&&!holder[1][15].equals("-1"))
                        {
                           // System.out.println("holder: "+holder[0][0]+);
                       }
                        */
                        break;
                   }
            
                 
             }
          
        }//*************END NOUN with pistfix************//
        for(int j=0;j<count_pronoun_s;j++)//*************PRONOUN***********//
        {
            subcom = true;
            if(words[i].equals(pronoun_s[j]))
            {
               // System.out.println("seCheck: "+seProcheck);
                if(seProcheck&&words[i].equals("সে"))
                {
                    subcom = true; 
                }
              else  
                {selectSemanticFeatures(pronoun_s[j],"P");
                SetHolder(pronoun_s[j], "Pronoun","P");
                count_seperation++;
                subcom = false;
                 seProcheck = false;
                break;
                }
                }
            
        }//*****************END PRONOUN**************//
           for(int j=0;j<count_subord_s;j++)//*************SUBORD***********//
        {
            if(words[i].equals(subord_s[j]))
            {
              
                System.out.println("subord: ");
             if(subord_s[j].equals("যে"))
                {
                    seProcheck =true;
                }     
                selectSemanticFeatures(subord_s[j],"subord");
                SetHolder(subord_s[j], "Subord","subord");
               hintsComplex = true;
             //   System.out.println("complex: "+complex);
                count_seperation++;
                break;
            }
        }//*****************END SUBORD**************//
              for(int j=0;j<count_subcom_s;j++)//*************SUBCOM***********//
        {
            if(subcom&&words[i].equals(subcom_s[j]))
            {
                if(hintsComplex)
                {
                    complex = true;
                }
                selectSemanticFeatures(subcom_s[j],"subcom");
                SetHolder(subcom_s[j], "Subcom","subcom");
                count_seperation++; 
                break;
            }
        }//*****************END SUBCOM**************//
        
         for(int j=0;j<count_adjective_s;j++)//*************ADJECTIVE***********//
        {
            if(words[i].equals(adjective_s[j]))
            {
                selectSemanticFeatures(adjective_s[j],"Adj");
                SetHolder(adjective_s[j], "Adjective","Adj");
                count_seperation++;
                break;
            }
        }//*****************END ADJECTIVE**************//
         
          for(int j=0;j<count_verb_s;j++)//*************VERB***********//
        {
            if(words[i].equals(verb_s[j]))
            {
                selectSemanticFeatures(verb_s[j],"V");
                SetHolder(verb_s[j], "Verb","V");
                count_seperation++;
                break;
                
            }
        }//*****************END VERB**************//
           for(int j=0;j<count_verb_s;j++)//*************VERB***********//
        {
               for(int k=0;k<count_aux_s;k++)
               {
                   temWord = verb_s[j]+auxilliary_s[k];
                   if(words[i].equals(temWord))
                   {
                       
                       selectSemanticFeatures(verb_s[j],"V");
                        SetHolder(verb_s[j], "Verb","V");
                        count_seperation++;
                       selectSemanticFeatures(auxilliary_s[k],"Aux");
                        SetHolder(auxilliary_s[k], "Auxilliary","Aux");
                        count_seperation++;
                        b= false;
                        break;
                   }
               }
               if(!b)
               {
                   break;
               }
        }//*****************END VERB**************//
         
                for(int j=0;j<count_prefix_s;j++)//*************Prefix***********//
                {
                    if(words[i].equals(prefix_s[j]))
                    {
                        selectSemanticFeatures(prefix_s[j],"prep");
                        SetHolder(prefix_s[j], "Prefix","prep");
                        count_seperation++;
                        break;
                    }
                }//*****************END Prefix**************//
                
                 for(int j=0;j<count_conj_s;j++)//*************Prefix***********//
                {
                    if(words[i].equals(conjunction_s[j]))
                    {
                        selectSemanticFeatures(conjunction_s[j],"Conj");
                        SetHolder(conjunction_s[j], "Conjunction","Conj");
                        compound = true;
                        count_seperation++;
                        break;
                    }
                }//*****************END Prefix**************//
                  for(int j=0;j<count_adverb_s;j++)//*************Prefix***********//
                {
                    if(words[i].equals(adverb_s[j]))
                    {
                        selectSemanticFeatures(adverb_s[j],"Adv");
                        SetHolder(adverb_s[j], "Adverb","Adv");
                        count_seperation++;
                        break;
                    }
                }//*****************END Prefix**************//
                  
                   for(int j=0;j<count_biv_s;j++)//*************Prefix***********//
                {
                    if(words[i].equals(bivokti_s[j]))
                    {
                        selectSemanticFeatures(bivokti_s[j],"biv");
                        SetHolder(bivokti_s[j], "Bivokti","biv");
                        count_seperation++;
                        break;
                    }
                }//*****************END Prefix**************//
           
          
    }
         System.err.println("Befor check:***********");
         showHolde();
        
         
    }
    
    public void showHolde()
    {
         for(int j=0;j<count_seperation;j++)
              {
                  for(int k=0;k<21 ;k++)
                  {
                      
                          System.out.println("J "+j+" k "+k+"=  "+  holder[j][k]);
                     
                  }
                  
              }
         
    }
    public void SetHolder(String word, String pos, String tag)
    {
       
                holder[c][0] = word;
                holder[c][1] = pos;
                holder[c][2] = tag;
                holder[c][3] = animate;
                holder[c][4] = human;
                holder[c][5] = gender;
                holder[c][6] = adult;
                holder[c][7] = intelligent;
                holder[c][8] = number;
                holder[c][9] = agent;
                holder[c][10] = alive;
                holder[c][11] = honor;
                holder[c][12] = person;
                holder[c][13] = emphasis;
                holder[c][14] = tense;
                holder[c][15] = numerics;
                holder[c][16] = subject;
                holder[c][17] = legs;
                holder[c][18] = hands;
                holder[c][19] = wings;  
                holder[c][20] = "f";  
                if(first)
                {
                     rule[num_rule++]= tag;
                     used_rule = used_rule+tag;
                     first = false;
                } 
                else
                 
                {  rule[num_rule++]= tag;
                used_rule = used_rule+" "+tag;
                    
                }
                token = token+word+"->"+pos+"\n";
                c++;
    }
    
     public void selectSemanticFeatures(String w, String tag) throws SQLException
    {       
        sql = SqlQuery.SELECT_SEAMNTIC_FAETURE(w,tag);
    //   System.out.println("Sql: "+sql);
        connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            tag = resultSet.getString("tag");
            animate = resultSet.getString("animate");
            human = resultSet.getString("human");
            gender = resultSet.getString("gender");
            adult = resultSet.getString("adult");
            intelligent = resultSet.getString("intelligent");
            number = resultSet.getString("number");
            agent = resultSet.getString("agent");
            alive = resultSet.getString("alive");
            honor = resultSet.getString("honor");
            person = resultSet.getString("person");
            emphasis = resultSet.getString("emphasis");
            tense = resultSet.getString("tense");
            numerics = resultSet.getString("numerics");
            subject = resultSet.getString("subject");
            legs = resultSet.getString("legs");
            hands = resultSet.getString("hands");
            wings = resultSet.getString("wings");
            
       /*    System.out.println("anim: "+animate+" hum: "+human+" gender: "+gender+" adult: "+adult);
            System.out.println("Intelligent: "+intelligent+" number: "+number+" agent: "+agent +" alive: "+alive);
            System.out.println("honor: "+honor+" person: "+person+" emphasis: "+emphasis+" tense: "+tense+ " numerics: "+numerics);
            System.out.println("Subject: "+subject+" legs: "+legs+" hands: "+hands+" wings: "+wings );
*/
        }
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
      //     SyntaxAnalysis ob = new SyntaxAnalysis("মানুষরা গাড়ি চালায় আর পশুজন সভা করে");
         // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি মেয়ের বই হারিয়েছে");
          // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি মেয়ে বই হারিয়েছে");
        // SyntaxAnalysis ob = new SyntaxAnalysis("তিনগুলো মেয়ে বই হারিয়েছে");
         // SyntaxAnalysis ob = new SyntaxAnalysis("তিনটি মেয়েগুলোর বই হারিয়েছে");
           Sample ob = new Sample("আমার ভাই বিদেশ থাকেন");
      //  System.out.println( "count pp "+ob.count_postfix_s);
    }
    
}
