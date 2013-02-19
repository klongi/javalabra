/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Krista
 */
public class TimerListener implements ActionListener {
        private Game game;
    
        public TimerListener(Game game) {
            this.game = game;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            game.reduceTimeRemaining();
            if (game.getTimeRemaining() >= 0) {
                game.getDrawarea().repaint();
            } else {
                System.out.println("Time's up!");
                game.endGame();
                
            }
        }
}
