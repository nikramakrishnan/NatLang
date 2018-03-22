/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author river
 */
public class commandLine {
    public boolean runCmd(String command) throws IOException{
        boolean result;
        //Get program path
        String path = new File(".").getCanonicalPath();
        //Set Variables
        String cmdFlush="";
        //Create CMD Process and pass the command
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            cmdFlush=cmdFlush+"\n"+line;
    }
    if(cmdFlush.toLowerCase().contains("nircmd")){
        result=false;
    }
    else{
        result=true;
    }
        return result;
}
}
