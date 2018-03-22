/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author river
 */
public class Node3Create {
    public static void main(String[] args) {
        System.out.println("Welcome to the Natlang Synonym Creater:");
        System.out.println("We will create the node 2 Synonyms now");
        System.out.println("What are the number of main words?");
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        //Get node 2 synonyms
        /*
        Node3Read n3 = new Node3Read();
        Map<String, List<String>> map;
        map = n3.getMap();
        */
        Map<String, List<String>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            System.out.println("\nBegin Word "+(i+1));
            System.out.println("Enter the main word:");
            String mainword=s.next();
            System.out.print("Enter number of synonyms for main word "+(i+1)+":");
            int k=s.nextInt();
            System.out.println("Begin synonyms input for main word "+mainword);
            List<String> synonyms = new ArrayList<>();
            synonyms.clear();
            for(int j=0;j<k;j++){
                System.out.println("Enter synonym "+(j+1)+" for the word:");
                String inp=s.next();
                synonyms.add(inp);
            }
            System.out.println("Adding "+synonyms+" to Hashmap...");
            map.put(mainword, synonyms);
            System.out.println("Done. Current map - "+map);
        }
        System.out.println("Input Complete. Appending to file...");
        try{
            FileOutputStream fout=new FileOutputStream("node2syn.txt");
            try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(map);
                oos.close();
                System.out.println("Done. Contents added to node2.txt");
            }
    } catch(IOException ex){
            System.out.println("Error:"+ex);
        }   
}
}
