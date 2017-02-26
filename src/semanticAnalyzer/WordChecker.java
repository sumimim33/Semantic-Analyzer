package semanticAnalyzer;

import database.ConnectDB;
import database.SqlQuery;
import java.sql.*;

public class WordChecker {
    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
   public static String Col_semantic_word="";
   public static String Col_semantic_animate="";
   public static String Col_semantic_human ="";
   public static String Col_semantic_gender="";
   public static String Col_semantic_adult ="";
   public static String Col_semantic_intelligent="";
   public static String Col_semantic_number="";
   public static String Col_semantic_agent="";
   public static String Col_semantic_alive ="";
   public static String Col_semantic_honor="";
   public static String Col_semantic_person="";
   public static String Col_semantic_emphasis="";
   public static String Col_semantic_tense ="";
   public static String Col_semantic_numerics="";
   public static String Col_semantic_subject ="";
   public static String Col_semantic_legs="";
   public static String Col_semantic_hands ="";
   public static String Col_semantic_wings="";
   public static String sql ="";
   public static String  Col_posTagger_tag ="";
   public static boolean wordFound = true;
    public static String input;
   boolean flag=false;
   boolean found = false;
   int count_word_in_sentence =0;
   int count_word_in_postagger =0;
   int count_word_in_semantics =0;
   int count_noun_p = 0 ,count_noun_s = 0, count_pronoun_p = 0, count_pronoun_s = 0,count_adjective_p = 0 ,count_adjective_s = 0, 
           count_verb_p = 0, count_verb_s = 0, count_adverb_p = 0, count_adverb_s = 0, count_postfix_p = 0,count_postfix_s = 0, 
           count_neg_p = 0,count_neg_s = 0,count_biv_p = 0,count_biv_s = 0, count_aux_p =0 , count_aux_s =0, count_conj_p=0, count_conj_s=0,
           count_M_p = 0, count_M_s=0, count_Mm_p=0, count_Mm_s=0, count_num_p=0, count_num_s=0,
           count_total_struct=0;         
   String words[] = new String[100], wordSemantics[]  = new  String[500], wordPostagger[] = new String[500];
   String noun_p[] = new String[100],noun_s[ ]= new String[100];
   String pronoun_p[] = new String[100],pronoun_s[] = new String[100];
   String adjective_p[] = new String[100],adjective_s[] = new String[100];
   String verb_p[] = new String[100],verb_s[] = new String[100];
   String adverb_p[] = new String[100],adverb_s[] = new String[100];
   String postfix_p[] = new String[100],postfix_s[] = new String[100];
   String bivokti_p[] = new String[100],bivokti_s[] = new String[100];
   String negative_p[] = new String[100], negative_s[] = new String[100];
   String auxilliary_p[] = new String[100], auxilliary_s[] = new String[100];
    String conjunction_p[] = new String[100], conjunction_s[] = new String[100];
 String numeric_p[] = new String[100], numeric_s[] = new String[100]; 
 String marker_p[] = new String[100], marker_s[] = new String[100];
 String marker2_p[] = new String[100], marker2_s[] = new String[100]; 
 
 String struct[] = new String[100];
 String rule_applied[] = new String[100];
 
