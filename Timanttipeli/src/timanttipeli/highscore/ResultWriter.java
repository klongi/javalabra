/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli.highscore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 *
 * @author Krista
 */
public class ResultWriter {

    private String filePath;

    public ResultWriter(String filePath) {
        this.filePath = filePath;

    }

    public void write(String results) {
        try {
            FileOutputStream out = new FileOutputStream(new File(filePath));
            for (int i = 0; i < results.length(); i++) {
                out.write(results.charAt(i));
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen ei onnistunut");
        }
    }
}
