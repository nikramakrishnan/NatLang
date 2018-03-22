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
public class NewClass {
    public static void main(String[] agr){
        Scanner s=new Scanner(System.in);
    String nam=s.nextLine();
    String part[]=nam.split(" ");
    System.out.println(part[2]+part[0]+part[1]);
    }
}
