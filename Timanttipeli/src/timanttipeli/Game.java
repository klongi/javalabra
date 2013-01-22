/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timanttipeli;

/**
 *
 * @author Krista
 */
public class Game {
    
    private GameWindow window;
    
    public Game() {
        window = new GameWindow(this);
        GameLoop();
    }
    
    public void GameLoop() {
        window.repaint();
    }
    
    public GameWindow getGameWindow(){
        return window;
    }
}
