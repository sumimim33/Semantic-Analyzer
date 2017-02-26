package database;

/**
 *
 * @author Sumi Mim
 */
public class Database {
 
    public static String DB_pos_tagger ="bangla_pos_tagger";
    public static String DB_semantic_feature ="bangla_word_semantic";
    
    public static String Table_pos_tagger ="table_postagger";
    public static String Table_semantic_feature ="table_semantic";
   

   public static String Col_semantic_word ="word";
   public static String Col_semantic_pos ="pos";
   public static String Col_semantic_tag="tag";
   
   public static String Col_semantic_animate="animate";
   public static String Col_semantic_human ="human";
   public static String Col_semantic_gender="gender";
   public static String Col_semantic_adult ="adult";
   public static String Col_semantic_intelligent="intelligent";
   public static String Col_semantic_number="number";
   public static String Col_semantic_agent="agent";
   public static String Col_semantic_alive ="alive";
   public static String Col_semantic_honor="honor";
   public static String Col_semantic_person="person";
   public static String Col_semantic_emphasis="emphasis";
   public static String Col_semantic_tense ="tense";
   public static String Col_semantic_numerics="numerics";
   public static String Col_semantic_subject ="subject";
   public static String Col_semantic_legs="legs";
   public static String Col_semantic_hands ="hands";
   public static String Col_semantic_wings="wings";
      public String Sql(String tag)
      {
        String sql;
           //  sql = "SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='"+tag+"'";
          sql=   "SELECT `word`,`animate`,`human`,`gender`,`alive`,`agent`,`honor`,`intelligent`,`adult`,`legs`,`hands`,`wings` FROM `pos_tagger` WHERE `tag`='"+tag+"'";
           return sql;
      }    
      
      public String SqlSemantic(String tableName, String hint)
      {
          String sql = null;
           if(hint.equals("C"))
          {
          sql = "SELECT COUNT(*) as `no_of_words` FROM "+DB_semantic_feature+"."+tableName;
          System.out.println("sql1: "+sql);
          }
           
          else if(hint.equals("S"))
             sql = "SELECT * FROM "+DB_semantic_feature+"."+tableName;
           return sql;    
      }
      
     
      public String SqlInsertInFeature()
      {
          String sql="";
           sql = "INSERT INTO `semantic_feature_bangla`.`nouninfo` (`sl`, `word`, `animate`, `human`, `gender`, `alive`, `agent`, `honor`, `intelligence`, `adult`, `leg`, `hands`, `wings`) "
              + "VALUES (NULL, 'জাম', '0', '0', '-1', '0', '0', '0', '-1', '-1', '-1', '-1', '-1');";
           
    return sql;
          
      }
             
   /*
   public static String Select_noun ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='N'"; //1
   public static String Select_pronoun ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='PN'";//2
   public static String Select_adjective ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='ADJ'";//3
   public static String Select_verb ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='V'";//4
   public static String Select_adverb ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='ADV'";//5
   public static String Select_bivokti ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='BIV'";//6
   public static String Select_det ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='DET'";//7
   public static String Select_preposition ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='PP'";//8
   public static String Select_quantifier ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='QFR'";//9
   public static String Select_auxiliary ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='AUX'";//10
   public static String Select_Conjunction1 ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='CONJ1'";//11
   public static String Select_Cnonjunction2 ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='CONJ2'";//12
   public static String Select_negative ="SELECT * FROM "+DB_pos_tagger+"."+Table_pos_tagger+" WHERE `tag`='NEG'";//13
   */
   
   
    }
