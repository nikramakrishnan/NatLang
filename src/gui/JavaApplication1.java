/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.util.*;
/**
 *
 * @author Vishen
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
   
        //to get input from the user
        Scanner s=new Scanner(System.in);
       
        String input;
        public void in(String x){
         x=s.nextLine();
        }
        
        // to split the string into an array
        public void split(String x){
        
                    
                    String part[]=x.split(" ");
                    System.out.println(part[0]);
                    System.out.println(part[1]);
            }
        
        
    
    
}
