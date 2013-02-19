/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Krista
 */
public class Menu {
    private JFrame window;
    private JTextField player;

    public Menu() {
        window = new JFrame("Diamonds");
        window.setPreferredSize(new Dimension(400,400));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());
        //window.paint(window.getGraphics());
        createComponents(window.getContentPane());
        window.pack();
        window.setVisible(true);
        
    }
    
    /**
     * Metodi lisää napit ja tekstikentän käyttöliittymän
     * container-olioon
     * 
     * @param container 
     */
    
    private void createComponents(Container container) {
        container.add(createNameField(), BorderLayout.CENTER);
        container.add(createButtons(), BorderLayout.SOUTH);      
    }
    
//    private void showResults(Container container) {
//        container.removeAll();
//        FileReader lukija = new FileReader();
//        JTextArea results = new JTextArea(lukija.lue());
//        results.setEditable(false);
//        results.setForeground(Color.WHITE);
//        results.setBackground(Color.BLACK);
//        container.add(results);
//        container.add(createResultButtons());
//    }
//    
//    private JPanel createResultButtons() {
//        JPanel panel = new JPanel(new GridLayout(1, 1));
//        panel.setBackground(Color.BLACK);     
//        JButton start = new JButton("Menu");
//        start.setForeground(Color.RED);
//        panel.add(start);
//        
//        JButton score = new JButton("High Score List");
//        panel.add(score);
//        score.setForeground(Color.RED);
//        
//        
//        MenuListener kuuntelija = new MenuListener(start, score, player, window);
//        start.addActionListener(kuuntelija);
//        score.addActionListener(kuuntelija);
//        
//        return panel;
//    }
    
    /**
     * Metodi luo JPanel olion ja lisää siihen kaksi nappia
     * kuuntelijalla varustettuna
     * 
     * @Return panel
     */
    
    private JPanel createNameField(){
        JPanel panel = new JPanel(new GridLayout(1,1));
        panel.setBackground(Color.BLACK);
        JLabel nameFieldText = new JLabel("Your Name: ");
        nameFieldText.setForeground(Color.WHITE);
        this.player = new JTextField();
      
        panel.add(nameFieldText);
        panel.add(player);
        
        return panel;     
    }
    
    private JPanel createButtons() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.BLACK);     
        JButton start = new JButton("New Game");
        start.setForeground(Color.RED);
        panel.add(start);
        
        JButton score = new JButton("High Score List");
        panel.add(score);
        score.setForeground(Color.RED);
        
//        
//      //  MenuListener kuuntelija = new MenuListener(start, score, player, window);
//        start.addActionListener(kuuntelija);
//        score.addActionListener(kuuntelija);
        
        return panel;
    }


    
    public JFrame getFrame(){
        return window;
    }
    
}


