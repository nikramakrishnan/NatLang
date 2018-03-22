/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author river
 */
public class CMDSetter{
    //variable success - stores if the specific command was successfull
    private boolean success;
    private void setBrightness(int value){
        /*
        Sets brightness to supplied value.
        */
        String cmd1="nircmdc setbrightness "+value;
        supplyToCMD(cmd1);
        System.out.println("CMDSetter: Brightness has been set to "+value);
    }
    
    private void setVolume(int value){
        /*
        Sets Master system volume to supplied value.
        */
        if(value>=0 && value<=100){
            double hexval=(value/100.0)*65545;
            int setVal=(int)Math.ceil(hexval);
            //System.out.println("Hexval="+setVal);
            String cmd1="nircmdc setsysvolume "+setVal;
            supplyToCMD(cmd1);
            System.out.println("CMDSetter: Volume has been set to "+value);
        }
        else{
            System.out.println("CMDSetter: Please enter a value between 0 and 100");
        }
    }
    public void mute(int val){
        /*
        Mutes master volume
        */
        String cmd1="";
        if(val==1){
            cmd1="nircmdc mutesysvolume 1";
            System.out.println("CMDSetter: Audio muted");
        }
        else{
            cmd1="nircmdc mutesysvolume 0";
            System.out.println("CMDSetter: Audio un-muted");
        }
        supplyToCMD(cmd1);
    }
    public boolean open(String path){
        /*
        Opens both files and CDROM
        */
        boolean opencd=false;
        String[] cd = {"cd","cdrom","disk","disk drive","diskdrive"};
        for(String cdword:cd){
            if(path.toLowerCase().contains(cdword)){
                opencd=true;
            }
        }
        if(opencd){
            String cmd3="nircmdc cdrom open";
            success=supplyToCMD(cmd3);
             if(success){
                 System.out.println("CMDSetter: Opened CD ROM");
             }
             else{
                System.out.println("CMDSetter: Sorry, unable to perform the action.");
            }
            return true;
        }
        else{
            String cmd4="nircmdc shexec \"open\" \""+path+"\"";
            success=supplyToCMD(cmd4);
            System.out.println("CMDSetter: Opening "+path);
            return success;
        }
    }
    public boolean search(String SearchQ){
        String[] words=SearchQ.split(" ");
        String query=String.join("+", words);
        System.out.println("Searching for \'"+SearchQ+"\'");
        String url="https://www.google.com/search?q="+query+"&ie=UTF-8";
        String cmd4="nircmdc shexec \"open\" \""+url+"\"";
        supplyToCMD(cmd4);
        return true;
    }
    
    public boolean supplyToCMD(String command){
        /*
        This function supplies the specified command to commandLine
        Also includes error handling
        Returns true if function was successfully performed, else returns false
        Can be called ONLY from within the class
        */
        boolean result;
        commandLine c=new commandLine();
        try {
            result=c.runCmd(command);
        } catch (IOException ex) {
            System.out.println("CMDSetter: Sorry, could not perform the function. Please check permissions.");
            result=false;
        }   
        return result;
    }
    
    public boolean excecute(int type,int value){
        boolean actionTaken=false;
        if(type==1){
            setVolume(value);
            
            actionTaken=true;
        }
        else if(type==2){
            setBrightness(value);
            
            actionTaken=true;
        }
        return actionTaken;
    }
}
