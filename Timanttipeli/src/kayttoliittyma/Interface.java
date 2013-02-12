/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import timanttipeli.Game;

/**
 *Interface luo JFramen ja DrawArean
 * 
 * @author Krista
 */

public class Interface {
 
    private JFrame window;
    private Game game;    
    private DrawArea alusta;
    
    public Interface(Game timanttipeli) {
        this.game = timanttipeli;
        
        window = new JFrame("Diamonds");
        //window.setPreferredSize(new Dimension(400, 400));
        //window.setSize(400, 400);
        window.setLayout(new BorderLayout());
        window.setBackground(Color.black);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
        //window.add(diamonds.getGamearea(),BorderLayout.EAST);
        
        createComponents(window.getContentPane());
        
        window.pack();
        window.setVisible(true);
    }
 
    private void createComponents(Container container) {
        this.alusta = new DrawArea(this.game);
        container.add(alusta);
        this.window.addMouseListener(new Listener(game, alusta));
    }
 
 
    public JFrame getFrame() {
        return window;
    }
    public DrawArea getDrawarea(){
        return this.alusta;
    }
}