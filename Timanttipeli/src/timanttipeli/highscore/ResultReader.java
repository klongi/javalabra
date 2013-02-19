/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli.highscore;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileReader hoitaa tiedostosta lukemisen
 * 
 * Reader lukee tiedoston rivi kerrallaan, ja luo jokaisesta rivistä uuden tuloksen,
 * jonka se lisää Results:in tuloslistaan.
 * 
 * @author Krista
 */
public class ResultReader {
    private Results results;
    private String filePath;
    
    public ResultReader(Results results, String filePath){
        this.results = results;
        this.filePath = filePath;
    }
    
    public void lue(){       
        try {
            Scanner lukija = new Scanner(new File(filePath));
            while (lukija.hasNextLine()) {
                String line = lukija.nextLine();
                try {
                    String[] lineList = line.split(":");
                    results.addToResultList(new Result(lineList[0].trim(), Integer.parseInt(lineList[1].trim())));
                } catch (Exception e) {
                    System.out.println("Lukeminen ei onnistunut");
                }
            }

        } catch (Exception e) {
            System.out.println("Tiedoston avaaminen ei onnistunut");
        } 
    
    }
    
}
