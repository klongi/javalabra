/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import timanttipeli.Coordinate;
import timanttipeli.Game;

/**
 *Listener implements MouseListener
 * 
 * @author Krista
 */
public class Listener implements MouseListener {
    private Game game;
    private JFrame window;
    
    public Listener(Game game, JFrame window){
        this.game = game;
        this.window = window;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
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
