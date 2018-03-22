/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author river
 */
public class Node2Read {
    private Map<String, List<Integer>> node2map;
     {
        //Map<String, List<Integer>> node2map = new HashMap<>();
        try{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("node2.txt"));
            try {
                node2map = (Map<String, List<Integer>>)ois.readObject();
                //System.out.println(node2map);
            } catch (ClassNotFoundException ex) {
                System.out.println("Class not found");
            }
        } catch(IOException ex){
            System.out.println("File could not be read");
        }
    }
    public Map<String, List<Integer>> getMap(){
        return this.node2map;
    }
    
    public static void main(String[] args) {
        Node2Read node2=new Node2Read();
        System.out.println(node2.node2map);
    }

    
}
