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
public class Node2Create {
    public static void main(String[] args) {
        System.out.println("Welcome to the Natlang Tree Creater:");
        System.out.println("We will create the node 2 now");
        System.out.println("What are the number of elements you want?");
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        Map<String, List<Integer>> map = new HashMap<>();
        
        for(int i=0;i<n;i++){
            System.out.println("\nBegin Word "+(i+1));
            System.out.println("Enter the main word:");
            String keyword=s.next();
            System.out.print("Enter number of node 1 words group "+(i+1)+" is associated with:");
            int k=s.nextInt();
            System.out.println("Begin associating group "+(i+1)+" with key "+keyword);
            List<Integer> valSet = new ArrayList<>();
            valSet.clear();
            for(int j=0;j<k;j++){
                System.out.println("Enter value number "+(j+1)+" of group:");
                int inp=s.nextInt();
                valSet.add(inp);
            }
            System.out.println("Adding "+valSet+" to Hashmap...");
            map.put(keyword, valSet);
            System.out.println("Done. Current map - "+map);
        }
        System.out.println("Input Complete. Appending to file...");
        try{
            FileOutputStream fout=new FileOutputStream("node2.txt");
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
