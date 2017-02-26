/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiDesign;

import com.sun.imageio.plugins.common.I18N;
import database.SqlQuery;
import database.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sumi Mim
 */
public class DictionaryHelp {
     Table table_obj = null;
    SqlQuery sqlQuery_obj = null;
    Connection connection = null;
    ResultSet resultSet = null; 
    PreparedStatement preparedStatement = null; 
    private void update_NounInfo(int row)
    {
        try {
            String word = jTable_Noun.getValueAt(row, 1).toString();
            String animate = jTable_Noun.getValueAt(row, 2).toString();
            String human =  jTable_Noun.getValueAt(row, 3).toString();
            String gender =  jTable_Noun.getValueAt(row, 4).toString();
            String alive =  jTable_Noun.getValueAt(row, 5).toString();
            String agent =  jTable_Noun.getValueAt(row, 6).toString();
            String honor = jTable_Noun.getValueAt(row, 7).toString();
            String intelligent = jTable_Noun.getValueAt(row, 8).toString();
            String adult =  jTable_Noun.getValueAt(row, 9).toString();
            String leg = jTable_Noun.getValueAt(row, 10).toString();
            String hand =  jTable_Noun.getValueAt(row, 11).toString();
            String wing =  jTable_Noun.getValueAt(row, 12).toString();
            
            System.out.println("a:"+animate+"\n h: "+human+"\ng"+gender+"\na"+alive+"\nag "+agent+"\nhonor "+honor+"\nint "+intelligent
                    +"\nad "+adult+"\n leg"+leg+"\nhan "+hand+"\nwing"+wing);
            String sql ="UPDATE `semantic_feature_bangla`.`"+table_obj.Table_noun+"` SET"
                    + " `"+table_obj.noun_Animate+"` = '"+animate+"', `"+table_obj.noun_Human+"` = '"+human+"', `"+table_obj.noun_Gender+"` = '"+gender+"',"
                    + " `"+table_obj.noun_Alive+"` = '"+alive+"', `"+table_obj.noun_Agent+"` = '"+agent+"', `"+table_obj.noun_Honor+"` = '"+honor+"',"
                    + " `"+table_obj.noun_Intelligence+"` = '"+intelligent+"', `"+table_obj.noun_Adult+"` = '"+adult+"', `"+table_obj.noun_Leg+"` = '"+leg+"',"
                    + " `"+table_obj.noun_Hands+"` = '"+hand+"', `"+table_obj.noun_Wings+"` = '"+wing+"'"
                    + "   WHERE `"+table_obj.Table_noun+"`.`"+table_obj.noun_word+"` = '"+word+"';";
              System.out.println("SQl:"+sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();                  
           
        } catch (Exception ex) {
            Logger.getLogger(Dictionary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
