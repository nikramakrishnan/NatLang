/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author river
 */
public class RegexParse{
    private String str;
    
    public boolean isSetter(String str){
        /*
        Checks if the supplied string is a setter using regex.
        */
        boolean res,res2,res3,res4,res5;
        //Get the list of synonyms
        String setters="volume|vol|sound|speaker|screen|display|bright|brightness";
        res=str.matches("set.*(\\d+).*");
        res2=str.matches(".*to (\\d+).*");
        res3=str.matches("\\w+\\s+(\\d+)");
        //res3=str.matches(".*(\\d+).*"); //VERY VERY DANGEROUS
        res4=str.matches("(\\d+).*("+setters+").*");
        res5=str.matches(".*("+setters+").*\\d+.*");
        return(res||res2||res3||res4||res5);
    }
    public boolean isIncDec(String str){
        /*
        Checks if the supplied string is a increase/decrease statement using regex.
        */
        boolean res;
        res=str.matches(".*by\\s(\\d+).*");
        return(res);
    }
    public int IncOrDec(String str){
        /*
        Determines the type (increase/decrease) of function
        to perform in the given inc/dec statement.
        */
        int IncOrDec=0;
        String[] inc={"increase","inc","raise"};
        String[] dec={"decrease","dec","reduce"};
        str=str.toLowerCase();
        for(String s:inc){
            if(str.contains(s)){
                return 1;
            }
        }
        for(String s2:dec){
            if(str.contains(s2)){
                return -1;
            }
        }
        return IncOrDec;
    }
    public int getValue(String str){
        /*
        Gets the integer from the supplied string.
        */
        String pattern = "(\\d+)";
        // Create a Pattern object
      Pattern r = Pattern.compile(pattern);
      // Now create matcher object.
      Matcher m = r.matcher(str);
      if (m.find( )) {
         //System.out.println("RegexParse: Found value: " + m.group(0) );
         int num=Integer.parseInt(m.group(0));
         return num;
      }else {
         return 0;
      }

    }
    
    public boolean isOpen(String str){
        boolean res,res2,res3,res4;
        str=str.toLowerCase();
        res=str.matches("(open|run)\\s+(.*)");
        res2=str.matches("(.*)\\s+(open|run)");
        res3=str.matches("/((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)/");
        res4=str.matches(".*(?:open|run).*(\\w+\\.\\w+)");
        return(res||res2||res3|res4);
    }
    
        public String getPath(String str){
        /*
        Gets the integer from the supplied string.
        */
        String path;
        String pattern = "open\\s+(.*)";
         String pattern2 = "(.*)\\s+open";
         String pattern3=".*(?:open|run).*(\\w+\\.\\w+)";
        // Create a Pattern object
      Pattern r = Pattern.compile(pattern);
      Pattern r2 = Pattern.compile(pattern2);
      Pattern r3 = Pattern.compile(pattern3);
      
      // Now create matcher object.
      Matcher m = r.matcher(str);
      Matcher m2 = r2.matcher(str);
      Matcher m3 = r3.matcher(str);
      
      if(m.find()){
          path=m.group(1);
      }
      else if(m2.find()){
          path=m2.group(1);
      }
      else if(m3.find()){
          path=m3.group(1);
          //System.out.println(m3.group(0)+" "+m3.group(1)+" "+m3.group(2));
      }
      else {
         path=null;
      }
      return path;
    }
        
    public boolean isSearch(String str){
        boolean res,res2,res3;
        str=str.toLowerCase();
        res=str.matches("(search|lookup|google)\\s+(.*)");
        res2=str.matches("(.*)\\s+(search|lookup|google)");
        return(res||res2);
    }
    
    public String getSearchQ(String str){
        /*
        Gets the search term from the supplied string.
        */
        String pattern = "(?:search|lookup|google)\\s+(.*)";
        String pattern2 = "(.*)\\s+(?:search|lookup|google)";
        String search;
        // Create a Pattern object
      Pattern r = Pattern.compile(pattern);
      Pattern r2 = Pattern.compile(pattern2);
      // Now create matcher object.
      Matcher m = r.matcher(str);
      Matcher m2 = r2.matcher(str);
      if(m.find()){
          search=m.group(1);
      }
      else if(m2.find()){
          search=m2.group(1);
      }
      else {
         search=null;
      }
      return search;
    }
    
    public static void main(String[] args) {
        /*
        Test
        */
        String s1="increase the volume to 50%";
        String s2="lookup my name^";
        String s3="";
        RegexParse rp = new RegexParse();
        //System.out.println(rp.isIncDec(s2));
       // System.out.println(rp.isSetter(s2));
        System.out.println(rp.getSearchQ(s2));
        //rp.getValue(s2);
    }
}
