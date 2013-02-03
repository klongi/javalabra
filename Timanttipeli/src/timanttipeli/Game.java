package timanttipeli;

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
    
    /**
     * Konstruktori kutsuu yliluokan konstruktoria, sekä luo Diamondsin.
     */
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
    
    /**
     * Metodi piirtää kaiken uudestaan, kun on tapahtunut muutoksia.
     */
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
