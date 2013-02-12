
package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import timanttipeli.Game;

/**
 * Actionlistener hoitaa nappien painamisen kuuntelun
 * 
 * @author Krista
 */
public class MenuListener implements ActionListener {
    
    private JButton start;
    private JButton finish;
    private JButton score;
    private JFrame frame;
    private JTextField player;
    
    public MenuListener(JButton start, JButton score, JTextField player, JFrame frame){
        this.start = start;
        this.score = score;
        this.frame = frame;
        this.player = player;
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
            Game game = new Game(player.getText());
        }
        if(ae.getSource() == score){
            System.out.println("Tulokset tulee joskus");
        }
//        if(ae.getSource() == finish){
//            System.exit(0);
//        }
    }
}
