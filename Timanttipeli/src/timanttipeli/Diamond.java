
package timanttipeli;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Krista
 */
public class Diamond extends JPanel {
    private Color[] colors = {Color.blue, Color.red, Color.yellow, Color.green, Color.magenta};
    private Color color;
    
    //jos annettu parametri on väliltä 0-4, valitaan väri colors taulukosta. Muuten väri arvotaan
    public Diamond(int color){
        if (color <0 || color >= colors.length){
            this.color = colors[(int)((colors.length)*Math.random())];
        }
        else {
            this.color = colors[color];
        }
    }
    
    public void setColor(int color){
        if (color >= 0 && color < colors.length){
            this.color = colors[color];
        }
    }
    
    public Color getColor(){
        return this.color;
    }
    
    @Override
    public void paint(Graphics gfx){
            this.paintBall(gfx);
    }
    
    public void paintBall(Graphics gfx) {
        gfx.setColor(this.color);
        gfx.fill3DRect(2, 2, 35, 35, true);
//        if (this.color == Color.blue)
//            gfx.fillOval(2, 2, 35, 35);
//        if (this.color == Color.red)
//            gfx.fillRect(2, 2, 35, 35);
//        if (this.color == Color.yellow)
//            gfx.fillRoundRect(2, 2, 35, 35, 20, 20);
//        if (this.color == Color.green)
//            gfx.fillOval(2, 2, 35, 35);
//        if (this.color == Color.magenta)
//            gfx.fillRect(2, 2, 35, 35);
        
    }

}

