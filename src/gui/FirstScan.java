/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author river
 */
public class FirstScan {
    public int check(String str) {
        /* 
            Checks the String for brightness and volume commands.
            Returns 0 if nothing/more than 1 found
            Returns 1 if Volume is found
            Returns 2 if Brightness is found
            Takes Synonyms into account
        */
        boolean isVol=false;
        boolean isBright=false;
        boolean isOpen=false;
        boolean isSearch=false;
        
        String[] volWords={"volume","sound","vol","speaker","speakers"};
        String[] brightWords={"brightness","bright","screen light"};
        String[] openWords={"open","run"};
        String[] searchWords={"search","lookup","google"};
    
        for(String s:volWords){
            if(str.contains(s)){
                isVol=true;
                break;
            }
        }
        
        for(String s:brightWords){
            if(str.contains(s)){
                isBright=true;
                break;
            }
        }
        
            for(String s:openWords){
            if(str.contains(s)){
                isOpen=true;
                break;
            }
        }
        
        for(String s:searchWords){
            if(str.contains(s)){
                isSearch=true;
                break;
            }
        }
            
        if(isVol && !isBright){
            return 1;
        }
        else if(isBright && !isVol){
            return 2;
        }
        else if(isOpen){
            return 3;
        }
        else if(isSearch){
            return 4;
        }
        else{
            return 0;
        }
    }
}
