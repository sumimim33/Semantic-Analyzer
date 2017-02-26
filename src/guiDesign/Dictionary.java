package guiDesign;

import database.ConnectDB;
import database.SqlQuery;
import database.Table;
import java.awt.Font;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import static semanticAnalyzer.WordChecker.sql;

/**
 *
 * @author Sumi Mim
 */
public class Dictionary extends javax.swing.JFrame {
    TableModel model;
 
    public static Connection connection = null;
   public static  ResultSet resultSet = null; 
  public static   PreparedStatement preparedStatement = null; 
  public static String word_from_table = "";
  public static String tag_from_table = "";
    public Dictionary() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String ssss = "<html><a href=\"\">Delete</a></html>";
        delete.setText(ssss);
       ShowDatabaseTable();
    }

    public  void ShowDatabaseTable()
    {
         try {
          
            sql = SqlQuery.SELECT_FROM_SEMANTIC();
          connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
          preparedStatement = connection.prepareStatement(sql);
          resultSet = preparedStatement.executeQuery(); 
          model =  (DefaultTableModel) DbUtils.resultSetToTableModel(resultSet);
          Table_db.setModel(model); 
         // count_total.setText(Integer.toString(Table_db.getRowCount()));
          preparedStatement =  null;
          resultSet = null;
          //String t_sql_noun = SqlQuery.COUNT_TOTAL("N");
           //  System.out.println("tioooo: "+t_sql_noun);
           int t;
           t = countTotal(null, "all");
           count_total.setText(""+t);
         t = countTotal("N","t");
          count_noun.setText(""+t);
          t = countTotal("P","t");
          count_pronoun.setText(""+t);
          t = countTotal("Adj","t");
          count_adj.setText(""+t);
            t = countTotal("V","t");
          count_verb.setText(""+t);
            t = countTotal("Adv","t");
          count_adverb.setText(""+t);
            t = countTotal("pp","t");
          count_postfix.setText(""+t);
            t = countTotal("Conj","t");
          count_conj.setText(""+t);
            t = countTotal("subord","t");
          count_subord.setText(""+t);
            t = countTotal("subcom","t");
          count_subcom.setText(""+t);
            t = countTotal("Neg","t");
          count_neg.setText(""+t);
           t = countTotal("biv","t");
          count_biv.setText(""+t);
           t = countTotal("prep","t");
          count_prifix.setText(""+t);
          t = countTotal("Num","t");
          count_num.setText(""+t);
             t = countTotal("Aux","t");
          count_aux.setText(""+t);
          
        } catch (SQLException ex) {
            Logger.getLogger(Dictionary_Frame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void filter(String query)
    {
       TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
       Table_db.setRowSorter(sorter);
       sorter.setRowFilter(RowFilter.regexFilter(query));
       
    }
    public int countTotal(String tag, String all) throws SQLException
    {
        int total=0 ;
        String t_sql="";
        if(all.equals("all"))
            t_sql= SqlQuery.COUNT_ALL();
        else
         t_sql = SqlQuery.COUNT_TOTAL(tag);
          preparedStatement = connection.prepareStatement(t_sql);
          resultSet = preparedStatement.executeQuery();
          while(resultSet.next())
          total = Integer.parseInt(resultSet.getString("total"));
        return total;
    }
    private void update_Info(String  sql )
    {
        try {
           
              System.out.println("SQl update noun:"+sql);
          preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();        
              
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, ex);
        }    
    }
    
    public boolean Check_Before_Insert(String w, String t) throws SQLException
    {
        String sl = SqlQuery.SELECT_SEAMNTIC_FAETURE(w, t);
        System.out.println("sl: "+sl);
        connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
         preparedStatement = connection.prepareStatement(sl);
         resultSet = preparedStatement.executeQuery();
         boolean val = resultSet.next();
        return val;
        
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel116 = new javax.swing.JPanel();
        jPanel117 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Table_db = new javax.swing.JTable();
        Total_Noun = new javax.swing.JLabel();
        count_total = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Total_Noun1 = new javax.swing.JLabel();
        count_noun = new javax.swing.JLabel();
        Total_Noun2 = new javax.swing.JLabel();
        count_pronoun = new javax.swing.JLabel();
        Total_Noun3 = new javax.swing.JLabel();
        count_adj = new javax.swing.JLabel();
        Total_Noun4 = new javax.swing.JLabel();
        count_verb = new javax.swing.JLabel();
        Total_Noun5 = new javax.swing.JLabel();
        count_adverb = new javax.swing.JLabel();
        Total_Noun6 = new javax.swing.JLabel();
        count_postfix = new javax.swing.JLabel();
        Total_Noun7 = new javax.swing.JLabel();
        count_conj = new javax.swing.JLabel();
        Total_Noun8 = new javax.swing.JLabel();
        count_neg = new javax.swing.JLabel();
        count_subord = new javax.swing.JLabel();
        Total_Noun9 = new javax.swing.JLabel();
        count_subcom = new javax.swing.JLabel();
        Total_Noun10 = new javax.swing.JLabel();
        count_biv = new javax.swing.JLabel();
        Total_Noun11 = new javax.swing.JLabel();
        count_prifix = new javax.swing.JLabel();
        Total_Noun12 = new javax.swing.JLabel();
        count_num = new javax.swing.JLabel();
        Total_Noun13 = new javax.swing.JLabel();
        count_aux = new javax.swing.JLabel();
        Total_Noun14 = new javax.swing.JLabel();
        filter = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        delete = new javax.swing.JLabel();
        jPanel118 = new javax.swing.JPanel();
        Tab = new javax.swing.JTabbedPane();
        jPanel119 = new javax.swing.JPanel();
        jPanel122 = new javax.swing.JPanel();
        a_Word = new javax.swing.JTextField();
        a_tag_Word = new javax.swing.JTextField();
        jPanel121 = new javax.swing.JPanel();
        jLabel315 = new javax.swing.JLabel();
        jLabel316 = new javax.swing.JLabel();
        jLabel317 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        jLabel319 = new javax.swing.JLabel();
        a_anim = new javax.swing.JTextField();
        a_hum = new javax.swing.JTextField();
        a_gender = new javax.swing.JTextField();
        a_intelligent = new javax.swing.JTextField();
        jPanel123 = new javax.swing.JPanel();
        jLabel321 = new javax.swing.JLabel();
        jLabel322 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        jLabel327 = new javax.swing.JLabel();
        a_number = new javax.swing.JTextField();
        a_agent = new javax.swing.JTextField();
        a_alive = new javax.swing.JTextField();
        a_numeric = new javax.swing.JTextField();
        a_tense = new javax.swing.JTextField();
        jLabel324 = new javax.swing.JLabel();
        a_honor = new javax.swing.JTextField();
        jLabel328 = new javax.swing.JLabel();
        a_person = new javax.swing.JTextField();
        jLabel349 = new javax.swing.JLabel();
        a_sub = new javax.swing.JTextField();
        jLabel353 = new javax.swing.JLabel();
        a_leg = new javax.swing.JTextField();
        jLabel356 = new javax.swing.JLabel();
        jLabel355 = new javax.swing.JLabel();
        a_hand = new javax.swing.JTextField();
        a_wing = new javax.swing.JTextField();
        jLabel354 = new javax.swing.JLabel();
        a_emphasis = new javax.swing.JTextField();
        jLabel334 = new javax.swing.JLabel();
        a_adult = new javax.swing.JTextField();
        jPanel130 = new javax.swing.JPanel();
        jLabel359 = new javax.swing.JLabel();
        jLabel360 = new javax.swing.JLabel();
        jLabel361 = new javax.swing.JLabel();
        jLabel362 = new javax.swing.JLabel();
        jLabel363 = new javax.swing.JLabel();
        jLabel364 = new javax.swing.JLabel();
        jLabel365 = new javax.swing.JLabel();
        jLabel366 = new javax.swing.JLabel();
        jLabel367 = new javax.swing.JLabel();
        jLabel368 = new javax.swing.JLabel();
        jLabel376 = new javax.swing.JLabel();
        jLabel377 = new javax.swing.JLabel();
        btn_insert = new javax.swing.JButton();
        btn_insert2 = new javax.swing.JButton();
        Label_Noun = new javax.swing.JLabel();
        Label_Noun1 = new javax.swing.JLabel();
        jPanel124 = new javax.swing.JPanel();
        jPanel128 = new javax.swing.JPanel();
        a_Word1 = new javax.swing.JTextField();
        a_tag_Word1 = new javax.swing.JTextField();
        jPanel129 = new javax.swing.JPanel();
        jLabel325 = new javax.swing.JLabel();
        jLabel326 = new javax.swing.JLabel();
        jLabel329 = new javax.swing.JLabel();
        jLabel330 = new javax.swing.JLabel();
        jLabel331 = new javax.swing.JLabel();
        a_anim1 = new javax.swing.JTextField();
        a_hum1 = new javax.swing.JTextField();
        a_gender1 = new javax.swing.JTextField();
        a_intelligent1 = new javax.swing.JTextField();
        jPanel133 = new javax.swing.JPanel();
        jLabel332 = new javax.swing.JLabel();
        jLabel333 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        jLabel336 = new javax.swing.JLabel();
        a_number1 = new javax.swing.JTextField();
        a_agent1 = new javax.swing.JTextField();
        a_alive1 = new javax.swing.JTextField();
        a_numeric1 = new javax.swing.JTextField();
        a_tense1 = new javax.swing.JTextField();
        jLabel337 = new javax.swing.JLabel();
        a_honor1 = new javax.swing.JTextField();
        jLabel338 = new javax.swing.JLabel();
        a_person1 = new javax.swing.JTextField();
        jLabel350 = new javax.swing.JLabel();
        a_sub1 = new javax.swing.JTextField();
        jLabel357 = new javax.swing.JLabel();
        a_leg1 = new javax.swing.JTextField();
        jLabel358 = new javax.swing.JLabel();
        jLabel386 = new javax.swing.JLabel();
        a_hand1 = new javax.swing.JTextField();
        a_wing1 = new javax.swing.JTextField();
        jLabel387 = new javax.swing.JLabel();
        a_emphasis1 = new javax.swing.JTextField();
        jLabel339 = new javax.swing.JLabel();
        a_adult1 = new javax.swing.JTextField();
        jPanel134 = new javax.swing.JPanel();
        jLabel388 = new javax.swing.JLabel();
        jLabel389 = new javax.swing.JLabel();
        jLabel390 = new javax.swing.JLabel();
        jLabel391 = new javax.swing.JLabel();
        jLabel392 = new javax.swing.JLabel();
        jLabel393 = new javax.swing.JLabel();
        jLabel394 = new javax.swing.JLabel();
        jLabel395 = new javax.swing.JLabel();
        jLabel396 = new javax.swing.JLabel();
        jLabel397 = new javax.swing.JLabel();
        jLabel399 = new javax.swing.JLabel();
        jLabel400 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Label_Noun4 = new javax.swing.JLabel();
        Label_Noun5 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 656));
        setResizable(false);

        jPanel116.setBackground(new java.awt.Color(204, 255, 204));
        jPanel116.setPreferredSize(new java.awt.Dimension(850, 629));

        jPanel117.setBackground(new java.awt.Color(204, 204, 255));

        Table_db.setFont(new java.awt.Font("SolaimanLipi", 0, 11)); // NOI18N
        Table_db.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_db.setToolTipText("Words with Semantics");
        Table_db.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_dbMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(Table_db);

        Total_Noun.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun.setText("Total = ");

        count_total.setText("j");

        jLabel13.setText("Dictionary");

        Total_Noun1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun1.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun1.setText("N= ");

        count_noun.setText("j");

        Total_Noun2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun2.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun2.setText("P= ");

        count_pronoun.setText("j");

        Total_Noun3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun3.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun3.setText("Adj= ");

        count_adj.setText("j");

        Total_Noun4.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun4.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun4.setText("V= ");

        count_verb.setText("j");

        Total_Noun5.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun5.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun5.setText("Adv= ");

        count_adverb.setText("j");

        Total_Noun6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun6.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun6.setText("PP=");

        count_postfix.setText("j");

        Total_Noun7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun7.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun7.setText("Conj=");

        count_conj.setText("j");

        Total_Noun8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun8.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun8.setText("Neg=");

        count_neg.setText("j");

        count_subord.setText("j");

        Total_Noun9.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun9.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun9.setText("Subord=");

        count_subcom.setText("j");

        Total_Noun10.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun10.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun10.setText("Subcom=");

        count_biv.setText("j");

        Total_Noun11.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun11.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun11.setText("Bivokti=");

        count_prifix.setText("j");

        Total_Noun12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun12.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun12.setText("Prifix=");

        count_num.setText("j");

        Total_Noun13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun13.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun13.setText("Num=");

        count_aux.setText("j");

        Total_Noun14.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        Total_Noun14.setForeground(new java.awt.Color(255, 102, 102));
        Total_Noun14.setText("Aux=");

        filter.setFont(new java.awt.Font("SolaimanLipi", 0, 11)); // NOI18N
        filter.setToolTipText("Search");
        filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterKeyReleased(evt);
            }
        });

        jLabel2.setText("Search");

        delete.setForeground(new java.awt.Color(255, 102, 102));
        delete.setText("jLabel3");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel117Layout.createSequentialGroup()
                        .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel117Layout.createSequentialGroup()
                                .addGap(347, 347, 347)
                                .addComponent(jLabel13)
                                .addGap(63, 63, 63)
                                .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel117Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Total_Noun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_total)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(count_noun)
                                .addGap(18, 18, 18)
                                .addComponent(Total_Noun2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_pronoun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_adj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_verb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total_Noun5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_adverb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total_Noun6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_postfix)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total_Noun7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_conj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_neg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_subord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_subcom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_biv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_prifix)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Total_Noun13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_num)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total_Noun14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(count_aux)
                                .addGap(18, 18, 18)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Total_Noun)
                    .addComponent(count_total)
                    .addComponent(Total_Noun1)
                    .addComponent(count_noun)
                    .addComponent(Total_Noun2)
                    .addComponent(count_pronoun)
                    .addComponent(Total_Noun3)
                    .addComponent(count_adj)
                    .addComponent(Total_Noun4)
                    .addComponent(count_verb)
                    .addComponent(Total_Noun5)
                    .addComponent(count_adverb)
                    .addComponent(Total_Noun6)
                    .addComponent(count_postfix)
                    .addComponent(Total_Noun7)
                    .addComponent(count_conj)
                    .addComponent(Total_Noun8)
                    .addComponent(count_neg)
                    .addComponent(Total_Noun9)
                    .addComponent(count_subord)
                    .addComponent(Total_Noun10)
                    .addComponent(count_subcom)
                    .addComponent(Total_Noun11)
                    .addComponent(count_biv)
                    .addComponent(Total_Noun12)
                    .addComponent(count_prifix)
                    .addComponent(Total_Noun13)
                    .addComponent(count_num)
                    .addComponent(Total_Noun14)
                    .addComponent(count_aux)
                    .addComponent(delete))
                .addGap(32, 32, 32))
        );

        jPanel118.setBackground(new java.awt.Color(204, 204, 255));

        Tab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tab.setMaximumSize(new java.awt.Dimension(750, 250));

        jPanel119.setBackground(new java.awt.Color(204, 204, 255));
        jPanel119.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanel119.setPreferredSize(new java.awt.Dimension(500, 235));

        a_Word.setFont(new java.awt.Font("SolaimanLipi", 0, 12)); // NOI18N
        a_Word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_WordActionPerformed(evt);
            }
        });

        a_tag_Word.setFont(new java.awt.Font("Siyam Rupali", 0, 12)); // NOI18N
        a_tag_Word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_tag_WordActionPerformed(evt);
            }
        });

        jLabel315.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel315.setText("Animate");

        jLabel316.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel316.setText("Human");

        jLabel317.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel317.setText("Gender");

        jLabel318.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel318.setText("Adult");

        jLabel319.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel319.setText("Intelligent");

        a_anim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_animActionPerformed(evt);
            }
        });

        a_hum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_humActionPerformed(evt);
            }
        });

        a_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_genderActionPerformed(evt);
            }
        });

        a_intelligent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_intelligentActionPerformed(evt);
            }
        });

        jLabel321.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel321.setText("Number");

        jLabel322.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel322.setText("Agent");

        jLabel323.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel323.setText("Alive");

        jLabel327.setText("Tense");

        a_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_numberActionPerformed(evt);
            }
        });

        a_agent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_agentActionPerformed(evt);
            }
        });

        a_alive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_aliveActionPerformed(evt);
            }
        });

        a_numeric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_numericActionPerformed(evt);
            }
        });

        a_tense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_tenseActionPerformed(evt);
            }
        });

        jLabel324.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel324.setText("Honor");

        a_honor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_honorActionPerformed(evt);
            }
        });

        jLabel328.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel328.setText("Person");

        a_person.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_personActionPerformed(evt);
            }
        });

        jLabel349.setText("Numerics");

        a_sub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_subActionPerformed(evt);
            }
        });

        jLabel353.setText("Subject");

        a_leg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_legActionPerformed(evt);
            }
        });

        jLabel356.setText("Legs");

        jLabel355.setText("Hands");

        a_hand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_handActionPerformed(evt);
            }
        });

        a_wing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_wingActionPerformed(evt);
            }
        });

        jLabel354.setText("Wings");

        a_emphasis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_emphasisActionPerformed(evt);
            }
        });

        jLabel334.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel334.setText("Emphasis");

        javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
        jPanel123.setLayout(jPanel123Layout);
        jPanel123Layout.setHorizontalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel323)
                    .addComponent(jLabel321)
                    .addComponent(jLabel322)
                    .addComponent(jLabel324)
                    .addComponent(jLabel328)
                    .addComponent(jLabel334))
                .addGap(18, 18, 18)
                .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a_alive, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel123Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a_number, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_agent, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(a_honor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_person, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_emphasis, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel123Layout.createSequentialGroup()
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel355)
                            .addComponent(jLabel354)
                            .addComponent(jLabel356))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(a_wing, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_leg, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_hand, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel123Layout.createSequentialGroup()
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel123Layout.createSequentialGroup()
                                .addComponent(jLabel327)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel353)
                                .addComponent(jLabel349)))
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel123Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(a_tense, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a_numeric, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(a_sub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanel123Layout.setVerticalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel123Layout.createSequentialGroup()
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel123Layout.createSequentialGroup()
                                .addComponent(a_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_agent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_alive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel123Layout.createSequentialGroup()
                                .addComponent(jLabel321)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel322)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel323)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel324)
                            .addComponent(a_honor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel328)
                            .addComponent(a_person, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel334)
                            .addComponent(a_emphasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel123Layout.createSequentialGroup()
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel123Layout.createSequentialGroup()
                                .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(a_tense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel327))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_numeric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel349))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_sub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel353))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_leg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel356))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_hand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel355))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_wing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel354))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        a_adult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_adultActionPerformed(evt);
            }
        });

        jPanel130.setBackground(new java.awt.Color(204, 255, 204));

        jLabel359.setText("Animate: 1");

        jLabel360.setText("Human: 1");

        jLabel361.setText("Gender- Mas: 1,Femi: 2");

        jLabel362.setText("Honorable: 1 ");

        jLabel363.setText("Alive: 1");

        jLabel364.setText("Agent: 1");

        jLabel365.setText("Intelligent: 1");

        jLabel366.setText("Adult: 1");

        jLabel367.setText("Non Property: 0");

        jLabel368.setText("Dont'care : -1");

        javax.swing.GroupLayout jPanel130Layout = new javax.swing.GroupLayout(jPanel130);
        jPanel130.setLayout(jPanel130Layout);
        jPanel130Layout.setHorizontalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel130Layout.createSequentialGroup()
                        .addComponent(jLabel365)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel366))
                    .addGroup(jPanel130Layout.createSequentialGroup()
                        .addComponent(jLabel359)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel130Layout.createSequentialGroup()
                                .addComponent(jLabel364)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel362))
                            .addGroup(jPanel130Layout.createSequentialGroup()
                                .addComponent(jLabel360)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel361))))
                    .addComponent(jLabel363)
                    .addComponent(jLabel367)
                    .addComponent(jLabel368))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel130Layout.setVerticalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel359)
                    .addComponent(jLabel360)
                    .addComponent(jLabel361))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel362)
                    .addComponent(jLabel363)
                    .addComponent(jLabel364))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel365)
                    .addComponent(jLabel366))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel367)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel368)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jLabel376.setForeground(new java.awt.Color(204, 51, 0));
        jLabel376.setText("Choose values carefully for semantics");

        jLabel377.setForeground(new java.awt.Color(255, 0, 51));
        jLabel377.setText("Hints: ");

        btn_insert.setBackground(new java.awt.Color(255, 102, 102));
        btn_insert.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn_insert.setText("Insert New Word");
        btn_insert.setBorderPainted(false);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_insert2.setBackground(new java.awt.Color(255, 102, 102));
        btn_insert2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn_insert2.setText("Insert New Word");
        btn_insert2.setBorderPainted(false);
        btn_insert2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insert2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel121Layout = new javax.swing.GroupLayout(jPanel121);
        jPanel121.setLayout(jPanel121Layout);
        jPanel121Layout.setHorizontalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel319)
                            .addComponent(jLabel318)
                            .addComponent(jLabel317)
                            .addComponent(jLabel315)
                            .addComponent(jLabel316))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a_hum, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_anim, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_adult, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_intelligent, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel121Layout.createSequentialGroup()
                                .addComponent(jLabel377)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel376))))
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(btn_insert2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel121Layout.setVerticalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel121Layout.createSequentialGroup()
                                .addComponent(a_anim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_hum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_adult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel121Layout.createSequentialGroup()
                                .addComponent(jLabel315)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel316)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel317)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel318)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel319)
                            .addComponent(a_intelligent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel121Layout.createSequentialGroup()
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel377)
                            .addComponent(jLabel376))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btn_insert2)
                .addGap(38, 38, 38)
                .addComponent(btn_insert)
                .addContainerGap())
        );

        Label_Noun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Noun.setText("Word");

        Label_Noun1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Noun1.setText("Tag");

        javax.swing.GroupLayout jPanel122Layout = new javax.swing.GroupLayout(jPanel122);
        jPanel122.setLayout(jPanel122Layout);
        jPanel122Layout.setHorizontalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel122Layout.createSequentialGroup()
                .addGroup(jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel122Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(a_Word, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel122Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(Label_Noun1))
                    .addGroup(jPanel122Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(a_tag_Word, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel122Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(Label_Noun)))
                .addGap(18, 18, 18)
                .addComponent(jPanel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel122Layout.setVerticalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel122Layout.createSequentialGroup()
                .addGroup(jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel122Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Label_Noun, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(a_Word, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label_Noun1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(a_tag_Word, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
        jPanel119.setLayout(jPanel119Layout);
        jPanel119Layout.setHorizontalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel122, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel119Layout.setVerticalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel122, javax.swing.GroupLayout.PREFERRED_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tab.addTab("Add", jPanel119);

        jPanel124.setBackground(new java.awt.Color(204, 204, 255));

        a_Word1.setFont(new java.awt.Font("SolaimanLipi", 0, 12)); // NOI18N
        a_Word1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_Word1ActionPerformed(evt);
            }
        });

        a_tag_Word1.setFont(new java.awt.Font("Siyam Rupali", 0, 12)); // NOI18N
        a_tag_Word1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_tag_Word1ActionPerformed(evt);
            }
        });

        jLabel325.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel325.setText("Animate");

        jLabel326.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel326.setText("Human");

        jLabel329.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel329.setText("Gender");

        jLabel330.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel330.setText("Adult");

        jLabel331.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel331.setText("Intelligent");

        a_anim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_anim1ActionPerformed(evt);
            }
        });

        a_hum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_hum1ActionPerformed(evt);
            }
        });

        a_gender1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_gender1ActionPerformed(evt);
            }
        });

        a_intelligent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_intelligent1ActionPerformed(evt);
            }
        });

        jLabel332.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel332.setText("Number");

        jLabel333.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel333.setText("Agent");

        jLabel335.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel335.setText("Alive");

        jLabel336.setText("Tense");

        a_number1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_number1ActionPerformed(evt);
            }
        });

        a_agent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_agent1ActionPerformed(evt);
            }
        });

        a_alive1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_alive1ActionPerformed(evt);
            }
        });

        a_numeric1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_numeric1ActionPerformed(evt);
            }
        });

        a_tense1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_tense1ActionPerformed(evt);
            }
        });

        jLabel337.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel337.setText("Honor");

        a_honor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_honor1ActionPerformed(evt);
            }
        });

        jLabel338.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel338.setText("Person");

        a_person1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_person1ActionPerformed(evt);
            }
        });

        jLabel350.setText("Numerics");

        a_sub1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_sub1ActionPerformed(evt);
            }
        });

        jLabel357.setText("Subject");

        a_leg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_leg1ActionPerformed(evt);
            }
        });

        jLabel358.setText("Legs");

        jLabel386.setText("Hands");

        a_hand1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_hand1ActionPerformed(evt);
            }
        });

        a_wing1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_wing1ActionPerformed(evt);
            }
        });

        jLabel387.setText("Wings");

        a_emphasis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_emphasis1ActionPerformed(evt);
            }
        });

        jLabel339.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel339.setText("Emphasis");

        javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
        jPanel133.setLayout(jPanel133Layout);
        jPanel133Layout.setHorizontalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel133Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel335)
                    .addComponent(jLabel332)
                    .addComponent(jLabel333)
                    .addComponent(jLabel337)
                    .addComponent(jLabel338)
                    .addComponent(jLabel339))
                .addGap(18, 18, 18)
                .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a_alive1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel133Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a_number1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_agent1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(a_honor1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_person1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_emphasis1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel133Layout.createSequentialGroup()
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel386)
                            .addComponent(jLabel387)
                            .addComponent(jLabel358))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(a_wing1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_leg1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel133Layout.createSequentialGroup()
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel133Layout.createSequentialGroup()
                                .addComponent(jLabel336)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel357)
                                .addComponent(jLabel350)))
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel133Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(a_tense1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a_numeric1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(a_sub1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanel133Layout.setVerticalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel133Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel133Layout.createSequentialGroup()
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel133Layout.createSequentialGroup()
                                .addComponent(a_number1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_agent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_alive1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel133Layout.createSequentialGroup()
                                .addComponent(jLabel332)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel333)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel335)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel337)
                            .addComponent(a_honor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel338)
                            .addComponent(a_person1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel339)
                            .addComponent(a_emphasis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel133Layout.createSequentialGroup()
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel133Layout.createSequentialGroup()
                                .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(a_tense1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel336))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_numeric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel350))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_sub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel357))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_leg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel358))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel386))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a_wing1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel387))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        a_adult1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_adult1ActionPerformed(evt);
            }
        });

        jPanel134.setBackground(new java.awt.Color(204, 255, 204));

        jLabel388.setText("Animate: 1");

        jLabel389.setText("Human: 1");

        jLabel390.setText("Gender- Mas: 1,Femi: 2");

        jLabel391.setText("Honorable: 1 ");

        jLabel392.setText("Alive: 1");

        jLabel393.setText("Agent: 1");

        jLabel394.setText("Intelligent: 1");

        jLabel395.setText("Adult: 1");

        jLabel396.setText("Non Property: 0");

        jLabel397.setText("Dont'care : -1");

        javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
        jPanel134.setLayout(jPanel134Layout);
        jPanel134Layout.setHorizontalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel134Layout.createSequentialGroup()
                        .addComponent(jLabel394)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel395))
                    .addGroup(jPanel134Layout.createSequentialGroup()
                        .addComponent(jLabel388)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel134Layout.createSequentialGroup()
                                .addComponent(jLabel393)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel391))
                            .addGroup(jPanel134Layout.createSequentialGroup()
                                .addComponent(jLabel389)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel390))))
                    .addComponent(jLabel392)
                    .addComponent(jLabel396)
                    .addComponent(jLabel397))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel134Layout.setVerticalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel388)
                    .addComponent(jLabel389)
                    .addComponent(jLabel390))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel391)
                    .addComponent(jLabel392)
                    .addComponent(jLabel393))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel394)
                    .addComponent(jLabel395))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel396)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel397))
        );

        jLabel399.setForeground(new java.awt.Color(204, 51, 0));
        jLabel399.setText("Choose values carefully for semantics");

        jLabel400.setForeground(new java.awt.Color(255, 0, 51));
        jLabel400.setText("Hints: ");

        jLabel1.setText("Coose row from table");

        javax.swing.GroupLayout jPanel129Layout = new javax.swing.GroupLayout(jPanel129);
        jPanel129.setLayout(jPanel129Layout);
        jPanel129Layout.setHorizontalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel331)
                    .addComponent(jLabel330)
                    .addComponent(jLabel329)
                    .addComponent(jLabel325)
                    .addComponent(jLabel326))
                .addGap(18, 18, 18)
                .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a_hum1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_anim1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_gender1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_adult1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a_intelligent1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel129Layout.createSequentialGroup()
                        .addComponent(jLabel400, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel399))
                    .addComponent(jLabel1))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel129Layout.setVerticalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel129Layout.createSequentialGroup()
                        .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel129Layout.createSequentialGroup()
                                .addComponent(a_anim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_hum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_gender1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(a_adult1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel129Layout.createSequentialGroup()
                                .addComponent(jLabel325)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel326)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel329)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel330)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel331)
                            .addComponent(a_intelligent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel129Layout.createSequentialGroup()
                        .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel400)
                            .addComponent(jLabel399))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Label_Noun4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Noun4.setText("Word");

        Label_Noun5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Noun5.setText("Tag");

        btn_update.setBackground(new java.awt.Color(255, 102, 102));
        btn_update.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn_update.setText("Update");
        btn_update.setBorderPainted(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel128Layout = new javax.swing.GroupLayout(jPanel128);
        jPanel128.setLayout(jPanel128Layout);
        jPanel128Layout.setHorizontalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addGroup(jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel128Layout.createSequentialGroup()
                        .addGroup(jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel128Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(a_Word1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel128Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(Label_Noun5))
                            .addGroup(jPanel128Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(a_tag_Word1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel128Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(Label_Noun4)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel128Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel128Layout.setVerticalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Noun4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(a_Word1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Label_Noun5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_tag_Word1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addComponent(jPanel129, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_update)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
        jPanel124.setLayout(jPanel124Layout);
        jPanel124Layout.setHorizontalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel124Layout.setVerticalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tab.addTab("Update", jPanel124);

        javax.swing.GroupLayout jPanel118Layout = new javax.swing.GroupLayout(jPanel118);
        jPanel118.setLayout(jPanel118Layout);
        jPanel118Layout.setHorizontalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tab, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel118Layout.setVerticalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tab, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
        jPanel116.setLayout(jPanel116Layout);
        jPanel116Layout.setHorizontalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel118, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel116Layout.setVerticalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel116, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void a_WordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_WordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_WordActionPerformed

    private void a_tag_WordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_tag_WordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_tag_WordActionPerformed

    private void a_animActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_animActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_animActionPerformed

    private void a_humActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_humActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_humActionPerformed

    private void a_genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_genderActionPerformed

    private void a_intelligentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_intelligentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_intelligentActionPerformed

    private void a_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_numberActionPerformed

    private void a_agentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_agentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_agentActionPerformed

    private void a_aliveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_aliveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_aliveActionPerformed

    private void a_numericActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_numericActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_numericActionPerformed

    private void a_tenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_tenseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_tenseActionPerformed

    private void a_honorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_honorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_honorActionPerformed

    private void a_personActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_personActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_personActionPerformed

    private void a_subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_subActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_subActionPerformed

    private void a_legActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_legActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_legActionPerformed

    private void a_handActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_handActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_handActionPerformed

    private void a_wingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_wingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_wingActionPerformed

    private void a_emphasisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_emphasisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_emphasisActionPerformed

    private void a_adultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_adultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_adultActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed

      
    }//GEN-LAST:event_btn_insertActionPerformed

    private void a_Word1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_Word1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_Word1ActionPerformed

    private void a_tag_Word1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_tag_Word1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_tag_Word1ActionPerformed

    private void a_anim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_anim1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_anim1ActionPerformed

    private void a_hum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_hum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_hum1ActionPerformed

    private void a_gender1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_gender1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_gender1ActionPerformed

    private void a_intelligent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_intelligent1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_intelligent1ActionPerformed

    private void a_number1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_number1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_number1ActionPerformed

    private void a_agent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_agent1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_agent1ActionPerformed

    private void a_alive1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_alive1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_alive1ActionPerformed

    private void a_numeric1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_numeric1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_numeric1ActionPerformed

    private void a_tense1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_tense1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_tense1ActionPerformed

    private void a_honor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_honor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_honor1ActionPerformed

    private void a_person1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_person1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_person1ActionPerformed

    private void a_sub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_sub1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_sub1ActionPerformed

    private void a_leg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_leg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_leg1ActionPerformed

    private void a_hand1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_hand1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_hand1ActionPerformed

    private void a_wing1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_wing1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_wing1ActionPerformed

    private void a_emphasis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_emphasis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_emphasis1ActionPerformed

    private void a_adult1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_adult1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a_adult1ActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
          try
        {
             String word = a_Word1.getText().toString().trim();
             String tag = a_tag_Word1.getText().toString().trim();
             String animate = a_anim1.getText().toString().trim();
                        if(animate.equals(""))
                            animate = ""+-1;
                        String human = a_hum1.getText().toString().trim();
                        if(human.equals(""))
                            human = ""+-1;
                        String gender = a_gender1.getText().toString().trim();
                        if(gender.equals(""))
                            gender = ""+-1;
                        String adult = a_adult1.getText().toString().trim();
                        if(adult.equals(""))
                            adult =""+-1;
                        String intelligent = a_intelligent1.getText().toString().trim();
                         if(intelligent.equals(""))
                             intelligent = ""+-1;
                        String number = a_number1.getText().toString().trim();
                        if(number.equals(""))
                            number = ""+-1;
                        String agent = a_agent1.getText().toString().trim();
                        if(agent.equals(""))
                            agent = ""+-1;
                        String alive = a_alive1.getText().toString().trim();
                        if(alive.equals(""))
                            alive = ""+-1;
                        String honor = a_honor1.getText().toString().trim();
                        if(honor.equals(""))
                            honor = ""+-1;
                        String person = a_person1.getText().toString().trim();
                        if(person.equals(""))
                            person = ""+-1;
                        String emphasis = a_emphasis1.getText().toString().trim();
                        if(emphasis.equals(""))
                            emphasis = ""+-1;
                        String tense = a_tense1.getText().toString().trim();
                        if(tense.equals(""))
                            tense = ""+-1;
                        String numeric = a_numeric1.getText().toString().trim();
                        if(numeric.equals(""))
                            numeric = ""+-1;
                        String subject = a_sub1.getText().toString().trim();
                        if(subject.equals(""))
                           subject = ""+-1;
                        String legs = a_leg1.getText().toString().trim();
                        if(legs.equals(""))
                            legs = ""+-1;
                        String hands = a_hand1.getText().toString().trim();
                        if(hands.equals(""))
                            hands = ""+-1;
                        String wings = a_wing1.getText().toString().trim();
                        if(wings.equals(""))
                            wings = ""+-1;
                        System.out.println("word "+word);
                        System.out.println("tag "+tag);
                        System.out.println("anim "+animate);
                        System.out.println("hum "+human);
                        System.out.println("gender "+gender);
                        System.out.println("adult "+adult);
                        System.out.println("intelligent "+intelligent);
                        System.out.println("number "+number);
                        System.out.println("agent "+agent);
                        System.out.println("alive "+alive);
                        System.out.println("honor "+honor);
                        System.out.println("person "+person);
                        System.out.println("emp "+emphasis);
                        System.out.println("tense  "+tense);
                        System.out.println("numeric "+numeric);
                        System.out.println("sub "+subject);
                        System.out.println("legs "+legs);
                        System.out.println("hands "+hands);
                        System.out.println("wings "+wings);

                        String sql = SqlQuery.INSERT_NEW_WORD(word, tag, animate, human, gender, adult, intelligent, number, agent, alive,
                            honor, person, emphasis, tense, numeric, subject, legs, hands, wings, "U");
            System.out.println("sql of update: "+sql);
            preparedStatement = connection.prepareStatement(sql);
          preparedStatement.executeUpdate();
          JOptionPane.showMessageDialog(null,"Data Updated Successfully!");   
                    a_Word1.setText("");
                    a_tag_Word1.setText("");
                    a_anim1.setText("");
                    a_hum1.setText("");
                    a_gender1.setText("");
                    a_adult1.setText("");
                    a_intelligent1.setText("");
                    a_number1.setText("");
                    a_agent1.setText("");
                    a_alive1.setText("");
                    a_honor1.setText("");
                    a_person1.setText("");
                    a_emphasis1.setText("");
                    a_tense1.setText("");
                    a_numeric1.setText("");
                    a_sub1.setText("");
                    a_leg1.setText("");
                    a_hand1.setText("");
                    a_wing1.setText("");
                  ShowDatabaseTable();  

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
       
    }//GEN-LAST:event_btn_updateActionPerformed

    private void filterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterKeyReleased
        // TODO add your handling code here:
        String query = filter.getText().trim();
        filter(query);
    }//GEN-LAST:event_filterKeyReleased

    private void Table_dbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_dbMouseClicked
        int Tab_index = Tab.getSelectedIndex();
        int row = Table_db.getSelectedRow();
        int column = Table_db.getSelectedColumn();
        String value = Table_db.getValueAt(row, column).toString();
        System.out.println("Tab index"+Tab_index);
        System.out.println("Title: "+value+" row "+row+" column "+column);
        word_from_table = Table_db.getValueAt(row, 1).toString();
        tag_from_table = Table_db.getValueAt(row, 2).toString();
        if(Tab_index==1)
        {
            try
            {
                a_Word1.setText(word_from_table);
                a_tag_Word1.setText(tag_from_table);
                a_anim1.setText(Table_db.getValueAt(row, 3).toString());
                a_hum1.setText(Table_db.getValueAt(row, 4).toString());
                a_gender1.setText(Table_db.getValueAt(row, 5).toString());
                a_adult1.setText(Table_db.getValueAt(row, 6).toString());
                a_intelligent1.setText(Table_db.getValueAt(row, 7).toString());
                a_number1.setText(Table_db.getValueAt(row, 8).toString());
                a_agent1.setText(Table_db.getValueAt(row, 9).toString());
                a_alive1.setText(Table_db.getValueAt(row, 10).toString());
                a_honor1.setText(Table_db.getValueAt(row, 11).toString());
                a_person1.setText(Table_db.getValueAt(row, 12).toString());
                a_emphasis1.setText(Table_db.getValueAt(row, 13).toString());
                a_tense1.setText(Table_db.getValueAt(row, 14).toString());
                a_numeric1.setText(Table_db.getValueAt(row, 15).toString());
                a_sub1.setText(Table_db.getValueAt(row, 16).toString());
                a_leg1.setText(Table_db.getValueAt(row, 17).toString());
                a_hand1.setText(Table_db.getValueAt(row, 18).toString());
                a_wing1.setText(Table_db.getValueAt(row, 19).toString());

            }

            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }//GEN-LAST:event_Table_dbMouseClicked

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        // TODO add your handling code here:
       // Dictionary ob = new Dictionary();
        int confirm = JOptionPane.showConfirmDialog (null, "Delete?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) { 
            try {
                System.out.println("Word "+word_from_table);
                System.out.println("Tag "+tag_from_table);
                String sql = SqlQuery.DELETE(word_from_table, tag_from_table);
                connection = ConnectDB.getConnection(SqlQuery.DB_semantic_feature);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
                        System.out.println("Sql for Dlete: "+sql);
                       ShowDatabaseTable();
                       JOptionPane.showMessageDialog(null, "Data Deleted Successfully!");
                       
            } catch (SQLException ex) {
                Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            }
              
            }
    }//GEN-LAST:event_deleteMouseClicked

    private void btn_insert2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insert2ActionPerformed
        // TODO add your handling code here:
                try
        {
            String word = a_Word.getText().toString().trim();
            String tag = a_tag_Word.getText().toString().trim();
            if(word.equals("")||tag.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter Word and Tag.");
            }
            else
            {
                boolean f = Check_Before_Insert(word, tag);
                System.out.println("f: "+f);
                if(f)
                {
                    
                    String msg = "<html><font face='Siyam Rupali' color='red'>"+word+" With tag "+tag+" already exists in Dictionary!</font></html>";
                    System.out.println("msg: "+tag);
                   
                    JOptionPane jopt = new JOptionPane();
                    jopt.setFont(new Font("Siyam Rupali", Font.BOLD, 20));
                    jopt.showMessageDialog(null, msg);
                   
                }
                else
                {
                        String animate = a_anim.getText().toString().trim();
                        if(animate.equals(""))
                            animate = ""+-1;
                        String human = a_hum.getText().toString().trim();
                        if(human.equals(""))
                            human = ""+-1;
                        String gender = a_gender.getText().toString().trim();
                        if(gender.equals(""))
                            gender = ""+-1;
                        String adult = a_adult.getText().toString().trim();
                        if(adult.equals(""))
                            adult =""+-1;
                        String intelligent = a_intelligent.getText().toString().trim();
                         if(intelligent.equals(""))
                             intelligent = ""+-1;
                        String number = a_number.getText().toString().trim();
                        if(number.equals(""))
                            number = ""+-1;
                        String agent = a_agent.getText().toString().trim();
                        if(agent.equals(""))
                            agent = ""+-1;
                        String alive = a_alive.getText().toString().trim();
                        if(alive.equals(""))
                            alive = ""+-1;
                        String honor = a_honor.getText().toString().trim();
                        if(honor.equals(""))
                            honor = ""+-1;
                        String person = a_person.getText().toString().trim();
                        if(person.equals(""))
                            person = ""+-1;
                        String emphasis = a_emphasis.getText().toString().trim();
                        if(emphasis.equals(""))
                            emphasis = ""+-1;
                        String tense = a_tense.getText().toString().trim();
                        if(tense.equals(""))
                            tense = ""+-1;
                        String numeric = a_numeric.getText().toString().trim();
                        if(numeric.equals(""))
                            numeric = ""+-1;
                        String subject = a_sub.getText().toString().trim();
                        if(subject.equals(""))
                           subject = ""+-1;
                        String legs = a_leg.getText().toString().trim();
                        if(legs.equals(""))
                            legs = ""+-1;
                        String hands = a_hand.getText().toString().trim();
                        if(hands.equals(""))
                            hands = ""+-1;
                        String wings = a_wing.getText().toString().trim();
                        if(wings.equals(""))
                            wings = ""+-1;

                        System.out.println("word "+word);
                        System.out.println("tag "+tag);
                        System.out.println("anim "+animate);
                        System.out.println("hum "+human);
                        System.out.println("gender "+gender);
                        System.out.println("adult "+adult);
                        System.out.println("intelligent "+intelligent);
                        System.out.println("number "+number);
                        System.out.println("agent "+agent);
                        System.out.println("alive "+alive);
                        System.out.println("honor "+honor);
                        System.out.println("person "+person);
                        System.out.println("emp "+emphasis);
                        System.out.println("tense  "+tense);
                        System.out.println("numeric "+numeric);
                        System.out.println("sub "+subject);
                        System.out.println("legs "+legs);
                        System.out.println("hands "+hands);
                        System.out.println("wings "+wings);

                        String sql = SqlQuery.INSERT_NEW_WORD(word, tag, animate, human, gender, adult, intelligent, number, agent, alive,
                            honor, person, emphasis, tense, numeric, subject, legs, hands, wings, "I");
                        System.out.println("sql of insert: "+sql);
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate(); a_Word.setText("");
                 
                        JOptionPane.showMessageDialog(null,"Data Inserted Successfully!");  
                        ShowDatabaseTable();          
                }    
                    a_Word.setText("");
                    a_tag_Word.setText("");
                    a_anim.setText("");
                    a_hum.setText("");
                    a_gender.setText("");
                    a_adult.setText("");
                    a_intelligent.setText("");
                    a_number.setText("");
                    a_agent.setText("");
                    a_alive.setText("");
                    a_honor.setText("");
                    a_person.setText("");
                    a_emphasis.setText("");
                    a_tense.setText("");
                    a_numeric.setText("");
                    a_sub.setText("");
                    a_leg.setText("");
                    a_hand.setText("");
                    a_wing.setText("");
                  
                   // a_tag_Word.setText("");
                
            }
                  
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btn_insert2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Dictionary ob = new Dictionary();
               ob.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_Noun;
    private javax.swing.JLabel Label_Noun1;
    private javax.swing.JLabel Label_Noun4;
    private javax.swing.JLabel Label_Noun5;
    private javax.swing.JTabbedPane Tab;
    private javax.swing.JTable Table_db;
    private javax.swing.JLabel Total_Noun;
    private javax.swing.JLabel Total_Noun1;
    private javax.swing.JLabel Total_Noun10;
    private javax.swing.JLabel Total_Noun11;
    private javax.swing.JLabel Total_Noun12;
    private javax.swing.JLabel Total_Noun13;
    private javax.swing.JLabel Total_Noun14;
    private javax.swing.JLabel Total_Noun2;
    private javax.swing.JLabel Total_Noun3;
    private javax.swing.JLabel Total_Noun4;
    private javax.swing.JLabel Total_Noun5;
    private javax.swing.JLabel Total_Noun6;
    private javax.swing.JLabel Total_Noun7;
    private javax.swing.JLabel Total_Noun8;
    private javax.swing.JLabel Total_Noun9;
    private javax.swing.JTextField a_Word;
    private javax.swing.JTextField a_Word1;
    private javax.swing.JTextField a_adult;
    private javax.swing.JTextField a_adult1;
    private javax.swing.JTextField a_agent;
    private javax.swing.JTextField a_agent1;
    private javax.swing.JTextField a_alive;
    private javax.swing.JTextField a_alive1;
    private javax.swing.JTextField a_anim;
    private javax.swing.JTextField a_anim1;
    private javax.swing.JTextField a_emphasis;
    private javax.swing.JTextField a_emphasis1;
    private javax.swing.JTextField a_gender;
    private javax.swing.JTextField a_gender1;
    private javax.swing.JTextField a_hand;
    private javax.swing.JTextField a_hand1;
    private javax.swing.JTextField a_honor;
    private javax.swing.JTextField a_honor1;
    private javax.swing.JTextField a_hum;
    private javax.swing.JTextField a_hum1;
    private javax.swing.JTextField a_intelligent;
    private javax.swing.JTextField a_intelligent1;
    private javax.swing.JTextField a_leg;
    private javax.swing.JTextField a_leg1;
    private javax.swing.JTextField a_number;
    private javax.swing.JTextField a_number1;
    private javax.swing.JTextField a_numeric;
    private javax.swing.JTextField a_numeric1;
    private javax.swing.JTextField a_person;
    private javax.swing.JTextField a_person1;
    private javax.swing.JTextField a_sub;
    private javax.swing.JTextField a_sub1;
    private javax.swing.JTextField a_tag_Word;
    private javax.swing.JTextField a_tag_Word1;
    private javax.swing.JTextField a_tense;
    private javax.swing.JTextField a_tense1;
    private javax.swing.JTextField a_wing;
    private javax.swing.JTextField a_wing1;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_insert2;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel count_adj;
    private javax.swing.JLabel count_adverb;
    private javax.swing.JLabel count_aux;
    private javax.swing.JLabel count_biv;
    private javax.swing.JLabel count_conj;
    private javax.swing.JLabel count_neg;
    private javax.swing.JLabel count_noun;
    private javax.swing.JLabel count_num;
    private javax.swing.JLabel count_postfix;
    private javax.swing.JLabel count_prifix;
    private javax.swing.JLabel count_pronoun;
    private javax.swing.JLabel count_subcom;
    private javax.swing.JLabel count_subord;
    private javax.swing.JLabel count_total;
    private javax.swing.JLabel count_verb;
    private javax.swing.JLabel delete;
    private javax.swing.JTextField filter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel331;
    private javax.swing.JLabel jLabel332;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel334;
    private javax.swing.JLabel jLabel335;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel358;
    private javax.swing.JLabel jLabel359;
    private javax.swing.JLabel jLabel360;
    private javax.swing.JLabel jLabel361;
    private javax.swing.JLabel jLabel362;
    private javax.swing.JLabel jLabel363;
    private javax.swing.JLabel jLabel364;
    private javax.swing.JLabel jLabel365;
    private javax.swing.JLabel jLabel366;
    private javax.swing.JLabel jLabel367;
    private javax.swing.JLabel jLabel368;
    private javax.swing.JLabel jLabel376;
    private javax.swing.JLabel jLabel377;
    private javax.swing.JLabel jLabel386;
    private javax.swing.JLabel jLabel387;
    private javax.swing.JLabel jLabel388;
    private javax.swing.JLabel jLabel389;
    private javax.swing.JLabel jLabel390;
    private javax.swing.JLabel jLabel391;
    private javax.swing.JLabel jLabel392;
    private javax.swing.JLabel jLabel393;
    private javax.swing.JLabel jLabel394;
    private javax.swing.JLabel jLabel395;
    private javax.swing.JLabel jLabel396;
    private javax.swing.JLabel jLabel397;
    private javax.swing.JLabel jLabel399;
    private javax.swing.JLabel jLabel400;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}


