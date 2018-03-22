/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Vishen
 */
import java.io.IOException;
import javax.swing.JTextArea;
import java.io.*;

public class Console{
   public void redirectConsoleTo(final JTextArea textarea) {
    PrintStream out;
       out = new PrintStream(new ByteArrayOutputStream() {
           @Override
           public synchronized void flush() throws IOException {
               textarea.setText(toString());
               
           }
       }, true);

    System.setErr(out);
    System.setOut(out);
    }
}
