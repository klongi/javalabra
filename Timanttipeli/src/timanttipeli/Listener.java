/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Krista
 */
public class Listener implements MouseListener {
    private Game game;
    private GameWindow window;
    
    public Listener(Game game){
        this.game = game;
        this.window = game.getGameWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
