/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import timanttipeli.Player;

/**
 *
 * @author Krista
 */
public class InfoField extends JPanel {
    private Player player;
    
    public InfoField() {
        this.setLayout(new GridLayout(2, 2, 0, 0));
        this.setSize(30,30);
        this.setPreferredSize(new Dimension(30,30));
        this.setVisible(true);
        this.setBackground(Color.black);
    }
    
    public void setPlayer(Player player) {
        this.player=player;
    }
    
    @Override
    protected void paintComponent(Graphics gfx){
        super.paintComponent(gfx);
        gfx.setColor(Color.red);
        if (player == null) {
            gfx.drawString("Points: ", 20, 20);
            return;
        }
        gfx.setColor(Color.BLACK);
        gfx.drawString("Points: "+player.getPoints(), 1, 1);
    }
    
}