   public WordChecker(String s) throws SQLException {
        input=s;
        seperateWord(input);
        wordFromSemantics();
        wordFromPosTagger();
        wordFound();
        String t="";
        String ww ="";
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
   
public void wordFromPosTagger() throws SQLException
{
  //  System.out.println("********Words from PosTagger*******");
       sql = "SELECT * FROM "+SqlQuery.Table_pos_tagger;
    //   System.out.println("Sql from tagger : "+sql);
       boolean f = true;
       connection = ConnectDB.getConnection(SqlQuery.DB_pos_tagger);
       preparedStatement = connection.prepareStatement(sql);
       resultSet = preparedStatement.executeQuery();
   
         while(resultSet.next())
          {
        switch (resultSet.getString("tag")) {
            case "N":
                noun_p[count_noun_p] = resultSet.getString("word");
                count_noun_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "P":
                pronoun_p[count_pronoun_p] = resultSet.getString("word");
                count_pronoun_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "Adj":
                adjective_p[count_adjective_p] = resultSet.getString("word");
                count_adjective_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "V":
                verb_p[count_verb_p] = resultSet.getString("word");
                count_verb_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "Adv":
                adjective_p[count_adverb_p] = resultSet.getString("word");
                count_adverb_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "pp":
                postfix_p[count_postfix_p] = resultSet.getString("word");
                count_postfix_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
        //(resultSet.getString("tag").equals("Neg"))
            case "Neg":
                negative_p[count_neg_p] = resultSet.getString("word");
                count_neg_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "biv":
                bivokti_p[count_biv_p] = resultSet.getString("word");
                count_biv_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;
            case "Aux":
                auxilliary_p[count_aux_p] = resultSet.getString("word");
                count_aux_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;   
             case "Conj":
                conjunction_p[count_conj_p] = resultSet.getString("word");
                count_conj_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;  
             case "Num":
                numeric_p[count_num_p] = resultSet.getString("word");
                count_num_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;  
                 case "M":
                marker_p[count_M_p] = resultSet.getString("word");
                count_M_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;  
                 case "Mm":
                marker2_p[count_Mm_p] = resultSet.getString("word");
                count_Mm_p++;
                wordPostagger[count_word_in_postagger] = resultSet.getString("word");
                count_word_in_postagger++;
                break;  
              
           default:
               break;
        }
              wordPostagger[count_word_in_postagger] = resultSet.getString("word");
              count_word_in_postagger++;

         }
         
     /*   for(int i=0;i<count_noun_p;i++)
         {
             System.out.println("Word "+(i+1)+": "+noun_p[i]);
             
         }
         
      */            
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
                  adjective_s[count_adverb_s] = resultSet.getString("word");
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
              case "M":
                  marker_s[count_M_s] = resultSet.getString("word");
                  count_M_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
             case "Mm":
                  marker2_s[count_Mm_s] = resultSet.getString("word");
                  count_Mm_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
               default:
                  break;
          }
         }
 
}

