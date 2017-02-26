/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticAnalyzer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sumi Mim
 */
public class Parsing {
  public Map<String, String> structure = new HashMap<String, String>();
    public Parsing() {
    }
    
     public String StructMap(String rr)
    {
        System.out.println("rr: "+rr);
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
        
        System.out.println("Rullllllll: "+rr);
        while(itr.hasNext())
        {
            r = (String)itr.next();
            s = (String)structure.get(r);
            if(rr.equals(r))
            {
               
                break;
             }
        }
       return s;
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
    public static void main(String[] args)
    {
        Parsing ob = new Parsing();
    }
    
}
