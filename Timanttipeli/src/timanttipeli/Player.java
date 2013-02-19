/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

/**
 * Pelaajaluokka pitää kirjaa pelaajan pisteistä
 * 
 * @author Krista
 */
public class Player {
    private int points;
    private String name;
    
    public Player(){
        points = 0;
    }
    
    public void addPoints(int points){
        this.points+=points;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name+", "+points+" points";
    }
    
}
