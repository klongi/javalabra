/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import timanttipeli.Game;

/**
 *Interface luo JFramen ja DrawArean
 * 
 * @author Krista
 */

public class UserInterface implements Runnable {
 
    private JFrame window;
    private Game game;    
    private DrawArea gameArea;
    private int height;
    private int widith;
    
    public UserInterface(Game timanttipeli, int height, int widith) {
        this.game = timanttipeli;
        this.height = height;
        this.widith = widith;
    }
 
    @Override
    public void run() {
        window = new JFrame("Diamonds");
        window.setLayout(new BorderLayout());
        window.setBackground(Color.black);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createComponents(window.getContentPane());
        
        window.pack();
        window.setVisible(true);
    }
    
       private void createComponents(Container container) {
        this.gameArea = new DrawArea(game, height, widith);
        container.add(gameArea, BorderLayout.CENTER);
        container.add(createButtons(), BorderLayout.SOUTH);
        this.window.addMouseListener(new Listener(game, gameArea));
    }

    private JPanel createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton newGame = new JButton("Uusi Peli");
        panel.add(newGame);  
        JButton end = new JButton("Lopeta Peli");
        panel.add(end);     
        ButtonListener listener = new ButtonListener(newGame, end, this, game);
        newGame.addActionListener(listener);
        end.addActionListener(listener);       
        return panel;
    }

    public JFrame getFrame() {
        return window;
    }

    public DrawArea getDrawarea() {
        return this.gameArea;
    }

}