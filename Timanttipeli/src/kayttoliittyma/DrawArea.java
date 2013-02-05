
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import timanttipeli.Diamond;
import timanttipeli.Game;

/**
 *DrawArea extends JPanel
 * 
 * @author Krista
 */

public class DrawArea extends JPanel{
    private Game timanttipeli;

    public DrawArea(Game timanttipeli){
        this.timanttipeli = timanttipeli;
        this.setLayout(new GridLayout(10, 10, 0, 0));
        this.setSize(450,450);
        this.setPreferredSize(new Dimension(405,405));
        this.setVisible(true);
        this.setBackground(Color.black);
    }
    
    public void update() {
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics gfx){
        super.paintComponent(gfx);
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, 405, 405);
        Diamond[][] diamondGraph = timanttipeli.getDiamonds().getDiamondArray();
        for(int i = 0; i < diamondGraph.length; i++){
            for (int j = 0; j < diamondGraph[0].length; j++) {
                 gfx.setColor(diamondGraph[i][j].getColor());
                 gfx.fill3DRect(5+40*j, 5+40*i, 35, 35, true);
            }
        }
    }
}
