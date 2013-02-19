/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import timanttipeli.Game;

/**
 *
 * @author Krista
 */
public class ButtonListener implements ActionListener {
    private JButton start;
    private JButton finish;
    private JButton end;
    private UserInterface gui;
    private Game game;
    
    
    public ButtonListener(JButton start, JButton end, UserInterface gui, Game game){
        this.start = start;
        this.end = end;
        this.gui = gui;
        this.game = game;
     
    }
    
    /**
     * actionPerformed määrittelee mitä kunkin buttonin klikkaaminen tekee
     * 
     * start aloittaa uuden pelin. Score antaa high score listan tiedot
     * 
     * @param ae 
     */
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == start){
            game.endGame();
            game.getPlayer().setName(askName());
            game.newGame(10,10);
            gui.getDrawarea().repaint();
            
            //gui.updateSize();
        }
        if(ae.getSource() == end){
            game.endGame();
        }
    }
    
    public String askName() {
        String name = JOptionPane.showInputDialog(null, "Nimi: ", "", 1);
        if (name == null || name.isEmpty()) {
            game.getPlayer().setName("Anonymous");
        }
        if (name.length() > 15) {
            JOptionPane.showMessageDialog(null, "Nimi liian pitkä!",
                    "Virhe", 0);
            return askName();
        }
        return name;
    }
    
}
