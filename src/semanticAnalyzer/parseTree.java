package semanticAnalyzer;
/**
 *
 * @author Sumi Mim
 */
import database.ConnectDB;
import database.Database;
import database.SqlQuery;
import java.sql.*;

public class parseTree {
    String sql ="";
    Connection connection = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public parseTree() {
        
    }
    
   String search_rules (String t,String rule[],int n)
    {
       // System.out.println("n== "+n);
        int i,k=0,j,f=0;
        String temp[]=new String[100],tt1,tt2,r="";
        for (i=0;i<n;i++)
        {
            k=0;
            f=0;
            tt1=rule[i];
           System.out.println("tt1 : "+tt1);
            tt2="";
            //System.out.println("tt1 length: "+tt1.length());
            for(j=0;j<tt1.length();j++)
            {
                 
                if (tt1.charAt(j)==' ')
                {
                    temp[k++]=tt2;
                    tt2="";//
                }
                else
                {tt2+=tt1.charAt(j);//System.out.println("temp: "+temp[k]);
                }
                    
              
            }
            //System.out.println(tt2+"TT2");
            temp[k++]=tt2;
         //   System.out.println("K: "+k);
         
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
                    if(temp[j].equals("biv")||temp[j].equals("Aux")||temp[j].equals("Num")||temp[j].equals("N")|| temp[j].equals("P")||temp[j].equals("pp")||temp[j].equals("V")||temp[j].equals("Adj")||temp[j].equals("Subord")||temp[j].equals("Adv")||temp[j].equals("Det")||temp[j].equals("Conj")||temp[j].equals("Subcom")||temp[j].equals("Subord")||temp[j].equals("Neg"))
                        
                    {
                        r+="("+temp[j]+",)";
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
    boolean MakeParse(String structure,String rule_used[])
    {
        int i, found=0, j;
        String struct[]={
            "Adj N N V Aux", //1
            "N Adv Adj",//2
            "P N V V",//3
            "P P N V Neg",//4
            "P P N V",//5
            "N V N V",//6
            "N V V N",//7
            "P N Adv V",//8
            "P N Adv V Neg",//9
            "P Adv N Neg",//10
            "Adj N V V",//11
            "P N Adv V Neg",//12
            "N N Adj N",//13
            "N pp Adj",//14
            "N N V N",//15
            "N pp V Aux",//16
            "Det N N V",//17
            "P Adv V Neg",//18
            "P Det N V",//19
            "N pp V Aux Adj",//20
            "P V Adj",//21
            "P Adj V",//22
            "N pp N V Aux",//23
            "N N V Neg",//24
            "P N V Aux Neg",//25
            "Det N Adj",//26
            "Det N V Adj",//27
            "N Adj N Conj P N V",//28
            "Det N Adv Adj",//29
            "P N V Aux",//30
            "Det N biv N V Aux",//31
            "N pp N V Aux Conj N pp N V Aux",//32
            "N N V Aux Conj N Adj",//33
            "N V Conj P N V",//34
            "P N V Aux Conj P N V Aux",//35
            "N Adj Conj P N V",//36
            "Subord P N V Aux Subcom P N V Aux",//37
            "Subord P N V Subcom P Adj V",//38
            "Subord P N V Aux Subcom N V Aux",//39
            "Subord N V Subcom P N V",//40
            "Subord N V N V Subcom N V N V",//41
            "Subord N V N V Subcom N N V",//42
            "Subord N V V N Subcom N N V",//43
            "Subord N pp N V Aux Subcom N pp N V Aux",//44
            "Subord N N V Subcom P N V",//45
            "Subord N N V Aux Subcom Adj N V Aux",//46
            "Subord P V Subcom P N",//47
            "Det N V Adj Conj P N V",//48
            "N N V V",//49
            "N pp Adj Conj N pp Adj",//50
            "N pp N V Aux",
            "Num pp N N V Aux",//51
            "N N V Aux", //52
            "N biv N V Aux", //53
            "Subord N biv N V Aux Subcom P N V Aux", //54
            "Subord N pp Adj Subcom N pp Adj",//55
            "Num pp N pp V Aux"
        };
        String rule_applied[][]={
            {"S -> SS","SS -> AP VP","AP -> Adj N","VP -> N V Aux"},//1
            {"S -> SS","SS -> NP AP","NP -> N","AP -> Adv Adj"},//2
            {"S -> SS","SS -> NP VP","NP -> P N","VP -> V V"},//3
            {"S -> SS","SS -> NP VP","NP -> P NP","NP ->P N","VP -> V Neg"},//4
            {"S -> SS","SS -> NP VP","NP -> P P","VP -> N V"},//5
            {"S -> SS","SS -> NP VP","NP -> N V","VP -> N V"},//6
            {"S -> SS","SS -> NP VP","NP -> N V","VP -> V N"},//7
            {"S -> SS","SS -> NP VP","NP -> P N","VP -> Adv VP","VP -> V"},//8
            {"S -> SS","SS -> NP VP","NP -> P N","VP -> Adv VP","VP -> V Neg"},//9
            {"S -> SS","SS -> NP NP","NP -> P Adv","NP -> N Neg"},//10
            {"S -> SS","SS -> AP VP","AP -> Adj N","VP -> V VP","VP -> V"},//11
            {"S -> SS","SS -> NP VP","NP -> P NP","NP -> N Adv","VP -> V Neg"},//12
            {"S -> SS","SS -> NP AP","NP -> N N","AP -> Adj N"},//13
            {"S -> SS","SS -> NP AP","NP -> N pp","AP -> Adj"},//14
            {"S -> SS","SS -> NP VP","NP -> N N","VP -> V N"},//15
            {"S -> SS","SS -> NP VP","NP -> N pp","VP -> V Aux"},//16
            {"S -> SS","SS -> NP VP","NP -> Det N","VP -> N V"},//17
            {"S -> SS","SS -> NP VP","NP -> P Adv","VP -> V Neg"},//18
            {"S -> SS","SS -> NP VP","NP -> P","VP -> NP VP","NP ->Det N","VP -> V"},//19
            {"S -> SS","SS -> NP VP","NP -> N pp","VP -> V Aux Adj"},//20
            {"S -> SS","SS -> NP VP","NP -> P","VP -> V Adj"},//21
            {"S -> SS","SS -> AP VP","AP -> P Adj","VP -> V"},//22
            {"S -> SS","SS -> NP NP","NP -> N pp","NP -> N VP","VP -> V Aux"},//23
            {"S -> SS","SS -> NP VP","NP -> N N","VP -> V Neg"},//24
            {"S -> SS","SS -> NP VP","NP -> P N","VP -> V Aux Neg"},//25
            {"S -> SS","SS -> NP AP","NP -> Det N","AP -> Adj"},//26
            {"S -> SS","SS -> NP VP","NP -> Det N","VP -> V Adj"},//27
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP AP","NP -> N Adj","AP -> N","SS2 -> NP VP","NP -> P N","VP -> V"},//28
            {"S -> SS","SS -> NP AP","NP -> Det N","AP -> Adv Adj"},//29
            {"S -> SS","SS -> NP VP","NP -> P N","VP -> V Aux"},//30
            {"S -> SS","SS -> NP VP","NP -> Det N biv","VP -> N V Aux"},//31
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP VP","NP-> N pp","VP-> N V Aux","SS2 -> NP VP","NP-> N pp","VP-> N V Aux"},//32
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP VP","NP-> N","VP-> N V Aux","SS2 -> NP VP","NP-> N","VP-> N V Aux"},//33
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP VP","NP-> N","VP-> N V","SS2 -> NP AP","NP-> N","AP-> Adj"},//34
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP VP","NP -> P","VP -> N V Aux","SS2 -> NP VP","NP -> P N","VP -> V Aux"},//35
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP AP","NP -> N","AP -> Adj","SS2 -> NP VP","NP -> P N","VP -> V"},//36
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> P N","VP -> V Aux","DC -> Subcom SS2","SS2 -> NP VP","NP -> P N","VP -> V Aux"},//37
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> P N","VP -> V","DC -> Subord SS2","SS2 -> AP VP","AP -> P Adj","VP -> V"},//38
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> P N","VP -> V Aux","DC -> Subcom SS2","SS2 -> NP VP","NP -> N","VP -> V Aux"},//39
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N","VP -> V","DC -> Subcom SS2","SS2 -> NP VP","NP -> N","VP -> N V"},//40
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N V","VP -> N V","DC -> Subcom SS2","SS2 -> NP VP","NP -> N V","VP -> N V"},//41
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N V","VP -> N V","DC -> Subcom SS2","SS2 -> NP VP","NP -> N V","VP -> V"},//42
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N V","VP -> V N","DC -> Subcom SS2","SS2 -> NP VP","NP -> N V","VP -> V"},//43
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N","VP -> N V","DC -> Subcom SS2","SS2 -> NP VP","NP -> N","VP -> N V"},//44
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N","VP -> N V","DC -> Subcom SS2","SS2 -> NP VP","NP -> P N","VP -> V"},//45
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N","VP -> N V Aux","DC -> Subcom SS2","SS2 -> NP VP","NP -> Adj N","VP -> V Aux"},//46
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> P","VP -> V","DC -> Subcom SS2","SS2 -> NP NP","NP -> P","NP -> N"},//47
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP AP","NP -> Det N","AP -> V Adj","SS2 -> NP VP","NP -> P N","VP -> V"},//48     
            {"S -> SS","SS -> NP VP","NP -> N NP","NP -> N","VP -> V VP","VP -> V"},//49
            {"S -> COMS","COMS -> SS1 Conj SS2","SS1 -> NP AP","NP -> N pp","AP -> Adj","SS2 -> NP AP","NP -> N pp","AP -> Adj"},//50
            {"S -> SS","SS -> NP VP","NP -> N pp","VP -> N VP","VP -> V Aux"},//51
            {"S -> SS","SS -> NP VP","NP -> Num pp N","VP -> N VP","VP -> V Aux"},//52
            {"S -> SS","SS -> NP VP","NP -> N","VP -> N VP","VP -> V Aux"},//52
            {"S -> SS","SS -> NP VP","NP -> N biv","VP -> N VP","VP -> V Aux"}, //53 
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP VP","NP -> N biv","VP -> N V Aux","DC -> Subcom SS2","SS2 -> NP VP","NP -> P N","VP -> V Aux"}, //54
            {"S -> CS","CS -> IC DC","IC -> Subord SS1","SS1 -> NP AP","NP -> N pp","AP -> Adj","DC -> Subcom SS2","SS2 -> NP AP","NP -> N pp","AP -> Adj"},
            {"S -> SS","SS -> NP NP","NP -> Num pp","NP -> N pp VP","VP -> V Aux"}
        };
        
     System.out.println("Structure : "+structure);
        for(i=0;i<struct.length;i++)
            if(struct[i].equals(structure))
            {
                found=i;
                System.out.println("Found: "+found);
                break;
            }
        if(i==struct.length)
            return false;
      System.out.println("rule_applied[found].length: "+rule_applied[found].length);
        for (i=0;i<rule_applied[found].length; i++)
        {
            System.out.println(rule_applied[found][i]);
            rule_used[i]= rule_applied[found][i];

        }
        return true;
    }

    /**
     *তিন
     */
     public static String  postfix[] = new String[200] , bivokti[]= new String[200], aux[]= new String[200],numeric[]= new String[200],
              noun[]= new String[200],pronoun[]= new String[200],verb[]= new String[200],det[]= new String[200],adj[]= new String[200],
              adv[]= new String[200],conj[]= new String[200],marker[]= new String[200],marker2[]= new String[200],negative[]= new String[200];
  
   
        int count_noun_s = 0, count_pronoun_s = 0,count_adjective_s = 0, 
            count_verb_s = 0,  count_adverb_s = 0, count_postfix_s = 0, 
           count_neg_s = 0, count_biv_s = 0,  count_aux_s =0, count_conj_s=0,
           count_M_s=0, count_Mm_s=0, count_num_s=0, count_det_s = 0,
           count_total_struct=0;         
  String  wordSemantics[]  = new  String[500];
  
 int count_word_in_semantics =0;
      
      public void wordFromSemantics() throws SQLException
{
     //  System.out.println("********Words from Semantics*******");
       sql = "SELECT * FROM "+SqlQuery.Table_semantic_feature;
    //  System.out.println("sql from semantics "+sql);
       boolean f = true;
       connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
       preparedStatement = connection.prepareStatement(sql);
       resultSet = preparedStatement.executeQuery();
       String ss="";
         while(resultSet.next())
          {
          switch (resultSet.getString("tag")) {
              case "N":
                  noun[count_noun_s] = resultSet.getString("word");
                  count_noun_s++;
                  count_word_in_semantics++;
                  wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "P":
                  pronoun[count_pronoun_s] = resultSet.getString("word");
                  count_pronoun_s++;
                  count_word_in_semantics++;
                    wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Adj":
                  adj[count_adjective_s] = resultSet.getString("word");
                  count_adjective_s++;
                  count_word_in_semantics++;
                    wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "V":
                  verb[count_verb_s] = resultSet.getString("word");
                  count_verb_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Adv":
                  adv[count_adverb_s] = resultSet.getString("word");
                  count_adverb_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "pp":
                  postfix[count_postfix_s] = resultSet.getString("word");
                  count_postfix_s++;
                  count_word_in_semantics++;
                    wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
          //(resultSet.getString("tag").equals("Neg"))
              case "Neg":
                negative[count_neg_s] = resultSet.getString("word");
                  count_neg_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "biv":
                  bivokti[count_biv_s] = resultSet.getString("word");
                  count_biv_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Aux":
                  aux[count_aux_s] = resultSet.getString("word");
                  count_aux_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;
              case "Conj":
                  conj[count_conj_s] = resultSet.getString("word");
                  count_conj_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
             case "Num":
                  numeric[count_num_s] = resultSet.getString("word");
                  count_num_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
              case "M":
                  marker[count_M_s] = resultSet.getString("word");
                  count_M_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
             case "Mm":
                  marker2[count_Mm_s] = resultSet.getString("word");
                  count_Mm_s++;
                  count_word_in_semantics++;
                   wordSemantics[count_word_in_semantics] = resultSet.getString("word");
                  break;  
               default:
                  break;
          }
         }
 
}
      
    public String parseEverything(String s) throws SQLException {
        // TODO code application logic here
      
      wordFromSemantics();
         String u;
         byte b[];
      /*  Charset a=Charset.forName("UTF-8");
        Scanner in=new Scanner(System.in);
        
        s=in.nextLine();
        b=s.getBytes("UTF-8");
    */    
         String word[]=new String[200],structure[]=new String[200],rule_used[]=new String[200],t="",p="",parse_result="",uu="";
         String num_list[],pronoun_list[],aux_list[],biv_list[],noun_list[],neg_list[],verb_list[],det_list[],adj_list[],adv_list[],conj_list[],marker_list[],pp_list[],parse_temp="";
        int i,j,count_word=0,count_pro=0,count_aux=0,count_biv=0,count_verb=0,count_noun=0,count_obj=0,count_det=0,count_adj=0,
                count_adv=0,count_conj=0,count_marker=0,count_neg=0,num_rules;
        boolean flag=false;
        for (i=0;i<s.length();i++)
        {
            if((s.charAt(i)==' ')&&flag)
            {
                word[count_word]=t;
                t=""; 
                count_word++;
                flag=false;
            }
            else if ((s.charAt(i)!=' '))
            {
                t+=s.charAt(i);
                flag=true;
            }
        }
        word[count_word]=t;
        t="";
        count_word++;
        flag=false;

        pronoun_list=new String[100];
        count_pro=0;
        aux_list=new String[200];
        count_aux=0;
        num_list=new String[200];
        int count_num=0;
        noun_list=new String[200];
        count_noun=0;
        pp_list=new String[200]; 
        int count_pp=0;
        biv_list=new String[200];
        count_biv=0;
        adj_list=new String[200];
        count_adj=0;
        adv_list=new String[200];
        count_adv=0;
        det_list=new String[200];
        count_det=0;
        verb_list=new String[200];
        count_verb=0;
        conj_list=new String[100];
        count_conj=0;
       marker_list=new String[200];
       count_marker=0;
       neg_list=new String[100];
       count_neg=0;
        
       int Arr=0;
        for(i=0;i<count_word;i++)
        {
          for(j=0;j<count_pronoun_s;j++)
          {
           if(word[i].equals(pronoun[j]))
           {
               structure[Arr++]="P";
               pronoun_list[count_pro++]=word[i];
                 t+=word[i]+"->Pronoun \n";
                 uu+=t+"\n";
               break;
           }
          }
          
          for(j=0;j<count_num_s;j++)
          {
              int fl=0;
              for(int k=0;k<count_postfix_s;k++)
              {
                  String wrd=numeric[j]+postfix[k];
                if(word[i].equals(wrd))
                {
                    structure[Arr++]="Num";
                    num_list[count_num++]=numeric[j];
                    t+=numeric[j]+"->Numeric \n";
                    uu+=t+"\n";
                    structure[Arr++]="pp";
                   pp_list[count_pp++]=postfix[k];
                    t+=postfix[k]+"->Postfix \n";
                    uu+=t+"\n";
                    fl=1;
                    break;
                }
              }
              if(fl==1)
                  break;
          }
       for(j=0;j<count_noun_s;j++)
          {
           if(word[i].equals(noun[j]))
           {
            structure[Arr++]="N";
            noun_list[count_noun++]=word[i];
            t+=word[i]+"->Noun \n";
            uu+=t+"\n";
            break;
           }
          }
          for(j=0;j<count_noun_s;j++)
          {
              int fl=0;int fb=0;
              for(int k=0;k<count_postfix_s;k++)
              {
                  String wrd=noun[j]+postfix[k];
                if(word[i].equals(wrd))
                {
                    structure[Arr++]="N";
                    noun_list[count_noun++]=noun[j];
                    t+=noun[j]+"->Noun \n";
                    uu+=t+"\n";
                    structure[Arr++]="pp";
                    pp_list[count_pp++]=postfix[k];
                    t+=postfix[k]+"->Postfix \n";
                    uu+=t+"\n";
                    fl=1;
                    break;
                }
              }
              for(int k=0;k<count_biv_s;k++)
              {
                  String wrd=noun[j]+bivokti[k];
                if(word[i].equals(wrd))
                {
                    structure[Arr++]="N";
                    noun_list[count_noun++]=noun[j];
                    t+=noun[j]+"->Noun \n";
                    uu+=t+"\n";
                    structure[Arr++]="biv";
                    biv_list[count_biv++]=bivokti[k];
                    t+=bivokti[k]+"->Bivokti \n";
                    uu+=t+"\n";
                    fb=1;
                    break;
                }
              }
              if(fl==1||fb==1)
                  break;
          }
          for(j=0;j<count_verb_s;j++)
          {
           if(word[i].equals(verb[j]))
           {
               structure[Arr++]="V";
               verb_list[count_verb++]=word[i];
               t+=word[i]+"->Verb \n";
               uu+=t+"\n";
               break;
           }
          }
          for(j=0;j<count_verb_s;j++)
          {
              int fl=0;
              for(int k=0;k<count_aux_s;k++)
              {
                  String wrd=verb[j]+aux[k];
                if(word[i].equals(wrd))
                {
                    structure[Arr++]="V";
                    verb_list[count_verb++]=verb[j];
                    t+=verb[j]+"->Verb \n";
                    uu+=t+"\n";
                    structure[Arr++]="Aux";
                   aux_list[count_aux++]=aux[k];
                    t+=aux[k]+"->Auxilliary \n";
                    uu+=t+"\n";
                    fl=1;
                    break;
                }
              }
              if(fl==1)
                  break;
          }
          for(j=0;j<count_adjective_s;j++)
          {
           if(word[i].equals(adj[j]))
           {
             structure[Arr++]="Adj";
             adj_list[count_adj++]=word[i];
             t+=word[i]+"->Adjective \n";
             uu+=t+"\n";
             break;
           }
          }
          for(j=0;j<count_adverb_s;j++)
          {
           if(word[i].equals(adv[j]))
           {
             structure[Arr++]="Adv";
             adv_list[count_adv++]=word[i];
                t+=word[i]+"->Adverb \n"; 
             uu+=t+"\n";
             break;
           }
          }
        
        
          for(j=0;j<count_conj_s;j++)
          {
           if(word[i].equals(conj[j]))
           {
               structure[Arr++]="Conj";
               conj_list[count_conj++]=word[i];
               t+=word[i]+"->Conjuction \n";
               uu+=t+"\n";
               break;
           }
          }
        
          for(j=0;j<count_neg_s;j++)
          {
           if(word[i].equals(negative[j]))
           {
               structure[Arr++]="Neg";
               neg_list[count_neg++]=word[i];
               t+=word[i]+"->Negative \n";
               uu+=t+"\n";
               break;
           }
          }
          
          for(j=0;j<count_M_s;j++)
          {
           if(word[i].equals(marker[j]))
           {
               structure[Arr++]="Subord";
               marker_list[count_marker++]=word[i];
               t+=word[i]+"->Subord \n";
               uu+=t+"\n";
               break;
           }
          }
          
          for(j=0;j<count_Mm_s;j++)
          {
           if(word[i].equals(marker2[j]))
           {
               structure[Arr++]="Subcom";
               marker_list[count_marker++]=word[i];
               t+=word[i]+"->Subcom \n";
               uu+=t+"\n";
               break;
           }
          }
          for(j=0;j<count_det_s;j++)
          {
           if(word[i].equals(det[j]))
           {
               structure[Arr++]="Det";
               det_list[count_det++]=word[i];
                   t+=word[i]+"->Determinate \n";
                uu+=t+"\n";
                break;
           }
          }
        
        }
       // System.out.println(t+"This is T");
        uu=t;
        t="";
        for(i=0;i<Arr;i++)
            if(i!=0)
                t+=" "+structure[i];
            else
                t+=structure[i];
       // System.out.println(t+"Another");
        parseTree ob=new parseTree();
        boolean state=ob.MakeParse(t,rule_used);
        t="";
        num_rules=0;
        for(i=0;i<rule_used.length;i++)
        {
            if(rule_used[i]!=null)
            {
                t+=rule_used[i]+"\n";
                num_rules++;
            }
        }
      //  System.out.println("start"+t+"hei");//Rules displayed

        parse_temp="";
        parse_temp=ob.search_rules("S", rule_used, num_rules);
        System.out.println("This is parse_temp"+parse_temp);
        parse_result="";

        StringBuffer buff;

        for(i=1;i<parse_temp.length()-1;i++)
        {
            parse_result+=parse_temp.charAt(i);
            if(parse_temp.charAt(i)==',')
            {
                p="";
                for(j=i-1;parse_temp.charAt(j)!='('&& parse_temp.charAt(j)!=' ';j--)
                    p+=parse_temp.charAt(j);
                buff = new StringBuffer(p);
                buff.reverse();
                p= buff.toString();
        //        System.out.println(p);
                if(p.equals("N"))
                {
                    for (j=0;j<count_noun;j++)
                    {
                        if(noun_list[j].length()>0)
                        {
                            parse_result += noun_list[j];
                            noun_list[j]="";
                            break;
                        }
                    }
                }
               else if(p.equals("Num"))
                {
                    for (j=0;j<count_num;j++)
                    {
                        if(num_list[j].length()>0)
                        {
                            parse_result += num_list[j];
                            num_list[j]="";
                            break;
                        }
                    }
                }    
                else if(p.equals("pp"))
                {
                    for (j=0;j<count_pp;j++)
                    {
                        if(pp_list[j].length()>0)
                        {
                            parse_result += pp_list[j];
                            pp_list[j]="";
                            break;
                        }
                    }
                }     
                
                else if(p.equals("biv"))
                {
                    for (j=0;j<count_biv;j++)
                    {
                        if(biv_list[j].length()>0)
                        {
                            parse_result += biv_list[j];
                            biv_list[j]="";
                            break;
                        }
                    }
                }    
                
                 else if(p.equals("Aux"))
                {
                    for (j=0;j<count_aux;j++)
                    {
                        if(aux_list[j].length()>0)
                        {
                            parse_result += aux_list[j];
                            aux_list[j]="";
                            break;
                        }
                    }
                }
                
                else if (p.equals("P"))
                {
                    for (j=0;j<count_pro;j++)
                    {
                        if(pronoun_list[j].length()>0)
                        {
                            parse_result += pronoun_list[j];
                            pronoun_list[j]="";
                            break;
                        }
                    }
                }
                else if (p.equals("V"))
                {
                    for (j=0;j<count_verb;j++)
                    {
                        if(verb_list[j].length()>0)
                        {
                            parse_result += verb_list[j];
                            verb_list[j]="";
                            break;
                        }
                    }
                }
                else if (p.equals("Adj"))
                {
                    for (j=0;j<count_adj;j++)
                    {
                        if(adj_list[j].length()>0)
                        {
                            parse_result += adj_list[j];
                            adj_list[j]="";
                            break;
                        }
                    }
                }
                else if (p.equals("Adv"))
                {
                    for (j=0;j<count_adv;j++)
                    {
                        if(adv_list[j].length()>0)
                        {
                            parse_result += adv_list[j];
                            adv_list[j]="";
                            break;
                        }
                    }
                }
                
                else if (p.equals("Conj"))
                {
                    for (j=0;j<count_conj;j++)
                    {
                        if(conj_list[j].length()>0)
                        {
                            parse_result += conj_list[j];
                            conj_list[j]="";
                            break;
                        }
                    }
                }
                else if (p.equals("Mar"))
                {
                    for (j=0;j<count_marker;j++)
                    {
                        if(marker_list[j].length()>0)
                        {
                            parse_result += marker_list[j];
                            marker_list[j]="";
                            break;
                        }
                    }
                }
                else if(p.equals("Subord"))
                {
                    for (j=0;j<count_marker;j++)
                    {
                        if(marker_list[j].length()>0)
                        {
                            parse_result += marker_list[j];
                            marker_list[j]="";
                            break;
                        }
                    }
                }
                else if(p.equals("N"))
                {
                    for (j=0;j<count_noun;j++)
                    {
                        if(noun_list[j].length()>0)
                        {
                            parse_result += noun_list[j];
                            noun_list[j]="";
                            break;
                        }
                    }
                }
                else if(p.equals("Neg"))
                {
                    for (j=0;j<count_neg;j++)
                    {
                        if(negative[j].length()>0)
                        {
                            parse_result += neg_list[j];
                            neg_list[j]="";
                            break;
                        }
                    }
                }
                else if (p.equals("Subcom"))
                {
                    for (j=0;j<count_marker;j++)
                    {
                        if(marker_list[j].length()>0)
                        {
                            parse_result += marker_list[j];
                            marker_list[j]="";
                            break;
                        }
                    }
                }
            }
        }
      /*  System.out.println("This is parse result\n"+parse_result);
        System.out.println("This is uu"+uu);
        System.out.println("This is t"+t);
*/
        String ret=uu+"*"+t+":"+parse_result;
        System.out.println("uu: "+uu);
        System.out.println("tt: "+t);
        System.out.println("parse tree: "+parse_result );
        return (ret);      
    }
    
                   
    }