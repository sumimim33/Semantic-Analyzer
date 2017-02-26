/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Sumi Mim
 */
public class Table {
    public static String DATABASE_NAME = "semantic_feature_bangla";
    
    public static String dont_care_feature ="Don't care";
    //**********Noun****************//
     public static String Table_noun = "nouninfo";
     public static String noun_SL ="SL";
     public static String noun_word ="Word";
     public static String noun_Animate ="Animate";
     public static String noun_Human ="Human";
     public static String noun_Gender ="Gender";
     public static String noun_Alive ="Alive";
     public static String noun_Agent ="Agent";
     public static String noun_Honor ="Honor";
     public static String noun_Intelligence="Intelligence";
     public static String noun_Adult ="Adult";
     public static String noun_Leg ="Leg";
     public static String noun_Hands ="Hands";
     public static String noun_Wings ="Wings";
     public static String noun_count_rows ="Total Nouns";
     //*********If change Add noun In UI then just chnage It******//
     public static String noun_anim_feature1 ="Animate";
     public static String noun_anim_feature2 ="Non-animate";
     
     public static String noun_human_feature1 ="Human";
     public static String noun_human_feature2 ="Non-human";
     
      public static String noun_gender_feature1 ="Masculine";
      public static String noun_gender_feature2 ="Feminine";
     
     public static String noun_alive_feature1 ="Alive";
     public static String noun_alive_feature2 ="Not-alive";
     
     public static String noun_agent_feature1 ="Agent";
     public static String noun_agent_feature2 ="Not-agent";
     
     public static String noun_honor_feature1 ="Honorable";
     public static String noun_honor_feature2 ="Not-honorable";
          
     public static String noun_intelligent_feature1 ="Intelligent";
     public static String noun_intelligent_feature2 ="Not-intelligent";
     
      public static String noun_adult_feature1 ="Adult";
      public static String noun_adult_feature2 ="Not-adult";
      
      public static String person_feature1 = "First";
      public static String person_feature2 = "Second";
      public static String person_feature3 = "Third";
      
      public static String number_feature1 = "Singuler";
      public static String number_feature2 = "Plural";
      
   
    //**********Pronoun****************// 
    public static String Table_pronoun = "pronouninfo";
    public static String Pronoun_person ="Person";
    public static String Pronoun_Animate ="Animate";
    public static String Pronoun_Huamn = "Human";
     public static String Pronoun_Number = "Number";
    public static String Pronoun_Honor = "Honor";
    
    
    
    //public static String Table_adjective = "nouninfo";
    public static String Table_verb= "verbinfo";
    public static String TABLE_RULE = "rule";
    
    // Define Tag for POS
    public static String TAG_NOUN = "N"; // Nouns
    public static String TAG_PRONOUN = "PN"; // se, tini
    public static String TAG_ADJECTIVE = "ADJ"; // valo, mondo, sundor
    public static String TAG_VERB = "V"; // ja, kor, kha
    public static String TAG_ADVERB = "ADV"; // druto, aste
    public static String TAG_DET = "DET"; // Ti, ta ,kahan, kahni
    public static String TAG_BIV = "BIV"; // ke, re ,er
    public static String TAG_DD = "DD"; // ei, sei
    public static String TAG_DO= "DO";  // prothom , dibtiyo
    public static String TAG_QFR= "QFR"; // ak, du, tin
    
}
