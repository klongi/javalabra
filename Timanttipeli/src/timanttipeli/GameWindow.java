/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Krista
 */
public class GameWindow {

    private JFrame window;
    private Diamonds diamonds;
            
    public GameWindow(Game game){
        window = new JFrame("Diamonds");
        diamonds = new Diamonds(10,10);
        window.setSize(400, 400);
        window.setLayout(new BorderLayout());
        window.setBackground(Color.black);
        window.setResizable(false);
        
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        window.add(diamonds.getGamearea(),BorderLayout.EAST);
    }
    
    public Diamonds getDiamonds(){
        return diamonds;
    }
    
    public void repaint() {
        diamonds.update();
    }
}
