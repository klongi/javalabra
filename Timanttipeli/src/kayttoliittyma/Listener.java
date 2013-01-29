/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import timanttipeli.Game;

/**
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
        System.out.println("Clicked");
        int x = (int) event.getX();
        int y = (int) event.getY();
        System.out.println(x);
        System.out.println(x/41);
        System.out.println(y);
        System.out.println((y-30)/41);
        System.out.println("here");
        game.setClicked(true);
        game.getDiamonds().destroyDiamond((y-30)/41, x/41);
        
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
