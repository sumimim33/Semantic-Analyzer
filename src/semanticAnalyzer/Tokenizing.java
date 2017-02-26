package semanticAnalyzer;

import database.ConnectDB;
import database.SqlQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sumi Mim
 */
public class Tokenizing {
    protected String s  ="jjjjj";
  public static boolean first_time_run = false;
   static  Connection connection = null;
 static   PreparedStatement preparedStatement;
  static  ResultSet resultSet;
   static  int count_word_in_postagger =0;
static   int count_word_in_semantics =0;
 static  int count_noun_p = 0 ,count_noun_s = 0, count_pronoun_p = 0, count_pronoun_s = 0,count_adjective_p = 0 ,count_adjective_s = 0, 
           count_verb_p = 0, count_verb_s = 0, count_adverb_p = 0, count_adverb_s = 0, count_postfix_p = 0,count_postfix_s = 0, 
           count_neg_p = 0,count_neg_s = 0,count_biv_p = 0,count_biv_s = 0, count_aux_p =0 , count_aux_s =0, count_conj_p=0, count_conj_s=0,
           count_subord_p = 0, count_subord_s=0, count_subcom_p=0, count_subcom_s=0, count_num_p=0, count_num_s=0,
            count_prefix_p=0, count_prefix_s=0,
           count_total_struct=0;         
 static  String words[] = new String[100], wordSemantics[]  = new  String[2000], wordPostagger[] = new String[500];
 static  String noun_p[] = new String[100],noun_s[ ]= new String[1000];
 static  String pronoun_p[] = new String[100],pronoun_s[] = new String[1000];
 static String adjective_p[] = new String[100],adjective_s[] = new String[1000];
 static  String verb_p[] = new String[100],verb_s[] = new String[1000];
 static  String adverb_p[] = new String[100],adverb_s[] = new String[1000];
 static  String postfix_p[] = new String[100],postfix_s[] = new String[1000];
 static  String bivokti_p[] = new String[100],bivokti_s[] = new String[1000];
 static  String negative_p[] = new String[100], negative_s[] = new String[1000];
 static  String auxilliary_p[] = new String[100], auxilliary_s[] = new String[1000];
 static   String conjunction_p[] = new String[100], conjunction_s[] = new String[1000];
 static String numeric_p[] = new String[100], numeric_s[] = new String[1000]; 
 static String subord_p[] = new String[100], subord_s[] = new String[1000];
 static String subcom_p[] = new String[100], subcom_s[] = new String[1000]; 
  static  String prefix_p[] = new String[100], prefix_s[] = new String[1000];
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
static   boolean flag=false;
   static int count_word_in_sentence =0;
     
   static  String holder[][] = new String [20][21]; 
    
    public static String rule[] = new String[20];
    public static String token;
   static  boolean first = true, last=false;
   static    boolean num_check = false, seProcheck = false, subcom = true, hintsComplex= false, pro = false;
 public  static    int num_rule=0;
    public static   int count_seperation =0;
   static   int c=0;
   static   String temWord =""; 
      public static String used_rule ="", used_structure="";
    public static  boolean simple = true, complex=false,compound=false;
    static  String rule_array[] = new String[20];
     static int count_rule =0;
        static String SS1="", SS2=""; 
 static   String main_rule ="";
   static String sql="";
        static int h =0;
         
    public static int  seperateWord(String s) throws SQLException
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
        return count_word_in_sentence;
   }
    
  public static  int to =0;
      public static void wordFromSemantics() throws SQLException
{
      
       sql = "SELECT * FROM "+SqlQuery.Table_semantic_feature;
  
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
         for(int i=0;i<count_neg_s;i++)
         {
             System.out.println("Neg: ["+i+"]"+negative_s[i]);
         }
         connection.close();
}
   
    public static String[][] MakeHolderList(String s) throws SQLException
    {
        simple = true; complex=false;compound=false;
        token = "";
        num_rule =0;
        to = seperateWord(s);
        System.out.println("to: "+to);
        wordFromSemantics();
        used_rule = "";
        used_structure="";
        count_seperation =0;
         for(int i=0;i<to;i++)
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
             for(int j=0;j<count_neg_s;j++)//************Negative************//
            {
                if(words[i].equals(negative_s[j]))
                {        
                    selectSemanticFeatures(negative_s[j],"N");
                    SetHolder(negative_s[j], "Negative", "Neg");
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
                      // System.out.println("ok");
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
                        //     System.out.println("error");
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
                        SetHolder(auxilliary_s[k], "Auxiliary","Aux");
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
         if(complex||compound)
         {
             simple= false;
         }
         
         count_word_in_sentence =0;
         count_word_in_semantics=0;
    
            count_noun_p = 0 ;count_noun_s = 0;count_pronoun_p = 0; count_pronoun_s = 0;count_adjective_p = 0 ;count_adjective_s = 0; 
            count_verb_p = 0; count_verb_s = 0;count_adverb_p = 0; count_adverb_s = 0; count_postfix_p = 0;count_postfix_s = 0;
            count_neg_p = 0;count_neg_s = 0;count_biv_p = 0;count_biv_s = 0; count_aux_p =0 ; count_aux_s =0; count_conj_p=0; count_conj_s=0;
            count_subord_p = 0; count_subord_s=0; count_subcom_p=0; count_subcom_s=0; count_num_p=0; count_num_s=0;
            count_prefix_p=0; count_prefix_s=0;
           count_total_struct=0;  
         
           num_check = false; seProcheck = false; subcom = true; hintsComplex= false; pro = false;
           first = true;
           c=0;
     return  (holder);
    }
    
    public static void SetHolder(String word, String pos, String tag)
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
    
     public static void selectSemanticFeatures(String w, String tag) throws SQLException
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
     public static void main(String[] args) throws SQLException
     {
        Tokenizing ob = new Tokenizing();
        ob.wordFromSemantics();
     }
    
}
