/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import timanttipeli.Coordinate;
import timanttipeli.Game;

/**
 *Listener implements MouseListener
 * 
 * @author Krista
 */
public class Listener implements MouseListener {
    private Game game;
    private JPanel drawarea;
    
    public Listener(Game game, JPanel drawarea){
        this.game = game;
        this.drawarea = drawarea;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        System.out.println(x);
        System.out.println(y);
        game.clicked(new Coordinate((y-30)/41, x/41));
        game.setClicked(true);
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
