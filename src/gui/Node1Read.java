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
public class Node1Read {
    private Map <Integer, List <String>> map;
    {
        //Map <Integer, List <String>> map = new HashMap <> ();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("node1.txt"));
         try {
            map = (Map <Integer, List < String>>) ois.readObject();
            //System.out.println(map);
         } catch (ClassNotFoundException ex) {
             System.out.println("ex:"+ex.getMessage());
         }
        } catch (IOException ex) {
         System.out.println("ex:" + ex.getMessage());
        }
    }
    public Map<Integer, List <String>> getNode1(){
        return this.map;
    }
    public static void main(String[] args) {
        Node1Read n=new Node1Read();
        Map <Integer, List < String>> m=n.getNode1();
        System.out.println(m);
    }
}
