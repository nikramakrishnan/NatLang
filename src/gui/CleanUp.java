/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.regex.*;

/**
 *
 * @author river
 */
public class CleanUp {
    public String cleanStr;
    public String clean(String dirtyStr){
    Pattern pt = Pattern.compile("[^a-zA-Z0-9 ]");
    //dirtyStr=dirtyStr.replace(",", " ");

    Matcher match= pt.matcher(dirtyStr);
    while(match.find())
        {
            String s= match.group();
        dirtyStr=dirtyStr.replaceAll("\\"+s, " ");
        }
        cleanStr = dirtyStr.trim().replaceAll(" +", " ");
        cleanStr=cleanStr.toLowerCase();
        return cleanStr;
    }

    public static void main(String[] args) {
        String lol="Wi-fi on ok%%%%%%%%%%%%%%%%lol";
        CleanUp cu=new CleanUp();
        String s=cu.clean(lol);
        String xpart[] = s.split(" ");
        System.out.println(s);
        
    }

}