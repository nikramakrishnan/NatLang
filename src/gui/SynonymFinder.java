/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import java.util.Map;

/**
 *
 * @author river
 */
public class SynonymFinder {
    public String find(String str) {
        //Get node 2 synonyms
        Node3Read n3 = new Node3Read();
        Map<String, List<String>> node2syn;
        node2syn = n3.getMap();
        
        //Search
        String mainword="";
        boolean found=false;
        for (Map.Entry<String, List<String>> entry : node2syn.entrySet()){
            List<String> words=entry.getValue();
            if(words.contains(str)){
                mainword=entry.getKey();
                //System.out.println("mainword:"+mainword);
                found=true;
                break;
            }
        }
        if(found){
            return mainword;
        }
        else{
            return str;
        }
    }
}
