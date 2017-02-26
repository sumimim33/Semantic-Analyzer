
package database;

import java.sql.SQLException;


/**
 *
 * @author Sumi Mim
 */
public class SqlQuery {
    
    public static String DB_pos_tagger ="bangla_pos_tagger";
    public static String DB_semantic_feature ="bangla_word_semantic";
    
    public static String Table_pos_tagger ="table_postagger";
    public static String Table_semantic_feature ="table_semantic";
   
    
 public static String SQL= "";
 

 public static String SELECT_FROM_POSTAGGER()
 {      SQL= "SELECT * FROM "+Table_pos_tagger+";";
         return SQL;
 }
 public static String SELECT_SEAMNTIC_FAETURE(String s, String tag)
 {
     SQL ="SELECT * FROM `table_semantic` WHERE `word` = '"+s+"' AND `tag` = '"+tag+"'";
     return SQL;
 }
 public static String COUNT_TOTAL(String tag)
 {      SQL= "SELECT COUNT(*) as `total` FROM `table_semantic` WHERE tag = '"+tag+"'";
         return SQL;
 }
 public static String COUNT_ALL()
 {
     SQL ="SELECT COUNT(*) as `total` FROM `table_semantic`";
     return SQL;
 }
   //**********************SELECT SEMANTIC*********************// 
  public static String SELECT_FROM_SEMANTIC()
 {      SQL = "SELECT * FROM "+Table_semantic_feature+";";
         return SQL;
 }

  public static String DELETE(String word, String tag)
  {
      SQL = "DELETE FROM `table_semantic` WHERE `word` = '"+word+"' AND `tag` = '"+tag+"'";
      return SQL;
  }
 
   // *********************INSERT NEW WORD*********************//

 public static String  INSERT_NEW_WORD(String word,String tag, String anim, String hum, String gender,String adult, String intelligent,String number,
             String agent,String alive, String honor, String person,String emphasis,String tense, String numeric,String subject, 
             String legs, String hands, String wings, String hints ) throws SQLException
{
    if(hints.equals("I"))
 SQL ="INSERT INTO `"+SqlQuery.DB_semantic_feature+"`.`"+SqlQuery.Table_semantic_feature+"` "
         + "(`sl`, `word`, `tag`, `animate`, `human`, `gender`, `adult`, `intelligent`, `number`, `agent`, `alive`, `honor`, `person`, `emphasis`, `tense`, "
         + "`numerics`, `subject`, `legs`, `hands`, `wings`) "
         + "VALUES (NULL, '"+word+"', '"+tag+"', '"+anim+"', '"+hum+"', '"+gender+"', '"+adult+"', '"+intelligent+"', "
         + "'"+number+"', '"+agent+"', '"+alive+"', '"+honor+"', '"+person+"', '"+emphasis+"', '"+tense+"',"
         + " '"+numeric+"', '"+subject+"', '"+legs+"', '"+hands+"', '"+wings+"');";
   else
        SQL ="UPDATE `"+DB_semantic_feature+"`.`"+Table_semantic_feature+"`"
                + " SET `word` = '"+word+"' , `tag` = '"+tag+"' , `animate` = '"+anim+"', `human` = '"+hum+"', `gender` = '"+gender+"', `adult` = '"+adult+"', `intelligent` = '"+intelligent+"', "
                + "`number` = '"+number+"', `agent` = '"+agent+"', `alive` = '"+alive+"', `honor` = '"+honor+"', `person` = '"+person+"', "
                + "`emphasis` = '"+emphasis+"', `tense` = '"+tense+"', `numerics` = '"+numeric+"', `subject` = '"+subject+"', `legs` = '"+legs+"', "
                + "`hands` = '"+hands+"', `wings` = '"+wings+"' WHERE `table_semantic`.`word` = '"+word+"';";
   return SQL;
}
 
 
    public static void main(String[] args)
    {
       
    }
}
