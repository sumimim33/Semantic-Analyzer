/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticAnalyzer;

/**
 *
 * @author Sumi Mim
 */
public class StringMatcher {
   
     boolean StringMatc(String x, String y)
    {
    boolean f = false;
    int lenx = x.length();
    int leny = y.length();
   if(x==y)
   {
          for(int i=0;i<x.length();i++)
       {
        f = x.charAt(i)==y.charAt(i); 
        return  f;
       }
   }
    return f;   
    }
    public static void main(String[] args) {
        String x ="aaa";
        String y ="aaa";
        StringMatcher m = new StringMatcher();
    boolean f = m.StringMatc(x,y);
        System.out.println("f: "+f);
    }
}
