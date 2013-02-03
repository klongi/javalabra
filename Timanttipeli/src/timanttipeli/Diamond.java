
package timanttipeli;

import java.awt.Color;

/**
 * Timanttiluokka, joka määrittelee timantin värin
 * @author Krista
 */
public class Diamond{
    
    /**
     * Taulukko, joka sisältää pelissä käytetyt värit
     */
    private Color[] colors = {Color.blue, Color.red, Color.yellow, Color.green, Color.magenta};
    private Color color;
    
    /**
     * Konstruktorille annetaan parametrina väri
     * 
     * Jos annettu parametri on väliltä 0-4, valitaan kyseinen väri colors taulukosta. Muuten väri arvotaan.
     * 
     * @param color 
     */
    public Diamond(int color){
        if (color <0 || color >= colors.length){
            this.color = colors[(int)((colors.length)*Math.random())];
        }
        else {
            this.color = colors[color];
        }
    }
    
    /**
     * Metodi valitsee colors taulukosta parametrina annetusta indeksistä värin, ja asettaa sen timantille. 
     * Jos annettu parametri ei ole sopivalta väliltä, mitään ei tehdä.
     * @param color 
     */
    public void setColor(int color){
        if (color >= 0 && color < colors.length){
            this.color = colors[color];
        }
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return this.color;
    }
   

}

