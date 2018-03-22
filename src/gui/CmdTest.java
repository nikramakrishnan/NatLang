/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;

/**
 *
 * @author river
 */
public class CmdTest {
    public static void main(String[] args) throws IOException {
        commandLine c = new commandLine();
        RegexParse rp = new RegexParse();
        String q="open i.mp3";
        String path=rp.getPath(q);
        System.out.println(path);
        String cmd3="nircmdc shexec \"open\" \""+path+"\"";
        c.runCmd(cmd3);
        String cmd1="nircmdc setbrightness 0";
        //String cmd2="nircmdc cdrom open";
        String cmd4="nircmdc showerror shexec \"open\" \"asdasd\"";
        //String cmd3="nircmdc shexec \"open\" \"https://www.google.com\"";
        c.runCmd(cmd1);
        //c.runCmd(cmd2);
        //c.runCmd(cmd3);
        c.runCmd(cmd4);
    }
}
