/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author river
 */
public class MapOnOff {
    public void execute(List<String> pri,List<String> sec){
        for(int i=0;i<pri.size();i++){
            String prim=pri.get(i);
            String seco=sec.get(i);
            go(prim,seco);
        }
    }
    private static void go(String pri,String sec){
        if(pri.equals("volume")){
            switch (sec) {
                case "off":
                    soundOff();
                    break;
                case "on":
                    soundOn();
                    break;
                case "troubleshoot":
                    soundTroubleshoot();
                    break;
                default:
                    break;
            }
        }
       if(pri.equals("wifi")){
            switch (sec) {
                case "on":
                    wifiOn();
                    break;
                case "off":
                    wifiOff();
                    break;
                case "troubleshoot":
                    wifiTroubleshoot();
                    break;
                default:
                    break;
            }
       }
    }
    private static void soundOff(){
        //This will mute
        CMDSetter cs=new CMDSetter();
        cs.mute(1);
    }
    private static void soundOn(){
        CMDSetter cs=new CMDSetter();
        cs.mute(0);
    }
    private static void soundTroubleshoot(){
        String cmd1="msdt.exe /id AudioPlaybackDiagnostic";
        System.out.println("Troubleshooting sound...");
        supplyToCMD(cmd1);   
    }
    private static void wifiOn(){
        System.out.println("Operation currently unsupported");
    }
    private static void wifiOff(){
        System.out.println("Operation currently unsupported");
    }
    private static void wifiTroubleshoot(){
        String cmd1="msdt.exe /id NetworkDiagnosticsNetworkAdapter";
        System.out.println("Troubleshooting wifi...");
        supplyToCMD(cmd1);
    }
    private static boolean supplyToCMD(String command){
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
}
