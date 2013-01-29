/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

import java.awt.event.ActionListener;
import javax.swing.Timer;
import kayttoliittyma.DrawArea;

/**
 *
 * @author Krista
 */
public class Game extends Timer {
    private Diamonds diamonds;
    private DrawArea area;
    private boolean clicked;
    private Player player;
    
    public Game() {
        super(1000, null);
        diamonds = new Diamonds(10,10);
    }
    
    public Diamonds getDiamonds(){
        return diamonds;
    }
    
    public void setDrawarea(DrawArea area) {
        this.area = area;
    }
    
    public DrawArea getDrawarea() {
        return this.area;
    }
    
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    public void GameLoop() {
        while (true) {
            this.stop();
            //area.repaint();
            if(this.clicked) {
                area.repaint();
                this.clicked = false;
                this.start();
            }
            
        }
    }

    
}
