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
public class CMDChanger {

    private void changeVolume(int type, int value) {
        if (value >= 0 && value <= 100) {
            double hexval = (value / 100.0) * 65545;
            int setVal = (int) Math.ceil(hexval);
            commandLine c = new commandLine();
            if (type == 1) {
                System.out.println("CMDChanger: Incrase type command");
                //System.out.println("Hexval="+setVal);
                String cmd1 = "nircmdc changesysvolume " + setVal;
                supplyToCMD(cmd1);
            } else {
                System.out.println("CMDChanger: Decrease type command");
                String cmd2 = "nircmdc changesysvolume -" + setVal;
                supplyToCMD(cmd2);
            }
        } else {
            System.out.println("CMDSetter: Please enter a value between 0 and 100");
        }
    }

    private void changeBrightness(int type, int value) {
        System.out.println("This will change brightness");
    }

    private void supplyToCMD(String command) {
        /*
        This function supplies the specified command to commandLine
        Also includes error handling
        Can be called ONLY from within the class
         */
        commandLine c = new commandLine();
        try {
            c.runCmd(command);
        } catch (IOException ex) {
            System.out.println("CMDSetter: Sorry, could not perform the function. Please check permissions.");
        }
    }

    public boolean excecute(int type, int IncOrDec, int value) {
        boolean actionTaken=false;
        if (type == 1) {
            changeVolume(IncOrDec, value);
            System.out.println("CMDSetter: Volume changed by " + value);
            actionTaken=true;
        } else if (type == 2) {
            changeBrightness(IncOrDec, value);
            System.out.println("CMDSetter: Brightness changed by " + value);
            actionTaken=true;
        }
        return actionTaken;
    }
}
