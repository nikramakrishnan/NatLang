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
public class Node1Create {
    public static void main(String[] args) {
        System.out.println("Welcome to the NatLang Tree Creater:");
        System.out.println("We will append to node 1");
        System.out.println("What are the number of elements you want? (Excluding synonyms)");
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        //Get node 1
        Node1Read n1 = new Node1Read();
        Map<Integer, List<String>> map;
        map = n1.getNode1();
        int size=map.size();
        //Map<Integer, List<String>> map = new HashMap<>();
        
        for(int i=size;i<size+n;i++){
            System.out.println("\nBegin Word "+(i+1));
            System.out.print("Enter number of total words for group "+(i+1)+":");
            int k=s.nextInt();
            System.out.println("Begin group "+(i+1));
            List<String> valSet = new ArrayList<>();
            valSet.clear();
            for(int j=0;j<k;j++){
                System.out.println("Enter element "+(j+1)+" of group:");
                String inp=s.next();
                valSet.add(inp);
            }
            System.out.println("Adding "+valSet+" to Hashmap...");
            map.put((i+1), valSet);
            System.out.println("Done. Current map - "+map);
        }
        System.out.println("Input Complete. Appending to file...");
        try{
            FileOutputStream fout=new FileOutputStream("node1.txt");
            try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(map);
                oos.close();
                System.out.println("Done. Contents added to node1.txt");
            }
    } catch(IOException ex){
            System.out.println("Error:"+ex);
        }   
}
}
