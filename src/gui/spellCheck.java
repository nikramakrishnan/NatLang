/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.xdrop.fuzzywuzzy.FuzzySearch;

/**
 *
 * @author river
 */
public class spellCheck {
    public List<String> check(List<String> str, Map<Integer, List<String>> n1){
        //Get node 2 synonyms
        Node3Read n3 = new Node3Read();
        Map<String, List<String>> node2syn;
        node2syn = n3.getMap();
        List<String> newStr=new ArrayList<>();
        boolean masterChanged=false;
        for (String word:str) {
            String matchWord=null;
            int greatest=0;
            for (Map.Entry<Integer, List<String>> entry : n1.entrySet()) {
                List<String> values = entry.getValue();
                for(String mapWord:values){
                    int ratio=FuzzySearch.ratio(word,mapWord);
                    if(ratio>greatest) {
                    greatest=ratio;
                    matchWord=mapWord;
                    }
                }
                
            }
            if(matchWord!=null){
                    masterChanged=true;
                    if(greatest>=80){
                        newStr.add(matchWord);
                        masterChanged=true;
                    }
                    else{
                        newStr.add(word);
                    }
                }
                else{
                    newStr.add(word);
                }
        }
        String finalStr=String.join(" ", newStr);
        if(masterChanged){
            System.out.println("Showing results for:"+finalStr);
            return newStr;
        }
        else{
            return str;
        }
    }
}
