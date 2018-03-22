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
public class Node3Read {
    private Map<String, List<String>> node3map;
     {
        //Map<String, List<Integer>> node2map = new HashMap<>();
        try{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("node2syn.txt"));
            try {
                node3map = (Map<String, List<String>>)ois.readObject();
                //System.out.println(node2map);
            } catch (ClassNotFoundException ex) {
                System.out.println("Class not found");
            }
        } catch(IOException ex){
            System.out.println("File could not be read");
        }
    }
    public Map<String, List<String>> getMap(){
        return this.node3map;
    }
    
    public static void main(String[] args) {
        Node3Read node3=new Node3Read();
        System.out.println(node3.node3map);
    }
}
