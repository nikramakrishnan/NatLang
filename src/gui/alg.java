/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.*;

/**
 *
 * @author Vishen
 */
public class alg {

    /**
     */
    //to get input from the user
    public String part[];
    public List<String> inp;
    public String x;
    public String strcpy;
    String[] y = {"ok"};
    Scanner s = new Scanner(System.in);

    String input;

    public void in(String st) {
        System.out.print(">");
        x = st;
        strcpy=new String(x);
        strcpy=strcpy.toLowerCase();
        CleanUp cu=new CleanUp();
        x=cu.clean(x); //Clean the String. THIS CANNOT BE USED IN FINAL VERSION
    }

    // to split the string into an array
    public void split() {
        String xpart[] = x.split(" ");
        part = xpart;
        List<String> input=new ArrayList<>(Arrays.asList(part)); //This creates an actual ArrayList, not just a wrapper
        inp=input;
        //System.out.println("Input List:"+inp);
    }
}
