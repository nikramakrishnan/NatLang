/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author river
 */
/*
Initial code credit - Veeki Yadav
*/
public class FileLocation {

    static String FilePath;

    static String findFile(String name, File file) {
        //STORE THE FILES IN THE LIST
        //FilePath=null;
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findFile(name, fil);
                } else if (name.equalsIgnoreCase(fil.getName())) {
                    fil.getParentFile();
                    FilePath = fil.toString();
                    break;
                }
            }
        }
        return FilePath;
        
    }

    static String ReturnPath(String FileName) {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("FileLocation: This function is not supported.");
        }
        String filepath="";
        String dirFile=FileName+":/";
        File f = new File(FileName);
        File df = new File(dirFile);
        //Check CD ROM
        boolean opencd=false;
        String[] cd = {"cd","cdrom","disk","disk drive","diskdrive"};
        for(String cdword:cd){
            if(FileName.toLowerCase().contains(cdword)){
                opencd=true;
            }
        }
        //End check CD ROM
        if (f.exists()) {
            filepath=FileName;
        }
        else if(df.isDirectory()){
            filepath=FileName;
        }
        else if(FileName.matches("(facebook|google|wikipedia|github|youtube|gmail)")){
            filepath="www."+FileName+".com";
        }
        else if(FileName.matches(".*(.com|.in|.co.in|.io|.xyz|.org|.ru|.to)")){
            filepath="www."+FileName;
        }
        else if(FileName.matches("(.*)\\.(.*)\\.(.*)")){
            filepath=FileName;
        }
        else if(opencd){
            filepath="cdrom";
        }
        else{
            //Scanner scan = new Scanner(System.in);
            //System.out.println("> Enter the directory where the file is:");
            String directory;
            String str = JOptionPane.showInputDialog(null, "Enter directory to search file : ", "Directory Input", 1);
            if(str!=null){
                directory=str;
            }
            else{
                System.out.println("No input given.");
                directory=null;
            }
            //System.out.println(directory);
            if(directory.matches("\\w") && directory!=null){
                directory=directory+":\\";
                System.out.println("FileLocation: Looking for the file...");
                filepath=findFile(FileName, new File(directory));
            }
            else{
            System.out.println("FileLocation: Looking for the file...");
            filepath = findFile(FileName, new File(directory));

            }
        }
        return filepath;
}
}