public void wordFound() throws SQLException
{
  
     for(int c=0;c<count_word_in_sentence;c++)
     {
           boolean f = false;
        boolean nn=false,p = false, adj = false,v= false, adv = false, neg=false, num= false, conj=false, marker=false, marker2=false;
           for(int j=0;j<count_noun_s;j++)
          {
               if(words[c].equals(noun_s[j]))
              { 
                  System.out.println("ok "+ noun_s[j]);
                  nn = true;
                  break;
              }
               
          }
           for(int j=0;j<count_pronoun_s;j++)
          {
               if(words[c].equals(pronoun_s[j]))
              { 
                  System.out.println("ok : "+pronoun_s[j]);
                   p = true;
                  break;
                  
              }
               
          }
                 for(int j=0;j<count_adjective_s;j++)
          {
               if(words[c].equals(adjective_s[j]))
              { 
                  System.out.println("ok : "+ adjective_s[j]);
                   adj = true;
                  break;
              }
               
          }
                  for(int j=0;j<count_verb_s;j++)
          {
               if(words[c].equals(verb_s[j]))
              { 
                  System.out.println("ok : "+verb_s[j]);
                   v = true;
                  break;
              }
               
          }
             for(int j=0;j<count_adverb_s;j++)
          {
               if(words[c].equals(adverb_s[j]))
              { 
                  System.out.println("ok : "+adverb_s[j]);
                   adv = true;
                  break;
              }
               
          }
            
                for(int j=0;j<count_neg_s;j++)
          {
               if(words[c].equals(negative_s[j]))
              { 
                  System.out.println("ok : "+negative_s[j]);
                   neg = true;
                  break;
              }
               
          }
                
                 for(int j=0;j<count_num_s;j++)
          {
               if(words[c].equals(numeric_s[j]))
              { 
                  System.out.println("ok "+ numeric_s[j]);
                  num = true;
                  break;
              }
               
          }
                 
                  for(int j=0;j<count_conj_s;j++)
          {
               if(words[c].equals(conjunction_s[j]))
              { 
                  System.out.println("ok "+ conjunction_s[j]);
                  conj = true;
                  break;
              }
               
          }
                   for(int j=0;j<count_M_s;j++)
          {
               if(words[c].equals(marker_s[j]))
              { 
                  System.out.println("ok "+ marker_s[j]);
                  marker = true;
                  break;
              }
               
          }
                   
                    for(int j=0;j<count_Mm_s;j++)
          {
               if(words[c].equals(marker2_s[j]))
              { 
                  System.out.println("ok "+ marker2_s[j]);
                  marker2 = true;
                  break;
              }
               
          }
            for(int j=0;j<count_noun_s;j++) //******** 1 1 *********//
          {
                boolean b = false;
              for(int k=0;k<count_postfix_s;k++)
              {
                  String ws = noun_s[j]+postfix_s[k];
                  if(words[c].equals(ws))
                  {   b = true;
                      break;
                  } 
              
              }
          }
                     for(int l=0;l<count_noun_p;l++) // *********** noun 1 0 || 0 1 *************//
                      {
                          boolean f1=  true,f2=true;
                          boolean a = false,b=false;
                          for(int m=0;m<count_postfix_p;m++)
                          {
                               String wp = noun_p[l]+postfix_p[m]; 
                               if(words[c].equals(wp))
                               {
                                   
                                   for(int n =0;n<count_noun_s;n++)
                                   {
                                       if(noun_p[l].equals(noun_s[n]))
                                       {   f1 = false;
                                           break;
                                       }
                                   }
                                    for(int o =0;o<count_postfix_s;o++)
                                   {
                                       if(postfix_p[m].equals(postfix_s[o]))
                                       {   f2 = false;
                                           break;
                                       }
   
                                   }
                                  if(f1&&f2)
                                  {
                                      System.out.println("insert: "+ noun_p[l]+ "  and insert : "+postfix_p[m]);
                                      selectSeamticFeatures(noun_p[l]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      selectSeamticFeatures(postfix_p[m]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      break;
                          
                                  }
                                   if (f2)
                                  {
                                      System.out.println("Insert : "+postfix_p[m]);
                                      
                                       selectSeamticFeatures(postfix_p[m]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      break;
                                      
                                  }     
                                  if(f1)
                                  {
                                      System.out.println("Insert : "+noun_p[l]);
                                      
                                       selectSeamticFeatures(noun_p[l]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      break;
                                  }
                            }
                              
                         
                      }
              } //************ 10 || 01 of noun  *************//       
                     // others
                     
              for(int j=0;j<count_noun_s;j++) //********  biv1 1 *********//
          {
                boolean b = false;
              for(int k=0;k<count_biv_s;k++)
              {
                  String ws = noun_s[j]+bivokti_s[k];
                  if(words[c].equals(ws))
                  {   b = true;
                      break;
                  } 
              
              }
          }
                     for(int l=0;l<count_noun_p;l++) // *********** noun 1 0 || 0 1 *************//
                      {
                          boolean f1=  true,f2=true;
                          boolean a = false,b=false;
                          for(int m=0;m<count_biv_p;m++)
                          {
                               String wp = noun_p[l]+bivokti_p[m]; 
                               if(words[c].equals(wp))
                               {
                                   
                                   for(int n =0;n<count_noun_s;n++)
                                   {
                                       if(noun_p[l].equals(noun_s[n]))
                                       {   f1 = false;
                                           break;
                                       }
                                   }
                                    for(int o =0;o<count_biv_s;o++)
                                   {
                                       if(bivokti_p[m].equals(bivokti_s[o]))
                                       {   f2 = false;
                                           break;
                                       }
                                   }
                                  if(f1&&f2)
                                  {     System.out.println("insert: "+ noun_p[l]+ "  and insert : "+bivokti_p[m]);
                                       selectSeamticFeatures(noun_p[l]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                       selectSeamticFeatures(bivokti_p[m]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                 
                                      break;
                          
                                  }
                                   if (f2)
                                  {
                                      
                                      System.out.println("Insert : "+bivokti_p[m]);
                                       selectSeamticFeatures(bivokti_p[m]);
                                     insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                       
                                      break;
                                      
                                  }     
                                  if(f1)
                                  {
                                      System.out.println("Insert : "+noun_p[l]);
                                       selectSeamticFeatures(noun_p[l]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      break;
                                  }
                            }    
                      }
              } //************ 10 || 01 of biv noun  *************//  
                     
          for(int j=0;j<count_verb_s;j++) //*****verb*****//
          {
                  //System.out.println("V : "+verb_s[j]);
               if(words[c].equals(verb_s[j]))
              { 
                  break;
              }
          }
            for(int j=0;j<count_verb_s;j++) //******** 1 1 *********//
          {
                boolean b = false;
              for(int k=0;k<count_aux_s;k++)
              {
                  String ws = verb_s[j]+auxilliary_s[k];
                  if(words[c].equals(ws))
                  {   b = true;
                      break;
                  } 
              
              }
          }  
            
             for(int l=0;l<count_verb_p;l++) // *********** verb 1 0 || 0 1 *************//
                      {
                          boolean f1=  true,f2=true;
                        
                          for(int m=0;m<count_aux_p;m++)
                          {
                               String wp = verb_p[l]+auxilliary_p[m]; 
                              // System.out.println("wp "+wp);
                               if(words[c].equals(wp))
                               {
                                   System.out.println("word "+wp);
                                 
                                   for(int n =0;n<count_verb_s;n++)
                                   {
                                       if(verb_p[l].equals(verb_s[n]))
                                       {   f1 = false;
                                           break;
                                       }
                                   }
                                    for(int o =0;o<count_aux_s;o++)
                                   {
                                       if(auxilliary_p[m].equals(auxilliary_s[o]))
                                       {   f2 = false;
                                           break;
                                       }
                                   }
                                  if(f1&&f2)
                                  {
                                      System.out.println("insert: "+ verb_p[l]+ "  and insert : "+auxilliary_p[m]);
                                       selectSeamticFeatures(verb_p[l]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                       selectSeamticFeatures(auxilliary_p[m]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      break;
                          
                                  }
                                   if (f2)
                                  {System.out.println("Insert : "+auxilliary_p[m]);
                                      selectSeamticFeatures(auxilliary_p[m]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      
                                      break;
                                      
                                  }     
                                  if(f1)
                                  {
                                      System.out.println("Insert : "+verb_p[l]);
                                      selectSeamticFeatures(verb_p[l]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                      break;
                                  }
                                 
                            }    
                      }
              } //************ 10 || 01 of biv noun  *************//  
             //******** only one word check  **********//
             if(!nn)
             {
                   for(int j=0;j<count_noun_p;j++)
                    {
                         if(words[c].equals(noun_p[j]))
                        { 
                            System.out.println("Insert : "+noun_p[j]);
                            selectSeamticFeatures(noun_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                            break;
                        }

                    }
             }
             if(!p)
             {
                 for(int j=0;j<count_pronoun_p;j++)
                {
                     if(words[c].equals(pronoun_p[j]))
                    { 
                        System.out.println("Insert : "+pronoun_p[j]);
                        
                         selectSeamticFeatures(pronoun_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                        
                        break;
                    }

                }
             }
                if(!adj)
                {
                           for(int j=0;j<count_adjective_p;j++)
                            {
                                 if(words[c].equals(adjective_p[j]))
                                { 
                                    System.out.println("Insert : "+ adjective_p[j]);
                                     selectSeamticFeatures(adjective_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                    break;
                                }

                            }
                }
          if(!v)
          {
               for(int j=0;j<count_verb_p;j++)
                {
                     if(words[c].equals(verb_p[j]))
                    { 
                        
                        System.out.println("Insert : "+verb_p[j]);
                         selectSeamticFeatures(verb_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                        break;
                    }

                }
          }
                 if(!adv)
                 {
                       for(int j=0;j<count_adverb_p;j++)
                        {
                             if(words[c].equals(adverb_p[j]))
                            { 
                                System.out.println("Insert : "+adverb_p[j]);
                                 selectSeamticFeatures(adverb_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                                break;
                            }

                        }
                 }
           
             
               if(!neg)
               {
                     for(int j=0;j<count_neg_p;j++)
                    {
                         if(words[c].equals(negative_p[j]))
                        { 
                            System.out.println("Insert : "+negative_p[j]);
                             selectSeamticFeatures(negative_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                            break;
                        }

                    }
               }
               if(!num)
               {
                     for(int j=0;j<count_num_p;j++)
                    {
                         if(words[c].equals(numeric_p[j]))
                        { 
                            System.out.println("Insert : "+numeric_p[j]);
                             selectSeamticFeatures(numeric_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                            break;
                        }

                    }
               }
               if(!conj)
               {
                     for(int j=0;j<count_conj_p;j++)
                    {
                         if(words[c].equals(conjunction_p[j]))
                        { 
                            System.out.println("Insert : "+conjunction_p[j]);
                             selectSeamticFeatures(conjunction_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                            break;
                        }

                    }
               }
               if(!marker)
               {
                     for(int j=0;j<count_M_p;j++)
                    {
                         if(words[c].equals(marker_p[j]))
                        { 
                            System.out.println("Insert : "+marker_p[j]);
                             selectSeamticFeatures(marker_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                            break;
                        }

                    }
               }
               if(!marker2)
               {
                     for(int j=0;j<count_Mm_p;j++)
                    {
                         if(words[c].equals(marker2_p[j]))
                        { 
                            
                            System.out.println("Insert : "+marker2_p[j]);
                             selectSeamticFeatures(marker2_p[j]);
                                      insertNewWord(Col_semantic_word, Col_semantic_animate, Col_semantic_human, Col_semantic_gender,Col_semantic_alive,
                                              Col_semantic_agent, Col_semantic_honor, Col_semantic_intelligent, Col_semantic_adult, Col_semantic_legs, Col_semantic_hands,
                                              Col_semantic_wings, Col_semantic_person, Col_semantic_number, Col_semantic_numerics, Col_semantic_tense, 
                                              Col_semantic_subject,Col_semantic_emphasis, Col_posTagger_tag);
                            break;
                        }

                    }
               }
              
     } // ************ end of for ************//
     
     
}
    
public void insertNewWord(String word, String anim, String hum, String gender,String alive, String agent, String honor, 
        String intelligent, String adult, String legs, String hands, String wings,String person,String number, String numeric, String tense, String subject, 
        String emphasis,String tag ) throws SQLException
{
 sql ="INSERT INTO `"+SqlQuery.DB_semantic_feature+"`.`"+SqlQuery.Table_semantic_feature+"` "
         + "(`sl`, `word`, `tag`, `animate`, `human`, `gender`, `adult`, `intelligent`, `number`, `agent`, `alive`, `honor`, `person`, `emphasis`, `tense`, "
         + "`numerics`, `subject`, `legs`, `hands`, `wings`) "
         + "VALUES (NULL, '"+word+"', '"+tag+"', '"+anim+"', '"+hum+"', '"+gender+"', '"+adult+"', '"+intelligent+"', "
         + "'"+number+"', '"+agent+"', '"+alive+"', '"+honor+"', '"+person+"', '"+emphasis+"', '"+tense+"',"
         + " '"+numeric+"', '"+subject+"', '"+legs+"', '"+hands+"', '"+wings+"');";
    connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
    preparedStatement = connection.prepareStatement(sql);
    preparedStatement.execute();    
}
public void selectSeamticFeatures(String w) throws SQLException
{
sql = "SELECT * "
        + "FROM `"+SqlQuery.Table_pos_tagger+"`"
        + " WHERE `word` ='"+w+"'";
connection = ConnectDB.getConnection(SqlQuery.DB_pos_tagger);
preparedStatement = connection.prepareStatement(sql);
resultSet = preparedStatement.executeQuery();

     while(resultSet.next())
          {
              Col_posTagger_tag = resultSet.getString("tag");
              Col_semantic_word = resultSet.getString("word");
              Col_semantic_animate = resultSet.getString("animate");
              Col_semantic_human = resultSet.getString("human");
              Col_semantic_gender = resultSet.getString("gender");
              Col_semantic_alive = resultSet.getString("alive");
              Col_semantic_agent = resultSet.getString("agent");
              Col_semantic_honor = resultSet.getString("honor");
              Col_semantic_intelligent = resultSet.getString("intelligent");
              Col_semantic_adult = resultSet.getString("adult");
              Col_semantic_legs = resultSet.getString("legs");
              Col_semantic_hands = resultSet.getString("hands");
              Col_semantic_wings = resultSet.getString("wings");
              Col_semantic_person = resultSet.getString("person");
              Col_semantic_number = resultSet.getString("number");
              Col_semantic_emphasis = resultSet.getString("emphasis");
              Col_semantic_numerics = resultSet.getString("numerics");
              Col_semantic_subject = resultSet.getString("subject");
              Col_semantic_tense = resultSet.getString("tense");
             // System.out.println("tag :"+Col_posTagger_tag);
             // System.out.println("anim:"+Col_semantic_animate);
          }
    
}

    public static void main(String[] args) throws SQLException
    {
     //  WordChecker w = new  WordChecker("খা");
    }
}
