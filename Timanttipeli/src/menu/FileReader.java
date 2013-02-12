/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.File;
import java.util.Scanner;

/**
 * FileReader hoitaa tiedostosta lukemisen
 * @author Krista
 */
public class FileReader {
    private String results;
    
    public FileReader(){
        this.results = "";
    }
    
    public String lue(){       
        try{
            Scanner lukija = new Scanner(new File("src/menu/results.txt"));
            while(lukija.hasNextLine()){
                String line = lukija.nextLine();
                results += line + "\n";              
            }
            
        } catch(Exception e){
            System.out.println("Filreader: " + e);
        }  
        return results;      
    }
    
}
