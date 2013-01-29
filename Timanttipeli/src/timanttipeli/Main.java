
package timanttipeli;

import javax.swing.SwingUtilities;
import kayttoliittyma.Interface;


public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Interface infa = new Interface(game);
        game.setDrawarea(infa.getDrawarea());
        game.start();
        game.GameLoop();
        
     
    }
}
