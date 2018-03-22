/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author river
 */
public class FileMapRead {
    public static void main(String[] args) {
        Map<Integer, List<String>> map = new HashMap<>();
        try{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("node1.txt"));
            try {
                map = (Map<Integer, List<String>>)ois.readObject();
                System.out.println(map);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FileMapRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch(IOException ex){
            System.out.println("ex:"+ex);
        }
    }
}
