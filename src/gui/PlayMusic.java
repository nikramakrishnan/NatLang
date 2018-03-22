/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;

/**
 *
 * @author river
 */
public class PlayMusic {
    public static List<File> fileList=new ArrayList<>();
    public static void main(String[] args) {
        File musicFolder=getMusicDir();
        if(musicFolder==null){
            musicFolder=storeMusicDir();
        }
        System.out.println("Music folder is "+musicFolder);
        if(musicFolder!=null){
            listFiles(musicFolder);
            long seed = System.nanoTime();
            Collections.shuffle(fileList, new Random(seed));
                try {
                    Desktop.getDesktop().open(fileList.get(0));
                }catch (IOException ex) {
                    System.out.println("Error:"+ex.getMessage());
                }
        }
    }
    
    public static File getMusicDir(){
        File musicFile=new File("musiclib.txt");
        if(musicFile.exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("musiclib.txt"));
             try {
                File folder = (File) ois.readObject();
                //System.out.println(map);
                return folder;
             } catch (ClassNotFoundException ex) {
                 System.out.println("ex:"+ex.getMessage());
                 return null;
             }
            } catch (IOException ex) {
             System.out.println("ex:" + ex.getMessage());
             return null;
            }
        }
        else{
            return null;
        }
    }
    public static File storeMusicDir(){
        File folder=fileChooser();
        if(folder!=null){
            try{
                FileOutputStream fout=new FileOutputStream("musiclib.txt");

                try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                    oos.writeObject(folder);
                    oos.close();
                    System.out.println("Done. Contents added to musiclib.txt");
                    return folder;
                }
            } catch(IOException ex){
                System.out.println("Error:"+ex.getMessage());
                return null;
            }
        }
        return null;
    }
    public static File fileChooser(){
        File finalFolder;
        JFileChooser chooser = new JFileChooser();
        String home=System.getProperty("user.home");
        chooser.setCurrentDirectory(new java.io.File(home));
        chooser.setDialogTitle("Select Music Folder");
        chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false); 
        int result=chooser.showDialog(null,"Select Folder");
        if(result==JFileChooser.APPROVE_OPTION){
            File folder1=chooser.getSelectedFile();
            File folder2=chooser.getCurrentDirectory();
            if(folder1==null){
                finalFolder=folder2;
            }
            else{
                finalFolder=folder1;
            }
        }
        else{
            System.out.println("Cancel button pressed");
            finalFolder=null;
        }
        return finalFolder;
    }
    public static void listFiles(File directoryName){
        File directory = directoryName;
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile() && file.getAbsolutePath().endsWith(".mp3")){
                fileList.add(file);
            } else if (file.isDirectory()){
                listFiles(file);
            }
        }
    }

}
